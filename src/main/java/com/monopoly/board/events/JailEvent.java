package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.performer.GoToJailPerformer;

public class JailEvent extends Event {

	public JailEvent(String description) {		
		this.description = description;
	}

	@Override
	public void performEvent() {
		Player player = GameSession.getInstance().getBoard().getCurrentPlayer();		
		if (Status.CLEAN == player.getJailStatus()) {
			ActionUtils.sendMessageToAll(player.getName() + " пришел в тюрьму как посетитель");
		} else if (Status.ESCAPE == player.getJailStatus()) {
			ActionUtils.sendMessageToAll(player.getName() +" пришел в тюрьму и его словили!");
			new GoToJailPerformer(GoToJailPerformer.ADD_JAIL_TERM).perform(player);
		}
	}
}
