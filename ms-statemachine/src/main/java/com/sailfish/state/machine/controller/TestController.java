package com.sailfish.state.machine.controller;


import com.sailfish.state.machine.domain.Events;
import com.sailfish.state.machine.domain.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    @RequestMapping("/testMachine")
    @ResponseBody
    public void testMachine() {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }
}