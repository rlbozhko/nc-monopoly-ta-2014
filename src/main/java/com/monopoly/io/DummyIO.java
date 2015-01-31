package com.monopoly.io;

import java.util.List;
import java.util.Queue;

import com.monopoly.action.ActionType;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.controller.ActionController;
import com.monopoly.action.deal.Deal;
import com.monopoly.board.cells.Property;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.WebIO.SelectPropertyHelper;
import com.monopoly.io.WebIO.YesNoDialog;

/**
 * Created by Roma on 20.11.2014.
 */
public class DummyIO implements IO, Runnable {

	private Player player;
	private List<ActionType> actions;
	private boolean answer = true;

	public DummyIO(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			outputAvailableActions();
			for (ActionType actionType : actions) {
				if (player.getMoney() == 0) {
					performAction(ActionType.FINISH_GAME);
					break;
				}
				if (actionType == ActionType.PAY_RENT) {
					performPayRent(actionType);
					break;
				} else if (actionType == ActionType.START_TURN || actionType == ActionType.END_TURN
						|| actionType == ActionType.SERVE_JAIL_TERM) {
					performAction(actionType);
					break;
				}
			}
		} while (Status.FINISH != player.getStatus());
	}

	private void performPayRent(ActionType actionType) {
		Property property = (Property) player.getCurrentCell();
		if (player.getMoney() >= property.getRent()) {
			performAction(actionType);
		} else {
			performAction(ActionType.FINISH_GAME);
		}
	}

	public void outputAvailableActions() {
		ActionController actionController = GameSession.getInstance().getActionController();
		actions = actionController.getAvailableActions(player);
	}

	@Override
	public void performAction(ActionType actionType) {
		actionType.create().performAction(player);
	}

	@Override
	public Player getOwner() {
		return player;
	}

	@Override
	public Player selectPlayer() {
		return null;
	}

	@Override
	public Property selectProperty(Player player) {
		return null;
	}

	@Override
	public com.monopoly.action.deal.Deal dealDialog(Player otherPlayer) {
		return null;
	}

	@Override
	public boolean yesNoDialog(String message) {
		if (answer) {
			answer = false;
			return true;
		} else {
			answer = true;
			return false;
		}
	}

	@Override
	public void showMessage(String message) {
		// System.out.println(player.getName() + ": " + message);
	}

	@Override
	public void showDice() {
		Dice dice = Dice.getInstance();
		ActionUtils.sendMessageToAll(player.getName() + " бросил кости: " + dice.getFaceDie1() + " "
				+ dice.getFaceDie2());
	}

	@Override
	public boolean hasSelectPlayerRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSelectedPlayer(Player selectedPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public SelectPropertyHelper getSelectPropertyHelper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatedDeal(Deal deal) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasCreateDealRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasYesNoDialog() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YesNoDialog getYesNoDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Queue<String> getAllMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showWarning(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getWarning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasWarning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSelectPropertyRequest() {
		// TODO Auto-generated method stub
		return false;
	}
}
