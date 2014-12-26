package com.monopoly.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.bean.User;
import com.monopoly.services.TestService;
import com.monopoly.services.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public ModelAndView getIndex(@CookieValue(value = "bb_data", required = false) String hash) {
		ModelAndView mav = new ModelAndView("index");
		
		User user = userService.getUser(hash);
        if (user == null) {
            return new ModelAndView("redirect:signin.action");
        }
        
        String email = user.getEmail();
		ArrayList<String> testList = testService.getTest();
		
		mav.addObject("email", email);
		mav.addObject("testList", testList);
		return mav;
	}
}
