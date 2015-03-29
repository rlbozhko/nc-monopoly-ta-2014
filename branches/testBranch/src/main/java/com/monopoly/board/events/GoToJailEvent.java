package com.monopoly.board.events;

import com.monopoly.action.EndTurnAction;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;
import com.monopoly.performer.GoToJailPerformer;

public class GoToJailEvent extends Event {
	
	public GoToJailEvent(String description) {		
		this.description = description;
	}
	
	@Override
	public void performEvent() {
		Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
		new GoToJailPerformer(GoToJailPerformer.FIRST_JAIL_TERM).perform(player);
		new EndTurnAction().performAction(player);
	}
}
