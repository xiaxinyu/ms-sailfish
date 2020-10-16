package com.sailfish.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XIAXINYU3
 * @date 2020.10.16
 */
@Controller
public class HomeController {
    @RequestMapping("/login_success")
    public String success() {
        return "success";
    }

    @RequestMapping("/login_fail")
    public String fail() {
        return "login";
    }
}