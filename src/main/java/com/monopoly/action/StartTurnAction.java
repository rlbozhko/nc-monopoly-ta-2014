package com.monopoly.action;

import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;

/**
 * Created by Roma on 19.11.2014.
 */
public class StartTurnAction implements Action {
	private static final int MAX_DOUBLES = 3;

	@Override
	public void performAction(Player player) {
		Dice dice = Dice.getInstance();
		dice.generateNewDiceValue();
		ActionUtils.getPlayerIO(player).showDice();
		checkDoubles(player, dice);
		if (hasMaxDoubles(player)) {
			ActionUtils.sendMessageToAll(player.getName() + " слишком часто выбрасывает дубли. Видимо он жульничает");
			new GoToJailAction(GoToJailAction.FIRST_JAIL_TERM).performAction(player);
			new EndTurnAction().performAction(player);
			return;
		}
		player.goToPosition(player.getPosition() + dice.getFaceDie1() + dice.getFaceDie2());
		player.setStatus(Status.ACTIVE);
	}

	private boolean hasMaxDoubles(Player player) {
		if (player.getDoublesCount() == MAX_DOUBLES) {
			player.resetDoublesCount();
			return true;
		}
		return false;
	}

	private void checkDoubles(Player player, Dice dice) {
		if (dice.isSame()) {		
			player.incrementDoublesCount();
		} else {
			player.resetDoublesCount();
		}
	}

	@Override
	public String getName() {
		return "Start Turn";
	}
}
