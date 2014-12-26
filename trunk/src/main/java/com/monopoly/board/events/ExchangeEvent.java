package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;

public class ExchangeEvent extends BaseEvent {

	public ExchangeEvent (String name, String description) {
        this.name = name;
        this.description = description;
    }
	
	@Override
	public void performEvent() {
		// TODO Auto-generated method stub
		Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
        ActionUtils.getPlayerIO(player).showMessage(description);	
	}

}
