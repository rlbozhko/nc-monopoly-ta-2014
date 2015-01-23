package com.monopoly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.SessionStatus;
import com.monopoly.services.IndexService;
import com.monopoly.services.UserService;

@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public ModelAndView getIndex(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerName", required = false) String playerName) {
		ModelAndView mav = new ModelAndView("index");

		User user = userService.getUser(hash);
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();

		SessionStatus sessionStatus = indexService.getStatus();
		
		List<User> usersList = GameSession.usersList;

		if (sessionStatusText != null) {
			indexService.setStatus(sessionStatusText);
			sessionStatus = indexService.getStatus();
		}
		
		if(isJoinToGame) {
			Player player = new Player(playerName);
			user.setPlayer(player);
			usersList.add(user);
		}

		if (countPlayers != null) {
			List<Player> players = indexService.getPlayers(countPlayers);
			Board board = indexService.createBoard(players);
			List<Cell> cellsList = board.getCells();

			mav.addObject("cellsList", cellsList);
			mav.addObject("players", players);
		}


		mav.addObject("email", email);
		mav.addObject("sessionStatus", sessionStatus);
		mav.addObject("isJoinToGame", isJoinToGame);
		mav.addObject("usersList", usersList);
		mav.addObject("playerName", playerName);
		return mav;
	}
}
