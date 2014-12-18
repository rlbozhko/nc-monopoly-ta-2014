package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;

public class MoneyEvent extends BaseEvent {

    private int startCash;

    public MoneyEvent(String name, String description) {
        this.name = name;
        this.description = description;
        this.startCash = 200;
    }

    @Override
    public void performEvent() {
        Player player = TestSession.getInstance().getBoard().getCurrentPlayer();
        player.addMoney(startCash);
        ActionUtils.getPlayerIO(player).showMessage(description);
    }

	public synchronized int getStartCash() {
		return startCash;
	}

	public synchronized void setStartCash(int startCash) {
		this.startCash = startCash;
	}
}
