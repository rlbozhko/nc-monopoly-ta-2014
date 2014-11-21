package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;

/**
 * Created by Roma on 20.11.2014.
 */
public class EndTurnAction implements Action {

    Session session;
    Board board;

    public EndTurnAction(Session session) {
        this.session = session;
        this.board = session.getBoard();
    }

    @Override
    public void performAction(Player player) {
        player.setStatus(Status.WAIT);
        board.getNextPlayer().setStatus(Status.START_TURN);
    }
}
