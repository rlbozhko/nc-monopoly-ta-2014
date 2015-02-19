package com.monopoly.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.action.controller.PlayerActionController;
import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.GameSession.GameSessionBuilder;
import com.monopoly.game.session.SessionStatus;
import com.monopoly.io.IO;
import com.monopoly.io.WebIO;
import com.monopoly.services.DbService;
import com.monopoly.services.UserDbService;

@Controller
public class JoinGameController {

	@Autowired
	private UserDbService userService;
	
	@Autowired
	private DbService dbs; 

	@RequestMapping(value = "/join_game.action", method = RequestMethod.POST)
	public ModelAndView postJoinGame(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "startMoney", required = false) Integer startMoney,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame) {
		ModelAndView mav = new ModelAndView("join_game");

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		
		SessionStatus sessionStatus = GameSession.getStatus();
		if (sessionStatus == SessionStatus.CREATING) {
			return new ModelAndView("redirect:join_game.action");
		}

		GameSessionBuilder.setMaxPlayers(countPlayers);
		GameSessionBuilder.setStartMoney(startMoney);

		if (sessionStatusText != null) {
			GameSession.setStatus(SessionStatus.valueOf(sessionStatusText));
			sessionStatus = GameSession.getStatus();
			mav.addObject("sessionStatus", sessionStatus);
		}

		String email = user.getEmail();

		mav.addObject("email", email);

		return mav;
	}

	@RequestMapping(value = "/join_game.action", method = RequestMethod.GET)
	public ModelAndView getJoinGame(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerColor", required = false) String playerColor) {
		ModelAndView mav = new ModelAndView("join_game");

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		
		SessionStatus sessionStatus = GameSession.getStatus();
		
		if (sessionStatusText != null) {
			GameSession.setStatus(SessionStatus.valueOf(sessionStatusText));
			sessionStatus = GameSession.getStatus();
			mav.addObject("sessionStatus", sessionStatus);
		}
		
		if (sessionStatus == SessionStatus.NOT_EXISTS) {
			return new ModelAndView("redirect:index.action");
		}
		
		if (sessionStatus == SessionStatus.RUN) {
			return new ModelAndView("redirect:game_test.action");
		}

		Map<User, IO> usersIO = GameSessionBuilder.getUsersIO();

		if (isJoinToGame) {
			Player player = new Player(user.getNickName());
			IO io = new WebIO(player);
			player.setPlayerColor(playerColor);
			usersIO.put(user, io);
		}

		int maxPlayers = GameSessionBuilder.getMaxPlayers();

		if (usersIO.size() == maxPlayers) {
			List<Player> players = new ArrayList<Player>();

			for (Entry<User, IO> entry : usersIO.entrySet()) {
				players.add(entry.getValue().getOwner());
			}
			players.get(0).setStatus(Status.START_TURN);
//			Board board = GameSession.newBoard(players, GameSessionBuilder.getStartMoney());
			Board board = dbs.newBoard(players, GameSessionBuilder.getStartMoney());
			GameSessionBuilder.setBoard(board);
			GameSessionBuilder.setActionController(new PlayerActionController());
			GameSessionBuilder.setPropertyManager(new PropertyManager(players));
			GameSessionBuilder.setUsersIO(usersIO);
			
			GameSession.setStatus(SessionStatus.RUN);
			GameSession.getInstance().getBoard().getCurrentPlayer().startTimer();
			
//			GameController.testProperty(); //for test
			
			return new ModelAndView("redirect:game_test.action");
		}

		String email = user.getEmail();
		mav.addObject("playerColor", playerColor);
		mav.addObject("usersIO", usersIO);
		mav.addObject("email", email);

		return mav;
	}
	
	@RequestMapping(value = "/join_game_players.action", method = RequestMethod.GET)
	public ModelAndView joinedPlayers(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("join_game_players");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.CREATING) {
			throw new NoContentException();
		}

		Map<User, IO> usersIO = GameSessionBuilder.getUsersIO();
		mav.addObject("usersIO", usersIO);

		return mav;
	}
}
