package com.monopoly.action;

import java.util.List;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.performer.GoToJailPerformer;

public class BetrayalActioin implements Action {
	public final static ActionType type = ActionType.BETRAYAL;
	
	private final int ADD_JAIL_TERM = 3;	

	@Override
	public void performAction(Player player) {
		int myPosition = player.getPosition();
		List<Player> players = GameSession.getInstance().getBoard().getPlayers();
		for (Player otherPlayer : players) {
			if ((myPosition == otherPlayer.getPosition()) && (otherPlayer.getJailStatus() == Status.ESCAPE)) {
				ActionUtils.sendMessageToAll(player.getName() + " сдал " + otherPlayer.getName() + " полиции!");
				new GoToJailPerformer(ADD_JAIL_TERM).perform(otherPlayer);
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
