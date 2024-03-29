package com.monopoly.action;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;
import com.monopoly.performer.AuctionPerformer;

/**
 * Created by Roma on 20.11.2014.
 */
public class EndTurnAction implements Action {
	public final static ActionType type = ActionType.END_TURN;
	
	private Board board;
	private Player player;
	private IO playerIO;

	public EndTurnAction() {
		this.board = GameSession.getInstance().getBoard();
	}

	@Override
	public void performAction(Player aPlayer) {
		player = aPlayer;		
		playerIO = ActionUtils.getPlayerIO(player);	
		
		if (checkDialogs(playerIO)) {
			return;
		}
		
		if (player.isPayRent()) {
			playerIO.showWarning("Для завершения хода уплатите аренду");
			player.addTime();
		} else if (player.getMoney() <= 0) {
			playerIO.showWarning("С отрицательным балансом на счету нельзя продолжать играть. Пополните свой счет или сдавайтесь");
			player.addTime();
		} else {
			checkForEscape();
			
			if (player.hasExtraTurn()) {
				player.setStatus(Status.START_TURN);
				ActionUtils.sendMessageToAll(player.getName() + " ходит еще раз!");
				player.setExtraTurn(false);
				player.setBuildPerformed(false);
				player.resetTimer();
				player.startTimer();
			} else {
				player.setStatus(Status.WAIT);
				Player nextPlayer = board.getNextPlayer();
				nextPlayer.setStatus(Status.START_TURN);
				ActionUtils.sendMessageToAll(player.getName() + " передал ход " + nextPlayer.getName());
				checkAndStartAuction();
			}
		}
	}
	
	private boolean checkDialogs(IO io) {
		if (io.hasYesNoDialog()) {
			io.showWarning("Чтобы завершить ход ответьте на все запросы!");
			io.getOwner().addTime();
			return true;
		} else {
			return false;
		}	 
	}

	private void checkForEscape() {
		if (player.getJailStatus() == Status.ESCAPE) {
			player.subtractJailTerm();
			if (player.getJailTerm() == 0) {
				player.setJailStatus(Status.CLEAN);				
				ActionUtils.sendMessageToAll(player.getName() + " отсиделся и его больше не разыскивают");
			} else {
				playerIO.showMessage("Ваc будут искать еще " + player.getJailTerm() + " ход(ов)");
			}
		}
	}

	private void checkAndStartAuction() {
		if (player.hasPledgedProperty()) {
			PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
			List<Property> auctionProperty = new ArrayList<>();
			List<Property> playerProperty = new ArrayList<>(propertyManager.getPlayerProperties(player));
			for (Property property : playerProperty) {
				if (isСonfiscateProperty(property)) {
					auctionProperty.add(property);
					propertyManager.resetPropertyOwner(property);
				}
			}
			for (Property property : auctionProperty) {
				new AuctionPerformer(property).perform(player);
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
	
	@Override
	public int hashCode() {		
		return type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {		
		return type.equals(obj);
	}
	
	@Override
	public ActionType getType() {		
		return type;
	}
}
