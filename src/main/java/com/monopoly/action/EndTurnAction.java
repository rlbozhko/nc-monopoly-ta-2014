package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class EndTurnAction implements Action {

	private Board board;

	public EndTurnAction() {
		this.board = GameSession.getInstance().getBoard();
	}

	@Override
	public void performAction(Player player) {
		IO playerIO = ActionUtils.getPlayerIO(player);

		if (player.isPayRent()) {
			playerIO.showMessage("Для завершения хода уплатите аренду");
		} else {
			player.setStatus(Status.WAIT);
			board.getNextPlayer().setStatus(Status.START_TURN);
			checkAndStartAuction(player);
		}
	}

	private void checkAndStartAuction(Player player) {
		if (player.hasPledgedProperty()) {
			PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
			List<Property> auctionProperty = new ArrayList<>();
			for (Property property : propertyManager.getPlayerProperties(player)) {
				if (isСonfiscateProperty(property)) {
					auctionProperty.add(property);
					propertyManager.resetPropertyOwner(property);
				}
			}			
			for (Property property : auctionProperty) {
				new AuctionAction(property).performAction(player);
			}
		}
	}

	private boolean isСonfiscateProperty(Property property) {
		return property.isPledged() && 0 == property.getTurnsToPayBack();
	}

	@Override
	public String getName() {
		return "End Turn";
	}
}
