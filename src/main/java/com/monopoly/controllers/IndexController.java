package com.monopoly.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.GameSession.GameSessionBuilder;
import com.monopoly.game.session.SessionStatus;
import com.monopoly.io.IO;
import com.monopoly.io.WebIO;
import com.monopoly.services.IndexService;
import com.monopoly.services.UserService;

@Controller
public class IndexController {

	private static final int START_MONEY = 5000;
	@Autowired
	private IndexService indexService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String getIndex(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam(value = "isCreator", required = false, defaultValue = "false") Boolean isCreator, 
			RedirectAttributes attributes) {		
				
		if (countPlayers != null) {
			attributes.addAttribute("countPlayers", countPlayers);
		}
		if (sessionStatusText != null) {
			attributes.addAttribute("sessionStatusText", sessionStatusText);
		}
		if (isJoinToGame != null) {
			attributes.addAttribute("isJoinToGame", isJoinToGame);
		}
		if (playerName != null) {
			attributes.addAttribute("playerName", playerName);
		}
		if (isCreator != null) {
			attributes.addAttribute("isCreator", isCreator);
		}	
				
		System.out.println("----------- index.action -----------");
		System.out.println("hash = " + hash);
		System.out.println("countPlayers = " + countPlayers);
		System.out.println("sessionStatusText = " + sessionStatusText);
		System.out.println("isJoinToGame = " + isJoinToGame);
		System.out.println("playerName = " + playerName);
		System.out.println("isCreator = " + isCreator);
		System.out.println("model = " + attributes);
		
		User user = userService.getUser(hash);
		if (user == null) {
			return "redirect:signin.action";
		}

		SessionStatus sessionStatus = indexService.getStatus();
		System.out.println("sessionStatus : " + sessionStatus);

		if (sessionStatusText != null) {
			indexService.setStatus(sessionStatusText);
			sessionStatus = indexService.getStatus();
		}

		if (sessionStatus == SessionStatus.NOT_EXISTS || isCreator) {
			return "redirect:createGame.action";
		} else if (sessionStatus == SessionStatus.CREATING) {
			if (!isJoinToGame) {
				return "redirect:joinGame.action";
			} else {
				return "redirect:waitGame.action";
			}
		} else if (isJoinToGame) {
			return "redirect:game.action";
		} else {
			return "redirect:serverIsFull.action";
		}
	}

	@RequestMapping(value = "/createGame.action", method = RequestMethod.GET)
	public ModelAndView getCreateGame(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam(value = "isCreator", required = false, defaultValue = "false") Boolean isCreator) {
		
		ModelAndView mav = new ModelAndView("createGame");

		System.out.println("----------- createGame.action -----------");
		System.out.println("hash = " + hash);
		System.out.println("countPlayers = " + countPlayers);
		System.out.println("sessionStatusText = " + sessionStatusText);
		System.out.println("isJoinToGame = " + isJoinToGame);
		System.out.println("playerName = " + playerName);
		System.out.println("isCreator = " + isCreator);	

		User user = userService.getUser(hash);
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();
		mav.addObject("email", email);

		SessionStatus sessionStatus = indexService.getStatus();

		if (sessionStatusText != null) {
			indexService.setStatus(sessionStatusText);
			sessionStatus = indexService.getStatus();
		}
		mav.addObject("sessionStatus", sessionStatus);

		if (countPlayers != null && isCreator == true && indexService.getMaxPlayers() == 0) {

			Player player = new Player(playerName);
			user.setPlayer(player);
			IO io = new WebIO(player);

			Map<User, IO> usersIO = new HashMap<User, IO>();
			usersIO.put(user, io);
			GameSessionBuilder.setUsersIO(usersIO);

			List<IO> ios = new ArrayList<IO>();
			ios.add(io);
			GameSessionBuilder.setIOs(ios);

			List<Player> players = new ArrayList<Player>(countPlayers);
			players.add(player);
			indexService.setMaxPlayers(countPlayers);

			Board board = GameSession.newBoard(players, START_MONEY);
			GameSessionBuilder.setBoard(board);

			System.out.println(players);
			System.out.println(ios);
			System.out.println(board);
			System.out.println("maxPlayers :" + indexService.getMaxPlayers());
			System.out.println("PlayersCount :" + indexService.getPlayersCount());
			
			ModelAndView indexMav = new ModelAndView("redirect:index.action");
			
			indexMav.addObject("countPlayers", countPlayers);
			indexMav.addObject("email", email);
			indexMav.addObject("sessionStatus", sessionStatus);
			indexMav.addObject("isJoinToGame", isJoinToGame);			
			indexMav.addObject("playerName", playerName);

			return indexMav;
		} else if (indexService.getMaxPlayers() != 0) {
			return new ModelAndView("redirect:index.action");
		}
		
		return mav;
	}

	@RequestMapping(value = "/waitGame.action", method = RequestMethod.GET)
	public ModelAndView getWaitGame(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam(value = "isCreator", required = false, defaultValue = "false") Boolean isCreator) {
		ModelAndView mav = new ModelAndView("waitGame");

		System.out.println("----------- waitGame.action -----------");
		System.out.println("hash = " + hash);
		System.out.println("countPlayers = " + countPlayers);
		System.out.println("sessionStatusText = " + sessionStatusText);
		System.out.println("isJoinToGame = " + isJoinToGame);
		System.out.println("playerName = " + playerName);
		System.out.println("isCreator = " + isCreator);

		User user = userService.getUser(hash);
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();
		mav.addObject("email", email);

		SessionStatus sessionStatus = indexService.getStatus();

		if (sessionStatusText != null) {
			indexService.setStatus(sessionStatusText);
			sessionStatus = indexService.getStatus();
		}
		mav.addObject("sessionStatus", sessionStatus);

		Board board = GameSessionBuilder.getBoard();
		System.out.println("wait.action " + board);
		List<Player> players = board.getPlayers();
		List<Cell> cellsList = board.getCells();

		mav.addObject("cellsList", cellsList);
		mav.addObject("players", players);

		return mav;
	}
	
	@RequestMapping(value = "/joinGame.action", method = RequestMethod.GET)
	public ModelAndView getJoinGame(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam(value = "isCreator", required = false, defaultValue = "false") Boolean isCreator) {
		ModelAndView mav = new ModelAndView("joinGame");

		System.out.println("----------- joinGame.action -----------");
		System.out.println("hash = " + hash);
		System.out.println("countPlayers = " + countPlayers);
		System.out.println("sessionStatusText = " + sessionStatusText);
		System.out.println("isJoinToGame = " + isJoinToGame);
		System.out.println("playerName = " + playerName);
		System.out.println("isCreator = " + isCreator);

		User user = userService.getUser(hash);
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();
		mav.addObject("email", email);

		SessionStatus sessionStatus = indexService.getStatus();

		if (sessionStatusText != null) {
			indexService.setStatus(sessionStatusText);
			sessionStatus = indexService.getStatus();
		}		
		mav.addObject("sessionStatus", sessionStatus);		
		
		if (SessionStatus.CREATING == sessionStatus && isJoinToGame 
				&& indexService.getMaxPlayers() > indexService.getPlayersCount()) {			
			Player player = new Player(playerName);
			user.setPlayer(player);
			IO io = new WebIO(player);

			Map<User, IO> usersIO = GameSessionBuilder.getUsersIO();
			usersIO.put(user, io);			

			List<IO> ios = GameSessionBuilder.getIos();
			ios.add(io);			

			List<Player> players = GameSessionBuilder.getBoard().getPlayers();
			players.add(player);			
			
			System.out.println("insideJoinGame");
			System.out.println(players);
			System.out.println(ios);
			
			ModelAndView indexMav = new ModelAndView("redirect:index.action");
			
			if (indexService.getMaxPlayers() == indexService.getPlayersCount()) {
				GameSession.setStatus(SessionStatus.RUN);
			}
			
			indexMav.addObject("email", email);			
			indexMav.addObject("isJoinToGame", isJoinToGame);			
			indexMav.addObject("playerName", playerName);
			
			return indexMav;
		} else if (indexService.getMaxPlayers() <= indexService.getPlayersCount()) {
			System.out.println("maxPlayers :" + indexService.getMaxPlayers());
			System.out.println("PlayersCount :" + indexService.getPlayersCount());
			
			ModelAndView serveIsFull = new ModelAndView("serverIsFull.jsp");
			serveIsFull.addObject("email", email);
			serveIsFull.addObject("sessionStatus", sessionStatus);
			return serveIsFull;
		}

		return mav;
	}	
	
	@RequestMapping(value = "/game.action", method = RequestMethod.GET)
	public ModelAndView getGame(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam(value = "isCreator", required = false, defaultValue = "false") Boolean isCreator) {
		ModelAndView mav = new ModelAndView("game");

		System.out.println("----------- game.action -----------");
		System.out.println("hash = " + hash);
		System.out.println("countPlayers = " + countPlayers);
		System.out.println("sessionStatusText = " + sessionStatusText);
		System.out.println("isJoinToGame = " + isJoinToGame);
		System.out.println("playerName = " + playerName);
		System.out.println("isCreator = " + isCreator);

		User user = userService.getUser(hash);
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();
		mav.addObject("email", email);

		SessionStatus sessionStatus = indexService.getStatus();

		if (sessionStatusText != null) {
			indexService.setStatus(sessionStatusText);
			sessionStatus = indexService.getStatus();
		}
		mav.addObject("sessionStatus", sessionStatus);

		Board board = GameSessionBuilder.getBoard();
		System.out.println("game.action " + board);
		List<Player> players = board.getPlayers();
		List<Cell> cellsList = board.getCells();

		mav.addObject("cellsList", cellsList);
		mav.addObject("players", players);

		return mav;
	}
	
	@RequestMapping(value = "/serverIsFull.action", method = RequestMethod.GET)
	public ModelAndView getServerIsFull(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "countPlayers", required = false) Integer countPlayers,
			@RequestParam(value = "sessionStatusText", required = false) String sessionStatusText,
			@RequestParam(value = "isJoinToGame", required = false, defaultValue = "false") Boolean isJoinToGame,
			@RequestParam(value = "playerName", required = false) String playerName,
			@RequestParam(value = "isCreator", required = false, defaultValue = "false") Boolean isCreator) {
		ModelAndView mav = new ModelAndView("serverIsFull");

		System.out.println("----------- serverIsFull.action -----------");
		System.out.println("hash = " + hash);
		System.out.println("countPlayers = " + countPlayers);
		System.out.println("sessionStatusText = " + sessionStatusText);
		System.out.println("isJoinToGame = " + isJoinToGame);
		System.out.println("playerName = " + playerName);
		System.out.println("isCreator = " + isCreator);

		User user = userService.getUser(hash);
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();
		mav.addObject("email", email);

		SessionStatus sessionStatus = indexService.getStatus();		
		mav.addObject("sessionStatus", sessionStatus);
		
		return mav;
	}	
}
