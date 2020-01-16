package com.sailfish.state.machine.core;

import java.lang.annotation.*;

/**
 * HandlerType
 *
 * @author XIAXINYU3
 * @date 2020.1.16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    String value();
}