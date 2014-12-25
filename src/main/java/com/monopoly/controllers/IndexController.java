package com.monopoly.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.services.TestService;

@Controller
public class IndexController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView("index");
		ArrayList<String> testList = testService.getTest(); 
		mav.addObject("testList", testList);
		return mav;
	}
}
