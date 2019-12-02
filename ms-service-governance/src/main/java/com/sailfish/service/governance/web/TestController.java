package com.sailfish.service.governance.web;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.sailfish.service.governance.exception.ExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {


    /**
     * @Description: 手动测试
     * @param:
     * @return:
     * @author: fanjc
     * @Date: 2019/5/15
     */
    @SentinelResource("flowTest")
    @ResponseBody
    @GetMapping(value = "/flowTest", produces = "application/json;charset=UTF-8")
    public void getTest() {
        initFlowRules();

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello world");
            } catch (BlockException ex) {
                ex.printStackTrace();
                // 处理被流控的逻辑
                System.out.println("blocked!");
                return;
            }
        }
    }


    /**
     * 初始化规则
     */
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * @Description: 注解测试
     * @param:
     * @return:
     * @author: fanjc
     * @Date: 2019/5/15
     */
    @SentinelResource(value = "demoTest", blockHandler = "exceptionHandler", blockHandlerClass = {ExceptionUtil.class})
    @GetMapping(value = "/demoTest", produces = "application/json;charset=UTF-8")
    public String getTest1() {
        System.out.println("demoTest isOk");
        return "isOk";

    }
}