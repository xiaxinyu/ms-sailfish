package com.sailfish.gateway.config;

import com.sailfish.gateway.filter.LimitGatewayFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory.Config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Slf4j
public class RouteLocatorConfig {
    @Bean
    public GatewayFilter stripPrefixGatewayFilter() {
        Config config = new Config();
        config.setParts(1);
        GatewayFilter stripPrefixGatewayFilter = new StripPrefixGatewayFilterFactory().apply(config);
        return stripPrefixGatewayFilter;
    }

    @Bean
    public GatewayFilter limitGatewayFilter() {
        return new LimitGatewayFilter(1, 1, Duration.ofSeconds(1));
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, GatewayFilter stripPrefixGatewayFilter, GatewayFilter limitGatewayFilter) {
        log.info("**************************** RouteLocatorConfig ****************************");
        return builder
                .routes()
                .route(r -> r.path("/oauth2/**").filters(f -> f.filter(stripPrefixGatewayFilter).filter(limitGatewayFilter)).uri("lb://ms-sailfish-authorization").order(0).id("oauth2"))
                .build();
    }
}
