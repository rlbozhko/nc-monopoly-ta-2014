package com.monopoly.board.events;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;

public class GoToJail {
    private final int JAIL_TERM = 5;
    private int jailPosition = 10;


    public void performEvent(Player player) {
        player.setPayRent(false);
        player.setStatus(Status.JAILED);
        player.setJailStatus(Status.JAILED);
        player.setJailTerm(JAIL_TERM);
        if (player.getPosition() != jailPosition) {
            player.goToPosition(jailPosition);
        }
    }
}
