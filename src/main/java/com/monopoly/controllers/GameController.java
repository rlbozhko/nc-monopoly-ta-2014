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
import com.monopoly.services.GameService;
import com.monopoly.services.UserDbService;

@Controller
public class GameController {
	@Autowired
	private UserDbService userService;
	@Autowired
	private GameService gameService;

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
		IO io = gameSession.getUserIO(user);
		
		List<Cell> cellsList = gameSession.getBoard().getCells();
		Board board = gameSession.getBoard();

		List<Player> players = board.getPlayers();
		List<Player> activePlayers = board.getActivePlayers();

		if (gameSession.getUserIO(user) == null) {
			mav.addObject("players", players);
			mav.addObject("activePlayers", activePlayers);
			mav.addObject("cellsList", cellsList);
			mav.addObject("email", email);
			return mav;
		}
		
		Player player = io.getOwner();
		
		List<ActionType> actions = gameSession.getActionController()
				.getAvailableActions(player);

		List<String> stringActions = new ArrayList<>();
		for (ActionType actionType : actions) {
			stringActions.add(actionType.toString());
		}

		PropertyManager propertyManager = gameSession.getPropertyManager();
		
		//TODO
//		testProperty();
		
		if (io.hasYesNoDialog()) {
			yesNoDialog = io.getYesNoDialog();
			mav.addObject("hasYesNoDialog", io.hasYesNoDialog());
			mav.addObject("yesNoDialog", yesNoDialog.getMessage());
		}

		if (io.hasSelectPlayerRequest()) {
			board = gameSession.getBoard();
			List<Player> selectabelPlayers = board.getActivePlayers();
			selectabelPlayers.remove(player);
			mav.addObject("selectabelPlayers", selectabelPlayers);
			mav.addObject("selectPlayerRequest", true);
		}

		if (io.hasCreateDealRequest()) {
			Player target = io.getDealTarget();
			mav.addObject("targetPlayer", target.getName());
			mav.addObject("targetProperty",
					propertyManager.getPlayerProperties(target));
			mav.addObject("sourceProperty",
					propertyManager.getPlayerProperties(player));
			mav.addObject("dealRequest", true);
		}

		if (io.hasSelectPropertyRequest()) {
			List<Property> propertyList= propertyManager.getPlayerProperties(player);
			mav.addObject("hasSelectPropertyRequest", io.hasSelectPropertyRequest());
			mav.addObject("propertyList", propertyList);
		}
		
		//TODO
		if (player.hasPledgedProperty()) {
			List<Property> propertyList= propertyManager.getPlayerProperties(player);
			List<Property> pledgedPropertiesList = gameService.getPledgedProperty(propertyList);
			
			mav.addObject("pledgedPropertiesList", pledgedPropertiesList);
		}
		

		Queue<String> messageQueue = io.getAllMessages();

		mav.addObject("messageQueue", messageQueue);
		mav.addObject("players", players);
		mav.addObject("player", player);
		mav.addObject("activePlayers", activePlayers);
		mav.addObject("propertyManager", propertyManager);
		mav.addObject("email", email);
		mav.addObject("cellsList", cellsList);
		mav.addObject("strActions", stringActions);
		mav.addObject("isJailed", player.isJailed());
		mav.addObject("isWarning", io.hasWarning());
		mav.addObject("warningMessage", io.getWarning());

		return mav;
	}
	
	public static void testProperty() {
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
		Property testProperty10 = (Property) session.getBoard().getCells()
				.get(14);
		Property testProperty11 = (Property) session.getBoard().getCells()
				.get(15);
		Property testProperty12 = (Property) session.getBoard().getCells()
				.get(17);
		Property testProperty13 = (Property) session.getBoard().getCells()
				.get(19);
		Property testProperty14 = (Property) session.getBoard().getCells()
				.get(21);
		Property testProperty15 = (Property) session.getBoard().getCells()
				.get(23);
		Property testProperty16 = (Property) session.getBoard().getCells()
				.get(25);
		Property testProperty17 = (Property) session.getBoard().getCells()
				.get(26);
		Property testProperty18 = (Property) session.getBoard().getCells()
				.get(28);
		Property testProperty19 = (Property) session.getBoard().getCells()
				.get(29);
		Property testProperty20 = (Property) session.getBoard().getCells()
				.get(31);
		Property testProperty21 = (Property) session.getBoard().getCells()
				.get(33);
		Property testProperty22 = (Property) session.getBoard().getCells()
				.get(34);
		Property testProperty23 = (Property) session.getBoard().getCells()
				.get(35);
		Property testProperty24 = (Property) session.getBoard().getCells()
				.get(36);
		Property testProperty25 = (Property) session.getBoard().getCells()
				.get(38);
		Property testProperty26 = (Property) session.getBoard().getCells()
				.get(39);
		
		

		Player p1 = session.getBoard().getPlayers().get(0);
		Player p2 = session.getBoard().getPlayers().get(1);

		testPropertyManager.setPropertyOwner(p2, testProperty1);
		testPropertyManager.setPropertyOwner(p2, testProperty2);
		testPropertyManager.setPropertyOwner(p2, testProperty3);
		testPropertyManager.setPropertyOwner(p2, testProperty4);
		testPropertyManager.setPropertyOwner(p1, testProperty5);
		testPropertyManager.setPropertyOwner(p1, testProperty6);
		testPropertyManager.setPropertyOwner(p1, testProperty7);
		testPropertyManager.setPropertyOwner(p1, testProperty8);
		testPropertyManager.setPropertyOwner(p1, testProperty9);
		testPropertyManager.setPropertyOwner(p1, testProperty10);
		testPropertyManager.setPropertyOwner(p1, testProperty11);
		testPropertyManager.setPropertyOwner(p1, testProperty12);
		testPropertyManager.setPropertyOwner(p1, testProperty13);
		testPropertyManager.setPropertyOwner(p1, testProperty14);
		testPropertyManager.setPropertyOwner(p2, testProperty15);
		testPropertyManager.setPropertyOwner(p2, testProperty16);
		testPropertyManager.setPropertyOwner(p2, testProperty17);
		testPropertyManager.setPropertyOwner(p2, testProperty18);
		testPropertyManager.setPropertyOwner(p2, testProperty19);
		testPropertyManager.setPropertyOwner(p2, testProperty20);
		testPropertyManager.setPropertyOwner(p2, testProperty21);
		testPropertyManager.setPropertyOwner(p2, testProperty22);
		testPropertyManager.setPropertyOwner(p2, testProperty23);
		testPropertyManager.setPropertyOwner(p2, testProperty24);
		testPropertyManager.setPropertyOwner(p2, testProperty25);
		testPropertyManager.setPropertyOwner(p2, testProperty26);
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
		
		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			return new ModelAndView("redirect:index.action");
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
		
		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			return new ModelAndView("redirect:index.action");
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
		
		if (GameSession.getStatus() == SessionStatus.RUN && GameSession.getInstance().getBoard().getActivePlayers().size() == 0) {
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
		
		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			return new ModelAndView("redirect:index.action");
		}

		IO io = GameSession.getInstance().getUserIO(user);
		yesNoDialog = io.getYesNoDialog();
		yesNoDialog.setAnswer(isAnswer);

		return new ModelAndView("redirect:game.action");
	}
	
	@RequestMapping(value = "/pledge_property.action", method = RequestMethod.GET)
	public ModelAndView selectPropertyPledge(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "propertyId", required = false) Integer propertyId) {
		
		System.out.println("inside selectPlayer()");

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		
		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			return new ModelAndView("redirect:index.action");
		}
		
		System.out.println(propertyId);
		IO io = GameSession.getInstance().getUserIO(user);
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		Property property = propertyManager.getPlayerProperties(io.getOwner()).get(propertyId);
		SelectPropertyHelper selectPropertyHelper = io.getSelectPropertyHelper();
		selectPropertyHelper.setProperty(property);
		
		return new ModelAndView("redirect:game.action");
	}
	
	@RequestMapping(value = "/pay_back.action", method = RequestMethod.GET)
	public ModelAndView selectPropertyUnpledge(
			@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "propertyId", required = false) Integer propertyId) {
		
		System.out.println("inside selectPlayer()");

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}
		
		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			return new ModelAndView("redirect:index.action");
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
