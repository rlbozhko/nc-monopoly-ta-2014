package com.monopoly.controllers;

import java.io.IOException;

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

@Controller
public class UserController {
	@Autowired
	private UserDbService userDbService;

	// private UserService userService;

	@RequestMapping(value = "/signin.action", method = RequestMethod.GET)
	public ModelAndView getLogin(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "signout", required = false) String signOut,
			@RequestParam(value = "isVeretificationUser", required = false, defaultValue = "true") Boolean isVerificationUser) {
		ModelAndView mav = new ModelAndView("signin");
		User user = userDbService.getUser(hash);

		if (signOut != null && user != null) {
			userDbService.signOut(hash);
			return mav;
		}

		if (user != null) {
			return new ModelAndView("redirect:index.action");
		}

		mav.addObject("isVerificationUser", isVerificationUser);

		return mav;
	}

	@RequestMapping(value = "/signin.action", method = RequestMethod.POST)
	public ModelAndView postLogin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) {
		Cookie cookie = userDbService.signIn(email, password);

		if (cookie != null) {
			response.addCookie(cookie);
			return new ModelAndView("redirect:index.action");
		}
		return new ModelAndView(
				"redirect:signin.action?isVerificationUser=false");
	}
	
	@RequestMapping(value = "/signup.action", method = RequestMethod.GET)
    public ModelAndView getLogin(@CookieValue(value = "bb_data", required = false) String hash) {
        ModelAndView mav = new ModelAndView("signup");

        User user = userDbService.getUser(hash);
        if (user != null) {
            return new ModelAndView("redirect:index.action");
        }

        boolean isPasswordValid = true;

        mav.addObject("isPasswordValid", isPasswordValid);

        return mav;
    }

	@RequestMapping(value = "/signup.action", method = RequestMethod.POST)
	public ModelAndView postLogin(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "nickName", required = false) String nickName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "retypePassword", required = false) String retypePassword) {
		ModelAndView mav = new ModelAndView("signup");

		User user = userDbService.getUser(hash);
		if (user != null) {
			return new ModelAndView("redirect:signin.action");
		}

		boolean isEmailExists = false;
		boolean isPasswordValid = false;
		boolean isNickNameExists = false;

		isEmailExists = userDbService.isEmailExists(email);
		isNickNameExists = userDbService.isNickNameExists(nickName);
		if (password.equals(retypePassword)) {
			isPasswordValid = true;
		}

			if (!isEmailExists && !isNickNameExists && isPasswordValid) {
				user = new User(email, password, hash, nickName);
				userDbService.createUser(user);
				return new ModelAndView("redirect:index.action");
			}

		mav.addObject("nickName", nickName);
		mav.addObject("email", email);
		mav.addObject("password", password);
		mav.addObject("retypePassword", retypePassword);
		mav.addObject("isEmailExists", isEmailExists);
		mav.addObject("isNickName", isNickNameExists);
		mav.addObject("isPasswordValid", isPasswordValid);

		return mav;
	}
	
	@RequestMapping(value = "/email.action", method = RequestMethod.GET)
    public void getEmail(HttpServletResponse response, @RequestParam(value = "email", required = true) String email) throws IOException {

        boolean isEmail = userDbService.isEmailExists(email);
        response.getWriter().print(isEmail);
    }
	
	@RequestMapping(value = "/nickname.action", method = RequestMethod.GET)
    public void getNickName(HttpServletResponse response, @RequestParam(value = "nickname", required = true) String nickName) throws IOException {

        boolean isNickName = userDbService.isNickNameExists(nickName);
        response.getWriter().print(isNickName);
    }
}
