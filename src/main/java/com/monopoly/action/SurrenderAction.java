package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;

/**
 * Created by Roma on 20.11.2014.
 */
public class SurrenderAction implements Action {

    Session session;
    Board board;

    public SurrenderAction(Session session) {
        this.session = session;
        this.board = session.getBoard();
    }

    @Override
    public void performAction(Player player) {
        player.setStatus(Status.FINISH);
        board.nextPlayer().setStatus(Status.START_TURN);
    }



}
