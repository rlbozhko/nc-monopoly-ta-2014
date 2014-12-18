package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

import java.util.ArrayList;

/**
 * Created by Roma on 20.11.2014.
 */
public class EndTurnAction implements Action {

    Board board;

    public EndTurnAction() {
        this.board = GameSession.getInstance().getBoard();
    }

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);

        if (player.isPayRent()) {
            playerIO.showMessage("Для завершения хода уплатите аренду");
        } else {
            player.setStatus(Status.WAIT);
            board.getNextPlayer().setStatus(Status.START_TURN);
            checkAndStartAuction(player);
        }
    }

    private void checkAndStartAuction(Player player) {
        if (player.hasPledgedProperty()) {
            for (Property property : new ArrayList<>(player.getPropertyList())) {
                if(property.isPledged() && 0 == property.getTurnsToPayBack()) {
                    new AuctionAction(property).performAction(player);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "End Turn";
    }
}
