package com.monopoly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.bean.User;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.SessionStatus;
import com.monopoly.services.UserDbService;
import com.monopoly.services.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserDbService userService;
//	private UserService userService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public ModelAndView getIndex(
			@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("index");

		User user = userService.getUser(hash);
		
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		
		String email = user.getEmail();
		
		mav.addObject("email", email);

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus == SessionStatus.CREATING) {
			return new ModelAndView("redirect:join_game.action");
		}
		
		if (sessionStatus == SessionStatus.RUN) {
			return new ModelAndView("redirect:game_test.action");
		}
		
		return mav;
	}
}
