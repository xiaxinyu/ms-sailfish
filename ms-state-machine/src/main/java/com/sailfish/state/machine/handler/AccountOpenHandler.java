package com.sailfish.state.machine.handler;

import com.sailfish.state.machine.enumeration.CheckTypeEnum;
import com.sailfish.state.machine.core.HandlerType;
import com.sailfish.state.machine.model.CheckResultReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * AccountOpenHandler
 *
 * @author XIAXINYU3
 * @date 2020.1.16
 */
@Slf4j
@Component
@HandlerType("ACCOUNT_OPEN")
public class AccountOpenHandler extends AbstractHandler {
    @Override
    public void handle(CheckResultReq req) {
        if (CheckTypeEnum.ACCOUNT_OPEN.getCode().equals(req.getCheckType())) {

        } else {
            log.info("审核不通过");
        }
    }
}