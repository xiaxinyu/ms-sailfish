package com.sailfish.gateway.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class RouteListener {
    @Autowired
    PropertiesRouteDefinitionLocator propertiesRouteDefinitionLocator;

    @Autowired
    DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator;

    @EventListener(ApplicationReadyEvent.class)
    public void springApplicationReadyEvent() {
        log.info("Init ClientRouteDefinition");

        Flux<RouteDefinition> routeDefinitionFlux = discoveryClientRouteDefinitionLocator.getRouteDefinitions();
        Iterable<RouteDefinition> routeDefinitions = routeDefinitionFlux.toIterable();
        int counter = 0;
        for (RouteDefinition routeDefinition : routeDefinitions) {
            log.info("RouteDefinition: {}", routeDefinition);
            counter++;
        }

        Flux<RouteDefinition> routeDefinitionFlux1 = propertiesRouteDefinitionLocator.getRouteDefinitions();
        Iterable<RouteDefinition> routeDefinitions1 = routeDefinitionFlux1.toIterable();
        for (RouteDefinition routeDefinition : routeDefinitions1) {
            log.info("RouteDefinition: {}", routeDefinition);
            counter++;
        }
        log.info("ClientRouteDefinition:  size={}", counter);
    }
}
