package com.monopoly.action;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;

public class GoToJailAction implements Action {
	public final static int FIRST_JAIL_TERM = 5;
	public final static int ADD_JAIL_TERM = 3;
	
	private int jailTerm;    
	
	public GoToJailAction(int jailTerm) {
		this.jailTerm = jailTerm;
	}
	
	@Override
	public void performAction(Player player) {
		Cell jailCell = GameSession.getInstance().getBoard().getJailCell();
		player.setPayRent(false);
        player.setJailStatus(Status.JAILED);
        player.setJailTerm(jailTerm);
        if (player.getPosition() != jailCell.getPosition()) {
            player.goToPosition(jailCell.getPosition());
        }
        ActionUtils.sendMessageToAll(player.getName() + " сел в тюрьму на " + jailTerm + " ходов");
	}

	@Override
	public String getName() { 
		return "Go to Jail";
	}
	
	@Override
	public ActionType getType() {		
		return null;
	}
}
