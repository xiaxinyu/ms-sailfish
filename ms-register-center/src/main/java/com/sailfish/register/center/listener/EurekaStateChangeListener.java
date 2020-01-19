package com.sailfish.register.center.listener;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        String appName = event.getAppName();
        String serverId = event.getServerId();
        log.info("EurekaInstanceCanceledEvent: appName={}, serverId={}", appName, serverId);
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("EurekaInstanceRegisteredEvent: {}", instanceInfo);
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        String appName = event.getAppName();
        String serverId = event.getServerId();
        log.info("EurekaInstanceRenewedEvent: appName={}, serverId={}", appName, serverId);
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("EurekaRegistryAvailableEvent: *****************Eureka is available*****************");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.info("EurekaServerStartedEvent: *****************Eureka is started*****************");
    }
}
