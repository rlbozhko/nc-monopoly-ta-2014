package com.monopoly.action.controller;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.action.Action;
import com.monopoly.action.BetrayalActioin;
import com.monopoly.action.BuildAction;
import com.monopoly.action.BuyPropertyAction;
import com.monopoly.action.DealAction;
import com.monopoly.action.EndTurnAction;
import com.monopoly.action.FinishGameAction;
import com.monopoly.action.PayBackAction;
import com.monopoly.action.PayRentAction;
import com.monopoly.action.PledgePropertyAction;
import com.monopoly.action.SellBuildingLevelAction;
import com.monopoly.action.StartTurnAction;
import com.monopoly.action.UpgradeBuildingAction;
import com.monopoly.action.WaitAction;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;

/**
 * Created by Roma on 20.11.2014.
 */
public class PlayerActionController implements ActionController {
	private Session session;
	private PropertyManager propertyManager;

	@Override
	public List<Action> getAvailableActions(Player player) {
		session = GameSession.getInstance();
		propertyManager = session.getPropertyManager();

		List<Action> result = new ArrayList<>();
		if (Status.FINISH == player.getStatus()) {
			return result;
		}

		if (!player.isOfferADeal()) {
			result.add(new DealAction());
		}
		result.add(new FinishGameAction());
		result.add(new WaitAction());
		
		if (player.hasProperty()) {
			result.add(new PledgePropertyAction());
			
			if (player.hasPledgedProperty()) {
				result.add(new PayBackAction());
			}
		}

		if (player.isJailed()) {
			result.addAll(new JailActionController().getAvailableActions(player));
			return result;
		}

		if (player.getCurrentCell().hasEscapedPlayers()) {
			result.add(new BetrayalActioin());
		}

		if (player.isPayRent()) {
			result.add(new PayRentAction());
		}

		if (hasMonopoly(player)) {
			result.add(new BuildAction());
		}
		
		if (hasBuildigs(player)) {
			result.add(new UpgradeBuildingAction());
			result.add(new SellBuildingLevelAction());
		}

		Cell cell = player.getCurrentCell();
		if (CellType.PROPERTY_CELL == cell.getCellType()) {
			PropertyCell propertyCell = (PropertyCell) cell;
			if (propertyManager.getPropertyOwner(propertyCell) == null) {
				result.add(new BuyPropertyAction());
			}
		}

		if (Status.START_TURN == player.getStatus()) {
			result.add(new StartTurnAction());
		} else if (Status.ACTIVE == player.getStatus()) {
			result.add(new EndTurnAction());
		}

		return result;
	}

	private boolean hasMonopoly(Player player) {
		for (Property property : propertyManager.getPlayerProperties(player)) {
			if (property.getMonopoly().hasSameOwner(player)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasBuildigs(Player player) {
		for (Property property : propertyManager.getPlayerProperties(player)) {
			if (property.hasBuilding()) {
				return true;
			}
		}
		return false;
	}

}
