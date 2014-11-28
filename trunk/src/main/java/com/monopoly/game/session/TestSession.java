package com.monopoly.game.session;

import com.monopoly.action.ActionController;
import com.monopoly.action.PlayerActionController;
import com.monopoly.board.Board;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.*;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.dice.ValueGeneratorForDice;
import com.monopoly.board.events.EmergencyEvent;
import com.monopoly.board.events.Event;
import com.monopoly.board.events.MoneyEvent;
import com.monopoly.board.events.RandomMoneyEvent;
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
    private static final int START_MONEY = 5000;

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        Player p1 = new Player("Player 1", new Wallet());
        Player p2 = new Player("Player 2", new Wallet());
        Player p3 = new Player("Player 3", new Wallet());
        p1.setStatus(Status.START_TURN);
        players.add(p1);
        players.add(p2);
        players.add(p3);

        List<IO> ios = new ArrayList<>();
        ios.add(new ConsoleIO(p1));
        ios.add(new DummyIO(p2));
        ios.add(new DummyIO(p3));


        TestSessionBuilder.setBoard(newBoard(players, START_MONEY));
        TestSessionBuilder.setActionController(new PlayerActionController());
        TestSessionBuilder.setIOs(ios);

        Session test = TestSession.getInstance();

        ConsoleIO consoleIO = (ConsoleIO) test.getIO().get(0);
        DummyIO dummyIO1 = (DummyIO) test.getIO().get(1);
        DummyIO dummyIO2 = (DummyIO) test.getIO().get(2);
        Thread player = new Thread(consoleIO);
        Thread dummy1 = new Thread(dummyIO1);
        Thread dummy2 = new Thread(dummyIO2);

        //Для тестирования Действий с собственностью
/*      for (Cell property : test.getBoard().getPropertyCell()) {
            ((Property) property).setOwner(p1);
        }
        Property testProperty = (Property) test.getBoard().getCells().get(1);
        testProperty.setOwner(p1);
        //p1.getWallet().addMoney(5000);
        p2.getWallet().subtractMoney(START_MONEY);
        //p3.getWallet().addMoney(5000);
        //
*/
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

    private TestSession(Board board, ActionController actionController, List<IO> ios) {
        this.board = board;
        this.actionController = actionController;
        this.ios = ios;
    }

    public static class TestSessionBuilder {
        private static Board board;
        private static ActionController actionController;
        private static List<IO> ios;
        private static ValueGeneratorForDice valueGeneratorForDice;

        private TestSessionBuilder() {
        }

        /*
                public static void setValueGeneratorForDice(ValueGeneratorForDice valueGeneratorForDice) {
                    TestSessionBuilder.valueGeneratorForDice = valueGeneratorForDice;
                }

                public static ValueGeneratorForDice getValueGeneratorForDice() {
                    return valueGeneratorForDice;
                }
        */
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


    private static Board newBoard(List<Player> players, int startMoney) {
        ValueGeneratorForDice valueGeneratorForDice = new ValueGeneratorForDice();
        Thread diceGenerator = new Thread(valueGeneratorForDice);
        diceGenerator.start();

        Dice dice = new Dice(valueGeneratorForDice);

        Monopoly monopoly1 = new Monopoly("Monopoly1");
        Monopoly monopoly2 = new Monopoly("Monopoly2");
        Monopoly monopoly3 = new Monopoly("Monopoly3");
        Monopoly monopoly4 = new Monopoly("Monopoly4");
        Monopoly monopoly5 = new Monopoly("Monopoly5");
        Monopoly monopoly6 = new Monopoly("Monopoly6");
        Monopoly monopoly7 = new Monopoly("Monopoly7");
        Monopoly monopoly8 = new Monopoly("Monopoly8");
        Monopoly monopoly9 = new Monopoly("Monopoly9");

        List<Event> chanceEvents = new ArrayList<Event>();
        chanceEvents.add(new EmergencyEvent("Emergency Event", "Случился пожар. Ваше здание Сгорело", 1));
        chanceEvents.add(new MoneyEvent("Получите деньги", "Получите $200", 200));
        chanceEvents.add(new MoneyEvent("Заплатите", "У Вас дополнителные расходы. Заплатите $200", -200));
        chanceEvents.add(new RandomMoneyEvent("Казино", "Может выиграете, а может и проиграете"));
        //chanceEvents.add(new ExtraTurnEvent("Дополнителный ход", "Получите дополнительный ход"));

        List<Cell> cells = new ArrayList<>();
        cells.add(new SingleEventCell("Начало", "Начало", 0,
                new MoneyEvent("Begin Event", "Вы прошли полный круг!!! Получите $200", 200)));
        cells.add(new PropertyCell("c1m1", "c1m1 desc", cells.size(), null,
                new ArrayList<Building>(), 1000, 200, monopoly1));
        cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
        cells.add(new PropertyCell("c2m1", "c2m1 desc", cells.size(), null,
                new ArrayList<Building>(), 1000, 200, monopoly1));
        cells.add(new PropertyCell("c3m1", "c3m1 desc", cells.size(), null,
                new ArrayList<Building>(), 1000, 200, monopoly1));
        cells.add(new PropertyCell("c1m9", "c1m9 desc", cells.size(), null,
                new ArrayList<Building>(), 2000, 250, monopoly9));
        cells.add(new PropertyCell("c1m2", "c1m2 desc", cells.size(), null,
                new ArrayList<Building>(), 1500, 300, monopoly2));
        cells.add(new PropertyCell("c2m2", "c2m2 desc", cells.size(), null,
                new ArrayList<Building>(), 1500, 300, monopoly2));
        cells.add(new SingleEventCell("Уплатите налог", "Подоходный налог -$200", cells.size(),
                new MoneyEvent("Уплатите налог", "Подоходный налог -$200", -200)));
        cells.add(new PropertyCell("c3m2", "c3m2 desc", cells.size(), null,
                new ArrayList<Building>(), 1500, 300, monopoly2));
        cells.add(new SingleEventCell("Тюрьма", "Вы можете кого то посетить", cells.size(),
                new MoneyEvent("Тюрьма", "Вы можете кого то посетить", 0)));
        cells.add(new PropertyCell("c1m3", "c1m3 desc", cells.size(), null,
                new ArrayList<Building>(), 3000, 300, monopoly3));
        cells.add(new PropertyCell("c2m3", "c2m3 desc", cells.size(), null,
                new ArrayList<Building>(), 3000, 300, monopoly3));
        cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
        cells.add(new PropertyCell("c3m3", "c3m3 desc", cells.size(), null,
                new ArrayList<Building>(), 3000, 300, monopoly3));
        cells.add(new PropertyCell("c2m9", "c2m9 desc", cells.size(), null,
                new ArrayList<Building>(), 2000, 250, monopoly9));
        cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
        cells.add(new PropertyCell("c1m4", "c1m4 desc", cells.size(), null,
                new ArrayList<Building>(), 3300, 330, monopoly4));
        cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь -$100", cells.size(),
                new MoneyEvent("Уплатите налог", "Налог на роскошь -$100", -100)));
        cells.add(new PropertyCell("c2m4", "c2m4 desc", cells.size(), null,
                new ArrayList<Building>(), 3300, 330, monopoly4));
        cells.add(new SingleEventCell("Бесплатная стоянка", "Можете передохнуть", cells.size(),
                new MoneyEvent("Бесплатная стоянка", "Можете передохнуть", 0)));
        cells.add(new PropertyCell("c1m5", "c1m5 desc", cells.size(), null,
                new ArrayList<Building>(), 3500, 350, monopoly5));
        cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
        cells.add(new PropertyCell("c2m5", "c2m5 desc", cells.size(), null,
                new ArrayList<Building>(), 3500, 350, monopoly5));
        cells.add(new SingleEventCell("Событие Хода", "Место для События хода", cells.size(),
                new MoneyEvent("Событие Хода", "Место для События хода", 0)));
        cells.add(new PropertyCell("c3m9", "c3m9 desc", cells.size(), null,
                new ArrayList<Building>(), 2000, 250, monopoly9));
        cells.add(new PropertyCell("c1m6", "c1m6 desc", cells.size(), null,
                new ArrayList<Building>(), 4000, 400, monopoly6));
        cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
        cells.add(new PropertyCell("c2m6", "c2m6 desc", cells.size(), null,
                new ArrayList<Building>(), 4000, 400, monopoly6));
        cells.add(new PropertyCell("c3m6", "c3m6 desc", cells.size(), null,
                new ArrayList<Building>(), 4000, 400, monopoly6));
        cells.add(new SingleEventCell("Событие в Тюрьму", "Место для События В тюрьму", cells.size(),
                new MoneyEvent("Событие в Тюрьму", "Место для События В тюрьму", 0)));
        cells.add(new PropertyCell("c1m7", "c1m7 desc", cells.size(), null,
                new ArrayList<Building>(), 4200, 420, monopoly7));
        cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь", cells.size(),
                new MoneyEvent("Уплатите налог", "Налог на роскошь", -100)));
        cells.add(new PropertyCell("c2m7", "c2m7 desc", cells.size(), null,
                new ArrayList<Building>(), 4200, 420, monopoly7));
        cells.add(new PropertyCell("c3m7", "c3m7 desc", cells.size(), null,
                new ArrayList<Building>(), 4200, 420, monopoly7));
        cells.add(new PropertyCell("c4m9", "c4m9 desc", cells.size(), null,
                new ArrayList<Building>(), 2000, 250, monopoly9));
        cells.add(new PropertyCell("c1m8", "c1m8 desc", cells.size(), null,
                new ArrayList<Building>(), 4500, 450, monopoly8));
        cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
        cells.add(new PropertyCell("c2m8", "c2m8 desc", cells.size(), null,
                new ArrayList<Building>(), 4500, 450, monopoly8));
        cells.add(new PropertyCell("c3m8", "c3m8 desc", cells.size(), null,
                new ArrayList<Building>(), 4500, 450, monopoly8));

        for (Player player : players) {
            player.getWallet().addMoney(startMoney);
        }

        return new Board(players, cells, dice);
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
/*
    @Override
    public ValueGeneratorForDice getValueGeneratorForDice() {
        return valueGeneratorForDice;
    }
    */
}
