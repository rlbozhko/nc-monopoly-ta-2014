package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

/**
 * Created by Roma on 20.11.2014.
 */
public class EndTurnAction implements Action {

    Board board;

    public EndTurnAction() {
        this.board = TestSession.getInstance().getBoard();
    }

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);
        if (player.mustPayRent()) {
            playerIO.showMessage("Для завершения хода уплатите аренду");
        } else {
            player.setStatus(Status.WAIT);
            board.getNextPlayer().setStatus(Status.START_TURN);
        }
    }

    @Override
    public String getName() {
        return "End Turn";
    }
}
