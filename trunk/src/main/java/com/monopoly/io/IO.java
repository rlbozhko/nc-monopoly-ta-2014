package com.monopoly.io;

import com.monopoly.action.Action;
import com.monopoly.board.player.Player;

/**
 * Created by Roma on 19.11.2014.
 */
public interface IO {
    void outputBoardState();
    void outputAvailableActions(Player player);
    void performAction(Action action);
}
