package com.monopoly.action;

import com.monopoly.board.player.Player;

/**
 * Created by Roma on 19.11.2014.
 */
public interface Action {
    void performAction(Player player);

    String getName();
}
