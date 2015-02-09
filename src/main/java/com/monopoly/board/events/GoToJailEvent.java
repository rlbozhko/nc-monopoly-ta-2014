package com.monopoly.board.events;

import com.monopoly.action.EndTurnAction;
import com.monopoly.action.GoToJailAction;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;

public class GoToJailEvent extends Event {
	
	public GoToJailEvent(String description) {		
		this.description = description;
	}
	
	@Override
	public void performEvent() {
		Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
		new GoToJailAction(GoToJailAction.FIRST_JAIL_TERM).performAction(player);
		new EndTurnAction().performAction(player);
	}
}
