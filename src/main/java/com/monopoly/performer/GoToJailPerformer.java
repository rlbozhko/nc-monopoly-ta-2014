package com.monopoly.performer;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;

public class GoToJailPerformer implements Performer {
	public final static int FIRST_JAIL_TERM = 5;
	public final static int ADD_JAIL_TERM = 3;

	private int jailTerm;

	public GoToJailPerformer(int jailTerm) {
		this.jailTerm = jailTerm;
	}

	@Override
	public void perform(Player player) {
		Cell jailCell = GameSession.getInstance().getBoard().getJailCell();
		player.setPayRent(false);
		player.setJailStatus(Status.JAILED);
		player.setJailTerm(jailTerm + player.getJailTerm());
		player.setExtraTurn(false);
		if (player.getPosition() != jailCell.getPosition()) {
			player.goToPosition(jailCell.getPosition());
		}
		ActionUtils.sendMessageToAll(player.getName() + " сел в тюрьму на " + player.getJailTerm() + " ходов");
	}
}
