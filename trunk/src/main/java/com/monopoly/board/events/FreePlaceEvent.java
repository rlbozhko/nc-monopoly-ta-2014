package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;

public class FreePlaceEvent extends Event {

	public FreePlaceEvent (String description) {        
        this.description = description;
    }
	
	@Override
	public void performEvent() {		
		Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
        ActionUtils.getPlayerIO(player).showMessage(description);	
	}

}
