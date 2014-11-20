package com.monopoly.game.session;

import com.monopoly.action.ActionController;
import com.monopoly.action.PlayerActionController;
import com.monopoly.board.Board;
import com.monopoly.board.cells.*;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Wallet;
import com.monopoly.io.ConsoleIO;
import com.monopoly.io.DummyIO;
import com.monopoly.io.IO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class TestSession implements Session {
    Board board;
    ActionController actionController;
    List<IO> ios;

    public static void main(String[] args) {
        Session test = new TestSession();
        ConsoleIO consoleIO = (ConsoleIO) test.getIO().get(0);
        DummyIO dummyIO = (DummyIO) test.getIO().get(1);
        Thread player = new Thread(consoleIO);
        Thread dummy = new Thread(dummyIO);

        player.start();
        dummy.start();

    }

    public TestSession() {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
            //cells.add(new PropertyCell.PropertyBuilder("Property",null,cells.size()).getPropertyCell());
            cells.add(new TestCell("Property",null, CellType.EVENT_CELL, cells.size()));
        }
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1", new Wallet()));
        players.add(new Player("Player 2", new Wallet()));

        board = new Board(players, cells);

        actionController = new PlayerActionController(this);
        ios = new ArrayList<>();

        ios.add(new ConsoleIO(this, players.get(0)));
        ios.add(new DummyIO(this, players.get(1)));

    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public ActionController getActionController() {
        return actionController;
    }

    @Override
    public List<IO> getIO() {
        return ios;
    }
}
