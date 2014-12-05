package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;

public class MoneyEvent extends BaseEvent {

    private int startCash;

    public MoneyEvent(String name, String description, int startCash) {
        this.name = name;
        this.description = description;
        this.startCash = startCash;
    }

    @Override
    public void performEvent() {
        Player player = TestSession.getInstance().getBoard().getCurrentPlayer();
        player.getWallet().addMoney(startCash);
        ActionUtils.getPlayerIO(player).showMessage(description);
    }
}
