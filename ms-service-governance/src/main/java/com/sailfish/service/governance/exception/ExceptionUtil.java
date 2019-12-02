package com.sailfish.service.governance.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @Description: 流控错误类处理器
 * @param:
 * @return:
 * @author: fanjc
 * @Date: 2019/5/10
 */
public class ExceptionUtil {

    /**
     * @Description: 普通阻塞错误
     * @param:
     * @return:
     * @author: fanjc
     * @Date: 2019/5/10
     */
    public static String exceptionHandler(BlockException ex) {
        System.err.println("错误发生: " + ex.getClass().getCanonicalName());
        return "error";
    }
}
