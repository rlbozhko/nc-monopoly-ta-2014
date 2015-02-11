package com.monopoly.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.monopoly.action.ActionType;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.deal.Deal;
import com.monopoly.action.deal.DealContainer;
import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.SessionStatus;
import com.monopoly.io.IO;
import com.monopoly.io.WebIO.SelectPropertyHelper;
import com.monopoly.io.WebIO.YesNoDialog;
import com.monopoly.services.UserDbService;

@Controller
public class GameController {
	@Autowired
	private UserDbService userService;

	private YesNoDialog yesNoDialog;

	// private UserService userService;

	@RequestMapping(value = "/game.action", method = RequestMethod.GET)
	public ModelAndView getLogin(
			@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game");

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			return new ModelAndView("redirect:index.action");
		}

		Session gameSession = GameSession.getInstance();

		List<Cell> cellsList = gameSession.getBoard().getCells();
		Board board = gameSession.getBoard();

		List<Player> players = board.getPlayers();
		List<Player> activePlayers = board.getActivePlayers();

		if (gameSession.getUserIO(user) == null) {
			mav.addObject("players", players);
			mav.addObject("activePlayers", activePlayers);
			mav.addObject("cellsList", cellsList);
			return mav;
		}

		List<ActionType> actions = gameSession.getActionController()
				.getAvailableActions(gameSession.getUserIO(user).getOwner());

		List<String> stringActions = new ArrayList<>();
		for (ActionType actionType : actions) {
			stringActions.add(actionType.toString());
		}

		PropertyManager propertyManager = gameSession.getPropertyManager();
		
		IO io = gameSession.getUserIO(user);
		System.out.println("io.hasSelectPlayerRequest() = "
				+ io.hasSelectPlayerRequest());

		//testProperty();
		
		if (io.hasYesNoDialog()) {
			yesNoDialog = io.getYesNoDialog();
			mav.addObject("hasYesNoDialog", io.hasYesNoDialog());
			mav.addObject("yesNoDialog", yesNoDialog.getMessage());
		}

		if (io.hasSelectPlayerRequest()) {
			board = gameSession.getBoard();
			List<Player> selectabelPlayers = board.getActivePlayers();
			selectabelPlayers.remove(io.getOwner());
			mav.addObject("selectabelPlayers", selectabelPlayers);
			mav.addObject("selectPlayerRequest", true);
		}

		System.out.println("io.hasCreateDealRequest() = "
				+ io.hasCreateDealRequest());
		if (io.hasCreateDealRequest()) {
			Player target = io.getDealTarget();
			mav.addObject("targetPlayer", target.getName());
			mav.addObject("targetProperty",
					propertyManager.getPlayerProperties(target));
			mav.addObject("sourceProperty",
					propertyManager.getPlayerProperties(io.getOwner()));
			mav.addObject("dealRequest", true);
		}

		System.out.println("io.hasSelectPropertyRequest() = "
				+ io.hasSelectPropertyRequest());
		if (io.hasSelectPropertyRequest()) {
			
			List<Property> propertyList= propertyManager.getPlayerProperties(io.getOwner());
			mav.addObject("hasSelectPropertyRequest", io.hasSelectPropertyRequest());
			mav.addObject("propertyList", propertyList);
		}

		Queue<String> messageQueue = io.getAllMessages();

		mav.addObject("messageQueue", messageQueue);
		mav.addObject("players", players);
		mav.addObject("activePlayers", activePlayers);
		mav.addObject("propertyManager", propertyManager);
		mav.addObject("email", email);
		mav.addObject("cellsList", cellsList);
		mav.addObject("strActions", stringActions);

		return mav;
	}
	
	private void testProperty() {
		Session session = GameSession.getInstance();
		PropertyManager testPropertyManager = session.getPropertyManager();

		Property testProperty1 = (Property) session.getBoard().getCells()
				.get(1);
		Property testProperty2 = (Property) session.getBoard().getCells()
				.get(3);
		Property testProperty3 = (Property) session.getBoard().getCells()
				.get(4);
		Property testProperty4 = (Property) session.getBoard().getCells()
				.get(5);
		Property testProperty5 = (Property) session.getBoard().getCells()
				.get(6);
		Property testProperty6 = (Property) session.getBoard().getCells()
				.get(9);
		Property testProperty7 = (Property) session.getBoard().getCells()
				.get(11);
		Property testProperty8 = (Property) session.getBoard().getCells()
				.get(12);
		Property testProperty9 = (Property) session.getBoard().getCells()
				.get(7);

//		Player p1 = session.getBoard().getPlayers().get(0);
		Player p2 = session.getBoard().getPlayers().get(1);

		testPropertyManager.setPropertyOwner(p2, testProperty1);
		testPropertyManager.setPropertyOwner(p2, testProperty2);
		testPropertyManager.setPropertyOwner(p2, testProperty3);
		testPropertyManager.setPropertyOwner(p2, testProperty4);
		testPropertyManager.setPropertyOwner(p2, testProperty5);
		testPropertyManager.setPropertyOwner(p2, testProperty6);
		testPropertyManager.setPropertyOwner(p2, testProperty7);
		testPropertyManager.setPropertyOwner(p2, testProperty8);
		testPropertyManager.setPropertyOwner(p2, testProperty9);
	}

	@RequestMapping(value = "/action_type.action", method = RequestMethod.GET, params = { "actionType" })
	public ModelAndView clickAction(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam String actionType) {

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus == SessionStatus.NOT_EXISTS) {
			return new ModelAndView("redirect:index.action");
		}

		if (sessionStatus == SessionStatus.CREATING) {
			return new ModelAndView("redirect:join_game.action");
		}

		IO io = GameSession.getInstance().getUserIO(user);
		io.performAction(ActionType.valueOf(actionType));

		System.out.println("perform " + actionType);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("clickACtion() -- io.hasSelectPlayerRequest() = "
				+ io.hasSelectPlayerRequest());
		return new ModelAndView("redirect:game.action");
	}

	@RequestMapping(value = "/select_player.action", method = RequestMethod.GET, params = "selectedPlayerName")
	public ModelAndView selectPlayer(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam String selectedPlayerName) {
		System.out.println("inside selectPlayer()");
	
		User user = userService.getUser(hash);
	
		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
	
		IO io = GameSession.getInstance().getUserIO(user);
		io.setSelectedPlayer(ActionUtils.getPlayerByName(selectedPlayerName));
		System.out.println(selectedPlayerName);
		return new ModelAndView("redirect:game.action");
	}	

	@RequestMapping(value = "/deal_target.action", method = RequestMethod.GET, params = { "dealTargetName" })
	public ModelAndView createDeal(
			@CookieValue(value = "bb_data", required = false) String hash,
			@ModelAttribute DealContainer dealContainer,
			@RequestParam String dealTargetName) {

		System.out.println("inside createDeal");
		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		IO io = GameSession.getInstance().getUserIO(user);

		dealContainer.setTarget(ActionUtils.getPlayerByName(dealTargetName));
		dealContainer.setSource(io.getOwner());
		Deal deal = dealContainer.createDeal();
		io.setCreatedDeal(deal);

		System.out.println(deal.message());

		return new ModelAndView("redirect:game.action");
	}

	@RequestMapping(value = "/game_over.action", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		if (GameSession.getInstance().getBoard().getActivePlayers().size() == 0) {
			GameSession.getInstance().close();
			return new ModelAndView("redirect:index.action");
		}

		return new ModelAndView("redirect:index.action");
	}

	@RequestMapping(value = "/dialog.action", method = RequestMethod.GET)
	public ModelAndView getAnwser(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "isAnswer", required = false, defaultValue = "false") Boolean isAnswer) {

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		IO io = GameSession.getInstance().getUserIO(user);
		yesNoDialog = io.getYesNoDialog();
		yesNoDialog.setAnswer(isAnswer);

		return new ModelAndView("redirect:game.action");
	}
	
	@RequestMapping(value = "/pledge_property.action", method = RequestMethod.GET)
	public ModelAndView selectProperty(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "propertyId", required = false) Integer propertyId) {
		
		System.out.println("inside selectPlayer()");

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		
		System.out.println(propertyId);
		IO io = GameSession.getInstance().getUserIO(user);
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		Property property = propertyManager.getPlayerProperties(io.getOwner()).get(propertyId);
		SelectPropertyHelper selectPropertyHelper = io.getSelectPropertyHelper();
		selectPropertyHelper.setProperty(property);
		
		return new ModelAndView("redirect:game.action");
	}

}
