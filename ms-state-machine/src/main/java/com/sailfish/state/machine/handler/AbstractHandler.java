package com.sailfish.state.machine.handler;

import com.sailfish.state.machine.model.CheckResultReq;

/**
 * AbstractHandler
 *
 * @author XIAXINYU3
 * @date 2020.1.16
 */
public abstract class AbstractHandler {
    abstract public void handle(CheckResultReq req);
}
