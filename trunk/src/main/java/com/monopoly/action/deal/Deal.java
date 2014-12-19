package com.monopoly.action.deal;

import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 18.12.2014.
 */
public interface Deal {
    Player getSource();
    Player getTarget();
    IO getSourceIO();
    IO getTargetIO();
    String message();
    void performDeal();
    boolean isValid();
}
