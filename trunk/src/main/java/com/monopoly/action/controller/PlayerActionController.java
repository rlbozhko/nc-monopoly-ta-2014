package com.monopoly.action.controller;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.action.ActionType;
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
	public List<ActionType> getAvailableActions(Player player) {
		session = GameSession.getInstance();
		propertyManager = session.getPropertyManager();

		List<ActionType> result = new ArrayList<>();
		if (Status.FINISH == player.getStatus()) {
			return result;
		}

		if (!player.isOfferADeal()) {
			result.add(ActionType.DEAL);
		}
		result.add(ActionType.FINISH_GAME);
		result.add(ActionType.WAIT);

		if (player.hasProperty()) {
			result.add(ActionType.PLEDGE_PROPERTY);

			if (player.hasPledgedProperty()) {
				result.add(ActionType.PAY_BACK);
			}
		}

		if (player.isJailed()) {
			result.addAll(new JailActionController().getAvailableActions(player));
			return result;
		}

		if (player.getJailStatus() != Status.ESCAPE && player.getCurrentCell().hasEscapedPlayers()) {
			result.add(ActionType.BETRAYAL);
		}

		if (player.isPayRent()) {
			result.add(ActionType.PAY_RENT);
		}

		if (hasMonopoly(player)) {
			result.add(ActionType.BUILD);
		}

		if (hasBuildigs(player)) {
			result.add(ActionType.UPGRADE_BUILDING);
			result.add(ActionType.SELL_BUILDING);
		}

		Cell cell = player.getCurrentCell();
		if (CellType.PROPERTY_CELL == cell.getCellType()) {
			PropertyCell propertyCell = (PropertyCell) cell;
			if (propertyManager.getPropertyOwner(propertyCell) == null) {
				result.add(ActionType.BUY_PROPERTY);
			}
		}

		if (Status.START_TURN == player.getStatus()) {
			result.add(ActionType.START_TURN);
		} else if (Status.ACTIVE == player.getStatus()) {
			result.add(ActionType.END_TURN);
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
