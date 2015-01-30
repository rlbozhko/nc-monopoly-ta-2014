package com.monopoly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.bean.User;
import com.monopoly.board.cells.Cell;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;
import com.monopoly.services.UserService;

@Controller
public class GameController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/game.action", method = RequestMethod.GET)
	public ModelAndView getLogin(@CookieValue(value = "bb_data", required = false) String hash) {
		
		ModelAndView mav = new ModelAndView("game");
		

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		
		String email = user.getEmail();
		
		Session gameSession = GameSession.getInstance();
		
		List<Cell> cellsList = gameSession.getBoard().getCells();

		mav.addObject("email", email);
		mav.addObject("cellsList", cellsList);

		
		
		
		return mav;
	}
	
}


