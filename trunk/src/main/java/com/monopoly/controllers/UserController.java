package com.monopoly.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.bean.User;
import com.monopoly.services.UserDbService;
import com.monopoly.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserDbService userService;
//	private UserService userService;
	
	@RequestMapping(value = "/signin.action", method = RequestMethod.GET)
	public ModelAndView getLogin(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "signout", required = false) String signOut,
			@RequestParam(value = "isVeretificationUser", required = false, defaultValue = "true") Boolean isVerificationUser) {
		ModelAndView mav = new ModelAndView("signin");
		User user = userService.getUser(hash); 
		
		if(signOut != null && user != null) {
			userService.signOut(hash);
			return mav;
		}
		
		if (user != null) {
			return new ModelAndView("redirect:index.action");
		}
		
		mav.addObject("isVerificationUser", isVerificationUser);
		
		return mav;
	}
	
	@RequestMapping(value = "/signin.action", method = RequestMethod.POST)
    public ModelAndView postLogin(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password) {
        Cookie cookie = userService.signIn(email, password);

        if (cookie != null) {
            response.addCookie(cookie);
            return new ModelAndView("redirect:index.action");           
        }
        return new ModelAndView("redirect:signin.action?isVerificationUser=false");        
    }
}


