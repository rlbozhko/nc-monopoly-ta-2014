package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;

public class MoneyEvent extends Event {

    private int value;

    public MoneyEvent(String description) {        
        this.description = description;
        this.value = 200;
    }

    @Override
    public void performEvent() {
        Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
        player.addMoney(value);
        ActionUtils.sendMessageToAll(player.getName() + " " + description);
    }

	public int getStartCash() {
		return value;
	}

	public void setStartCash(int startCash) {
		this.value = startCash;
	}
}
