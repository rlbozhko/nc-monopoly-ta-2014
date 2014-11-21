package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;

/**
 * Created by Roma on 21.11.2014.
 */

import com.monopoly.board.Board;
        import com.monopoly.board.player.Player;
        import com.monopoly.board.player.Status;
        import com.monopoly.game.session.Session;

/**
 * Created by Roma on 20.11.2014.
 */
public class WaitAction implements Action {


    public WaitAction() {
    }

    @Override
    public void performAction(Player player) {
    }

    @Override
    public String getName() {
        return "Wait";
    }
}