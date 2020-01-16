package com.sailfish.state.machine.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.Map;

public class HandlerProcessor implements BeanFactoryPostProcessor {
    private static final String HANDLER_PACKAGE = "这里指定要扫描的包路径";

    //扫描指定路径下的所有拥有HandlerType注解的class，并将信息存入到一个HashMap<String,Class>中
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //自定义设定HashMap初始容量，阅读HashMap源码可得知最好预先指定hashmap的size为2的整数次幂次方
        // 关于HashMap这里不做详解，不过其组成结构和扩容机制是不错的知识点，感兴趣的朋友可以深入了解学习
        Map<String, Class> handlerMap = new HashMap(8);
        ClassScaner.scan(HANDLER_PACKAGE, HandlerType.class).forEach(clazz -> {
            String type = clazz.getAnnotation(HandlerType.class).value();
            handlerMap.put(type, clazz);
        });
        // 创建一个HandlerContext容器，并将HandlerContext
        HandlerContext context = new HandlerContext(handlerMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }
}