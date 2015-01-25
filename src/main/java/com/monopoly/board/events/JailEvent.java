package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.action.GoToJailAction;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

public class JailEvent extends BaseEvent {

	public JailEvent(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public void performEvent() {
		Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
		IO playerIO = ActionUtils.getPlayerIO(player);
		if (Status.CLEAN == player.getJailStatus()) {
			playerIO.showMessage("Вы пришли в тюрьму как посетитель");
		} else if (Status.ESCAPE == player.getJailStatus()) {
			playerIO.showMessage("Вы пришли в тюрьму и Вас словили");
			new GoToJailAction(player.getJailTerm() + GoToJailAction.ADD_JAIL_TERM).performAction(player);
		}
	}
}
