package com.monopoly.action;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;

public class GoToJailAction implements Action {
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
        ActionUtils.getPlayerIO(player).showMessage("Вас посадили в тюрьму на " + jailTerm + " ходов");
	}

	@Override
	public String getName() { 
		return "Go to Jail";
	}
	
	


    

}
