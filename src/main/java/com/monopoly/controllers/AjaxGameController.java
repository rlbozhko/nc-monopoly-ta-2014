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
import org.springframework.web.bind.annotation.ResponseBody;
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
public class AjaxGameController {
	@Autowired
	private UserDbService userService;
	@Autowired
	private GameService gameService;

	private YesNoDialog yesNoDialog;

	@RequestMapping(value = "/game_test.action", method = RequestMethod.GET)
	public ModelAndView getWindow(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_test");

		User user = userService.getUser(hash);

		if (user == null) {
			return new ModelAndView("redirect:signin.action");
		}

		String email = user.getEmail();

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			return new ModelAndView("redirect:index.action");
		}

		mav.addObject("email", email);

		return mav;
	}

	@RequestMapping(value = "/game_buttons.action", method = RequestMethod.GET)
	public ModelAndView buttons(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_buttons");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		String email = user.getEmail();

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
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

		List<ActionType> actions = gameSession.getActionController().getAvailableActions(player);

		List<String> stringActions = new ArrayList<>();
		for (ActionType actionType : actions) {
			stringActions.add(actionType.toString());
		}

		mav.addObject("player", player);
		mav.addObject("activePlayers", activePlayers);
		mav.addObject("email", email);
		mav.addObject("strActions", stringActions);
		return mav;
	}

	@RequestMapping(value = "/game_chat.action", method = RequestMethod.GET)
	public ModelAndView chat(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_chat");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}
		
		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();
		IO io = gameSession.getUserIO(user);
		
		if (io == null) {
			throw new NoContentException();
		}

		Queue<String> messageQueue = io.getAllMessages();

		mav.addObject("messageQueue", messageQueue);

		return mav;
	}

	@RequestMapping(value = "/game_board.action", method = RequestMethod.GET)
	public ModelAndView board(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_board");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();
		
		List<Cell> cellsList = gameSession.getBoard().getCells();
		Board board = gameSession.getBoard();
		
		List<Player> activePlayers = board.getActivePlayers();
		
		mav.addObject("activePlayers", activePlayers);
		mav.addObject("cellsList", cellsList);

		return mav;
	}

	@RequestMapping(value = "/game_player_info.action", method = RequestMethod.GET)
	public ModelAndView playerInfo(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_player_info");
		User user = userService.getUser(hash);
		if (user == null) {
			throw new NoContentException();
		}
		SessionStatus sessionStatus = GameSession.getStatus();
		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();

		IO io = gameSession.getUserIO(user);
		Board board = gameSession.getBoard();
		List<Cell> cellsList = board.getCells();
		PropertyManager propertyManager = gameSession.getPropertyManager();
		
		List<Player> players = new ArrayList<Player>();
		if (io == null) {
			players = board.getPlayers();
		} else {
			players.add(io.getOwner());
			List<Player> otherPlayers = new ArrayList<Player>(board.getPlayers());
			otherPlayers.remove(io.getOwner());
			players.addAll(otherPlayers);
		}

		mav.addObject("players", players);
		mav.addObject("cellsList", cellsList);
		mav.addObject("propertyManager", propertyManager);

		return mav;
	}

	@RequestMapping(value = "/a_action_type.action", method = RequestMethod.GET, params = { "actionType" })
	public @ResponseBody String clickAction(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam String actionType) {

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		IO io = GameSession.getInstance().getUserIO(user);
		io.performAction(ActionType.valueOf(actionType));

		System.out.println("perform " + actionType);

		System.out.println("clickAction() -- io.hasSelectPlayerRequest() = " + io.hasSelectPlayerRequest());
		return "Ok";
	}

	@RequestMapping(value = "/game_select_player.action", method = RequestMethod.GET)
	public ModelAndView hasSelectPlayerRequest(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_select_player");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}	

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();
		IO io = gameSession.getUserIO(user);

		if (gameSession.getUserIO(user) == null) {
			throw new NoContentException();
		}

		if (io.hasSelectPlayerRequest()) {
			Board board = gameSession.getBoard();
			List<Player> selectabelPlayers = board.getActivePlayers();
			selectabelPlayers.remove(io.getOwner());
			mav.addObject("selectabelPlayers", selectabelPlayers);
		} else {
			throw new NoContentException();
		}
		return mav;
	}

	@RequestMapping(value = "/a_select_player.action", method = RequestMethod.GET, params = "selectedPlayerName")
	public @ResponseBody String selectPlayer(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam String selectedPlayerName,
			@RequestParam(value = "isAccept", required = false, defaultValue = "false") Boolean isAccept) {
		System.out.println("inside selectPlayer()");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		IO io = GameSession.getInstance().getUserIO(user);
		if (isAccept) {
			io.setSelectedPlayer(ActionUtils.getPlayerByName(selectedPlayerName));
		} else {
			io.setSelectedPlayer(ActionUtils.getPlayerByName(null));
		}
		System.out.println(selectedPlayerName);
		return "Ok";
	}

	@RequestMapping(value = "/game_deal.action", method = RequestMethod.GET)
	public ModelAndView hasDeal(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_deal");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}	

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();
		IO io = gameSession.getUserIO(user);

		if (gameSession.getUserIO(user) == null) {
			throw new NoContentException();
		}

		if (io.hasCreateDealRequest()) {
			PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
			Player target = io.getDealTarget();
			mav.addObject("targetPlayer", target.getName());
			mav.addObject("targetProperty", propertyManager.getPlayerProperties(target));
			mav.addObject("sourceProperty", propertyManager.getPlayerProperties(io.getOwner()));
			mav.addObject("dealRequest", true);
		} else {
			throw new NoContentException();
		}
		return mav;
	}

	@RequestMapping(value = "/a_deal_target.action", method = RequestMethod.GET, params = { "dealTargetName" })
	public @ResponseBody String createDeal(@CookieValue(value = "bb_data", required = false) String hash,
			@ModelAttribute DealContainer dealContainer, @RequestParam String dealTargetName,
			@RequestParam(value = "isAccept", required = false, defaultValue = "false") Boolean isAccept) {

		System.out.println("inside createDeal");
		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		IO io = GameSession.getInstance().getUserIO(user);

		Deal deal;
		dealContainer.setTarget(ActionUtils.getPlayerByName(dealTargetName));
		dealContainer.setSource(io.getOwner());
		deal = dealContainer.createDeal();
		if (isAccept) {
			io.setCreatedDeal(deal);
		} else {
			io.setCreatedDeal(null);
		}
		System.out.println("isAccept = " + isAccept);		
		System.out.println(deal.message());

		return "deal";
	}

	@RequestMapping(value = "/a_game_over.action", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		if (GameSession.getInstance().getBoard().getActivePlayers().size() == 0) {
			GameSession.getInstance().close();
			return new ModelAndView("redirect:index.action");
		}

		return new ModelAndView("redirect:index.action");
	}

	@RequestMapping(value = "/game_dialog.action", method = RequestMethod.GET)
	public ModelAndView hasDialog(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_dialog");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}	

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();
		IO io = gameSession.getUserIO(user);

		if (gameSession.getUserIO(user) == null) {
			throw new NoContentException();
		}

		if (io.hasYesNoDialog()) {
			yesNoDialog = io.getYesNoDialog();
			mav.addObject("yesNoDialog", yesNoDialog.getMessage());
		} else {
			throw new NoContentException();
		}
		return mav;
	}

	@RequestMapping(value = "/a_dialog.action", method = RequestMethod.GET)
	public @ResponseBody String getAnwser(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "isAnswer", required = false, defaultValue = "false") Boolean isAnswer) {

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		IO io = GameSession.getInstance().getUserIO(user);
		yesNoDialog = io.getYesNoDialog();
		yesNoDialog.setAnswer(isAnswer);

		return "Ok";
	}

	@RequestMapping(value = "/game_warning.action", method = RequestMethod.GET)
	public ModelAndView hasWarning(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_warning");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();
		IO io = gameSession.getUserIO(user);

		if (io == null) {
			throw new NoContentException();
		}

		if (io.hasWarning()) {
			mav.addObject("warningMessage", io.getWarning());
		} else {
			throw new NoContentException();
		}
		return mav;
	}

	@RequestMapping(value = "/game_select_property.action", method = RequestMethod.GET)
	public ModelAndView hasSelectPropertyRequest(@CookieValue(value = "bb_data", required = false) String hash) {

		ModelAndView mav = new ModelAndView("game_select_property");

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		Session gameSession = GameSession.getInstance();
		IO io = gameSession.getUserIO(user);

		if (gameSession.getUserIO(user) == null) {
			throw new NoContentException();
		}

		if (io.hasSelectPropertyRequest()) {
			SelectPropertyHelper helper = io.getSelectPropertyHelper();
			PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
			List<Property> propertyList = propertyManager.getPlayerProperties(helper.getOwner());
			mav.addObject("propertyList", propertyList);
		} else {
			throw new NoContentException();
		}
		return mav;
	}

	@RequestMapping(value = "/a_select_property.action", method = RequestMethod.GET)
	public @ResponseBody String selectProperty(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "propertyId", required = false) Integer propertyId,
			@RequestParam(value = "isAccept", required = false, defaultValue = "false") Boolean isAccept) {

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NoContentException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus != SessionStatus.RUN) {
			throw new NoContentException();
		}

		System.out.println(propertyId);
		IO io = GameSession.getInstance().getUserIO(user);

		Property property = (Property) GameSession.getInstance().getBoard().getCells().get(propertyId);
		SelectPropertyHelper selectPropertyHelper = io.getSelectPropertyHelper();
		if (isAccept) {
			selectPropertyHelper.setProperty(property);
		} else {
			selectPropertyHelper.setProperty(null);
		}
		System.out.println("clickAction() -- io.hasWarning() = " + io.hasWarning());

		return "ok";
	}

	@RequestMapping(value = "/a_status_check.action", method = RequestMethod.GET)
	public @ResponseBody String getStatus(@CookieValue(value = "bb_data", required = false) String hash,
			@RequestParam(value = "isAnswer", required = false, defaultValue = "false") Boolean isAnswer) {

		User user = userService.getUser(hash);

		if (user == null) {
			throw new NotFoundException();
		}

		SessionStatus sessionStatus = GameSession.getStatus();

		if (sessionStatus == SessionStatus.RUN) {
			return "RUN";
		} else if (sessionStatus == SessionStatus.CREATING) {
			return "CREATING";
		} else {
			return "NOT_EXISTS";
		}
	}
}
