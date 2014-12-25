package com.monopoly.action;

import com.monopoly.board.events.GoToJail;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;

import java.util.List;

public class BetrayalActioin implements Action {
    @Override
    public void performAction(Player player) {

        int myPosition = player.getPosition();
        List<Player> players = GameSession.getInstance().getBoard().getPlayers();
        for (Player otherPlayer : players) {
            if ((myPosition == otherPlayer.getPosition())&&(otherPlayer.getJailStatus() == Status.ESCAPE)) {
                new GoToJail().performEvent(otherPlayer);
            }
        }
    }

    @Override
    public String getName() {
        return "betray jailed";
    }
}
