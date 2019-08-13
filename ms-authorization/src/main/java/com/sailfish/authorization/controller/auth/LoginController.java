package com.sailfish.authorization.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("/oauth/login")
	public ModelAndView loginPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("oauth/login");
		view.addObject("loginProcessUrl", "/oauth/authorize");
		return view;
	}
}