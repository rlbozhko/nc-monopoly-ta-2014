package com.monopoly.game.session;

import com.monopoly.action.ActionController;
import com.monopoly.action.PlayerActionController;
import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.TestCell;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
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
    private static volatile Session session;

    private Board board;
    private ActionController actionController;
    private List<IO> ios;

    public static void main(String[] args) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            cells.add(new TestCell("Property", null, CellType.EVENT_CELL, cells.size()));
        }

        List<Player> players = new ArrayList<>();
        Player p1 = new Player("Player 1", new Wallet());
        Player p2 = new Player("Player 2", new Wallet());
        Player p3 = new Player("Player 3", new Wallet());
        p1.setStatus(Status.START_TURN);
        p2.setStatus(Status.WAIT);
        players.add(p1);
        players.add(p2);
        players.add(p3);

        List<Dice> dice = new ArrayList<>();
        dice.add(new Dice());
        dice.add(new Dice());

        List<IO> ios = new ArrayList<>();
        ios.add(new ConsoleIO(p1));
        ios.add(new DummyIO(p2));
        ios.add(new DummyIO(p3));

        TestSessionBuilder.setBoard(new Board(players, cells, null, null, dice));
        TestSessionBuilder.setActionController(new PlayerActionController());
        TestSessionBuilder.setIOs(ios);


        Session test = TestSession.getInstance();

        ConsoleIO consoleIO = (ConsoleIO) test.getIO().get(0);
        DummyIO dummyIO1 = (DummyIO) test.getIO().get(1);
        DummyIO dummyIO2 = (DummyIO) test.getIO().get(2);
        Thread player = new Thread(consoleIO);
        Thread dummy1 = new Thread(dummyIO1);
        Thread dummy2 = new Thread(dummyIO2);

        player.start();
        dummy1.start();
        dummy2.start();
    }


    public static Session getInstance() {
        Session localInstance = session;
        if (localInstance == null) {
            synchronized (TestSession.class) {
                localInstance = session;
                if (localInstance == null) {
                    session = localInstance = new TestSession(TestSessionBuilder.getBoard(),
                            TestSessionBuilder.getActionController(), TestSessionBuilder.getIos());
                }
            }
        }
        return localInstance;
    }

    /*
        private TestSession() {
            List<Cell> cells = new ArrayList<>();
            for (int i = 0; i < 20 ; i++) {
                cells.add(new TestCell("Property",null, CellType.EVENT_CELL, cells.size()));
            }

            List<Player> players = new ArrayList<>();
            Player p1 = new Player("Player 1", new Wallet());
            Player p2 = new Player("Player 2", new Wallet());
            Player p3 = new Player("Player 3", new Wallet());
            p1.setStatus(Status.START_TURN);
            p2.setStatus(Status.WAIT);
            players.add(p1);
            players.add(p2);
            players.add(p3);

            List<Dice> dice = new ArrayList<>();
            dice.add(new Dice());
            dice.add(new Dice());

            board = new Board(players, cells,null,null, dice);

            actionController = new PlayerActionController(this);
            ios = new ArrayList<>();

            ios.add(new ConsoleIO(this, p1));
            ios.add(new DummyIO(this, p2));
            ios.add(new DummyIO(this, p3));
        }
    */
    private TestSession(Board board, ActionController actionController, List<IO> ios) {
        this.board = board;
        this.actionController = actionController;
        this.ios = ios;
    }

    public static class TestSessionBuilder {
        private static Board board;
        private static ActionController actionController;
        private static List<IO> ios;

        private TestSessionBuilder() {
        }

        public static void setBoard(Board board) {
            TestSessionBuilder.board = board;
        }

        public static void setActionController(ActionController actionController) {
            TestSessionBuilder.actionController = actionController;
        }

        public static void setIOs(List<IO> ios) {
            TestSessionBuilder.ios = ios;
        }

        public static ActionController getActionController() {
            return actionController;
        }

        public static Board getBoard() {
            return board;
        }

        public static List<IO> getIos() {
            return ios;
        }
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
