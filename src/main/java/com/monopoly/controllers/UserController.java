package com.monopoly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@RequestMapping(value = "/signin.action", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView("signin");
		return mav;
	}
}
