package com.monopoly.action;

import java.util.List;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;

public class BetrayalActioin implements Action {
	public final static ActionType type = ActionType.BETRAYAL;
	
	private final int ADD_JAIL_TERM = 3;	

	@Override
	public void performAction(Player player) {
		int myPosition = player.getPosition();
		List<Player> players = GameSession.getInstance().getBoard().getPlayers();
		for (Player otherPlayer : players) {
			if ((myPosition == otherPlayer.getPosition()) && (otherPlayer.getJailStatus() == Status.ESCAPE)) {
				new GoToJailAction(otherPlayer.getJailTerm() + ADD_JAIL_TERM).performAction(otherPlayer);
			}
		}
	}

	@Override
	public String getName() {
		return "Call Police";
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