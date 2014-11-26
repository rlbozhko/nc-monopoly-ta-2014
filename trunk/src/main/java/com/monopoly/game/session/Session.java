package com.monopoly.game.session;

import com.monopoly.action.ActionController;
import com.monopoly.board.Board;
import com.monopoly.board.dice.ValueGeneratorForDice;
import com.monopoly.io.IO;

import java.util.List;

/**
 * Created by Roma on 19.11.2014.
 */
public interface Session {
    Board getBoard();

    ActionController getActionController();

    List<IO> getIO();

    ValueGeneratorForDice getValueGeneratorForDice();
}
