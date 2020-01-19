package com.sailfish.state.machine;

import com.sailfish.state.machine.core.HandlerContext;
import com.sailfish.state.machine.model.CheckResultReq;

import javax.annotation.Resource;

public class CheckResultHandleManager {
    @Resource
    private HandlerContext handlerContext;

    //化繁为简
    public void deal(CheckResultReq req) {
        //AbstractHandler handler = handlerContext.getInstance(dto.getAuditType());
        //handler.handle(dto);
    }
}
