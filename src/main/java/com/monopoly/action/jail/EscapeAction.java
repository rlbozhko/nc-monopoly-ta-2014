package com.monopoly.action.jail;

import com.monopoly.action.Action;
import com.monopoly.action.ActionType;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.EndTurnAction;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.io.IO;

public class EscapeAction implements Action {
	public final static ActionType type = ActionType.ESCAPE;
	private final int ESCAPE_COMBINATION = 9;
	private final int PUNISHMENT = 3;	

	@Override
	public void performAction(Player player) {
		IO playerIO = ActionUtils.getPlayerIO(player);
		Dice dice = Dice.getInstance();
		dice.generateNewDiceValue();
		int goodLuckCombination = dice.getFaceDie1() + dice.getFaceDie2();

		if (goodLuckCombination >= ESCAPE_COMBINATION || dice.isSame()) {
			player.setJailStatus(Status.ESCAPE);
			player.goToPosition(player.getPosition() + goodLuckCombination);
			playerIO.showMessage("Поздравляем!!! Вам удалось совершить побег. Но в течении " + player.getJailTerm()
					+ " ходов Вас будут разыскивать. Не попадитесь");
			ActionUtils.sendMessageToAll("Розыскивается сбежавший преступник " + player.getName() + " !!!");
		} else {
			player.setJailTerm(player.getJailTerm() + PUNISHMENT);
			playerIO.showMessage("План побега не удался. За попытку побега вам добавили к сроку " + PUNISHMENT
					+ " ходов");
			new EndTurnAction().performAction(player);
		}
	}

	@Override
	public String getName() {
		return "Escape from jail";
	}
	
	@Override
	public ActionType getType() {		
		return type;
	}
}
