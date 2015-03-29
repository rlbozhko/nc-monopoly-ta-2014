package com.monopoly.action.jail;

import com.monopoly.action.Action;
import com.monopoly.action.ActionType;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.EndTurnAction;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.io.IO;

public class ServeJailTermAction implements Action {	
	public final static ActionType type = ActionType.SERVE_JAIL_TERM;
	
	@Override
	public void performAction(Player player) {
		IO playerIO = ActionUtils.getPlayerIO(player);
		player.subtractJailTerm();		
		if (player.getJailTerm() == 0) {
			player.setJailStatus(Status.CLEAN);
			playerIO.showMessage("Вы отсидели свой срок. Можете быть свободны.");
			ActionUtils.sendMessageToAll(player.getName() + " отсидел свой срок.");
		} else {
			playerIO.showMessage("Вам еще сидеть " + player.getJailTerm() + " ход(ов)");
		}
		new EndTurnAction().performAction(player);		
	}

	@Override
	public String getName() {
		return "Кантоваться(отсидеть срок)";
	}
	
	@Override
	public ActionType getType() {		
		return type;
	}
}