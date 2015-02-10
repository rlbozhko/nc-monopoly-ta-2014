package com.monopoly.action;

import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;

/**
 * Created by Roma on 19.11.2014.
 */
public class StartTurnAction implements Action {
	private static final int MAX_DOUBLES = 3;
	public final static ActionType type = ActionType.START_TURN;

	@Override
	public void performAction(Player player) {
		Dice dice = Dice.getInstance();
		dice.generateNewDiceValue();
		ActionUtils.getPlayerIO(player).showDice();
		if (checkDoubles(player, dice)) {			
			player.setExtraTurn(true);
		}
		if (hasMaxDoubles(player)) {
			ActionUtils.sendMessageToAll(player.getName() + " слишком часто выбрасывает дубли. Видимо он жульничает");
			player.setExtraTurn(false);
			new GoToJailAction(GoToJailAction.ADD_JAIL_TERM).performAction(player);
			new EndTurnAction().performAction(player);
			return;
		}
		if (player.hasExtraTurn()) {
			ActionUtils.sendMessageToAll(player.getName() + " выбросил дубль и получил дополнительный ход!");
		}
		player.setStatus(Status.ACTIVE);
		player.goToPosition(player.getPosition() + dice.getFaceDie1() + dice.getFaceDie2());		
	}

	private boolean hasMaxDoubles(Player player) {
		if (player.getDoublesCount() == MAX_DOUBLES) {
			player.resetDoublesCount();
			return true;
		}
		return false;
	}

	private boolean checkDoubles(Player player, Dice dice) {
		if (dice.isSame()) {		
			player.incrementDoublesCount();
			return true;
		} else {
			player.resetDoublesCount();
			return false;
		}
	}

	@Override
	public String getName() {
		return "Start Turn";
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
