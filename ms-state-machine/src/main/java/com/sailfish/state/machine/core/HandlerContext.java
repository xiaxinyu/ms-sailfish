package com.sailfish.state.machine.core;

import com.sailfish.state.machine.handler.AbstractHandler;
import com.sailfish.state.machine.utils.GetBeanTool;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * HandlerContext
 *
 * @author XIAXINYU3
 * @date 2020.1.16
 */
@Component
public class HandlerContext {
    private Map<String, Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractHandler getInstance(String type) {
        Class clazz = handlerMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }
        return (AbstractHandler) GetBeanTool.getBean(clazz);
    }
}
