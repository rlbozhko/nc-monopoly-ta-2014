package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

public class JailEvent extends BaseEvent {

    private int JAIL_POSITION = 10;//temporary jail location

    @Override
    public void performEvent() {
        Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
        IO playerIO = ActionUtils.getPlayerIO(player);
        playerIO.showMessage("Вы попали в тюрьму. Ближайшие 5 ходов вы будете сидеть на нарах и хлебать баланду.");
        player.setStatus(Status.JAILED);
        if (player.getPosition() != JAIL_POSITION) {
            player.goToPosition(JAIL_POSITION);
        }
    }
}
