package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;

public class MoneyEvent implements Event {

    private int startCash;
    private String eventName;
    private String description;

    public MoneyEvent(String eventName, String description, int startCash) {
        this.eventName = eventName;
        this.description = description;
        this.startCash = startCash;
    }

    @Override
    public void performEvent() {
        Player player = TestSession.getInstance().getBoard().getCurrentPlayer();
        player.getWallet().addMoney(startCash);
        ActionUtils.getPlayerIO(player).showMessage(description);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return eventName;
    }
}
