package com.sailfish.gateway.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class StartUpListener {
    @Autowired
    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator;

    @EventListener(ApplicationReadyEvent.class)
    public void springApplicationReadyEvent() {
        Flux<RouteDefinition> routeDefinitionFlux = discoveryClientRouteDefinitionLocator.getRouteDefinitions();
        Iterable<RouteDefinition> routeDefinitions = routeDefinitionFlux.toIterable();
        log.info("Init ClientRouteDefinition");
        int counter = 0;
        for (RouteDefinition routeDefinition : routeDefinitions) {
            log.info("RouteDefinition: {}", routeDefinition);
            counter++;
        }
        log.info("ClientRouteDefinition:  size={}", counter);
    }
}
