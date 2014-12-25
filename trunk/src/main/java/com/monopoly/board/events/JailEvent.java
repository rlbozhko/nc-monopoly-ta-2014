package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.action.EndTurnAction;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

public class JailEvent extends BaseEvent {

    private int jailPosition = 10;//temporary jail location
    private final int JAIL_TERM = 5;

    @Override
    public void performEvent() {
        Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
        IO playerIO = ActionUtils.getPlayerIO(player);
        playerIO.showMessage("Вы попали в тюрьму. Ближайшие 5 ходов вы будете сидеть на нарах и хлебать баланду.");
        player.setStatus(Status.JAILED);
        player.setJailTerm(JAIL_TERM);
        if (player.getPosition() != jailPosition) {
            player.goToPosition(jailPosition);
        }
        new EndTurnAction().performAction(player);
    }
}
