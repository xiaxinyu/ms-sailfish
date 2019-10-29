package com.sailfish.gateway.filter;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class LimitGatewayFilter implements GatewayFilter, Ordered {
    private int capacity;
    private int refillTokens;
    private Duration refillDuration;
    private static final Map<String, Bucket> CACHE = new ConcurrentHashMap<>();

    public LimitGatewayFilter(int capacity, int refillTokens, Duration refillDuration) {
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillDuration = refillDuration;
    }

    private Bucket createNewBucket() {
        Refill refill = Refill.greedy(refillTokens, refillDuration);
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = CACHE.computeIfAbsent(ip, k -> createNewBucket());
        log.info("IP: " + ip + ", available tokens :" + bucket.getAvailableTokens());
        if (bucket.tryConsume(1L)) {
            return chain.filter(exchange);
        }
        log.info("IP: " + ip + ", available tokens :" + bucket.getAvailableTokens() + " too many requests");
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}