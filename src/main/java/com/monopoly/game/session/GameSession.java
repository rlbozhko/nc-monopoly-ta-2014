package com.monopoly.game.session;

import com.monopoly.action.controller.ActionController;
import com.monopoly.action.controller.PlayerActionController;
import com.monopoly.board.Board;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.*;
import com.monopoly.board.events.*;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.io.ConsoleIO;
import com.monopoly.io.DummyIO;
import com.monopoly.io.IO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class GameSession implements Session {
	private static Session session;
	private static SessionStatus status = SessionStatus.NOT_EXISTS;
	
	private Board board;
	private ActionController actionController;
	private PropertyManager propertyManager;
	private List<IO> ios;
	private static final int START_MONEY = 5000;

	public static void main(String[] args) {
		List<Player> players = new ArrayList<>();
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		Player p3 = new Player("Player 3");

		p1.setStatus(Status.START_TURN);
		players.add(p1);
		players.add(p2);
		players.add(p3);

		List<IO> ios = new ArrayList<>();
		ios.add(new ConsoleIO(p1));
		ios.add(new DummyIO(p2));
		ios.add(new DummyIO(p3));

		GameSessionBuilder.setBoard(newBoard(players, START_MONEY));
		GameSessionBuilder.setActionController(new PlayerActionController());
		GameSessionBuilder.setPropertyManager(new PropertyManager(players));
		GameSessionBuilder.setIOs(ios);

		Session gameSession = GameSession.getInstance();

		ConsoleIO consoleIO = (ConsoleIO) gameSession.getIO().get(0);
		DummyIO dummyIO1 = (DummyIO) gameSession.getIO().get(1);
		DummyIO dummyIO2 = (DummyIO) gameSession.getIO().get(2);
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
			synchronized (GameSession.class) {
				localInstance = session;
				if (localInstance == null) {
					session = localInstance = new GameSession(GameSessionBuilder.getBoard(),
							GameSessionBuilder.getActionController(), GameSessionBuilder.getIos(),
							GameSessionBuilder.getPropertyManager());
				}
			}
		}
		return localInstance;
	}

	private GameSession(Board board, ActionController actionController, List<IO> ios, PropertyManager propertyManager) {
		this.board = board;
		this.actionController = actionController;
		this.ios = ios;
		this.propertyManager = propertyManager;
	}

	public static class GameSessionBuilder {
		private static Board board;
		private static PropertyManager propertyManager;
		private static ActionController actionController;
		private static List<IO> ios;

		private GameSessionBuilder() {
		}

		public static void setBoard(Board board) {
			GameSessionBuilder.board = board;
		}

		public static void setPropertyManager(PropertyManager propertyManager) {
			GameSessionBuilder.propertyManager = propertyManager;
		}

		public static void setActionController(ActionController actionController) {
			GameSessionBuilder.actionController = actionController;
		}

		public static void setIOs(List<IO> ios) {
			GameSessionBuilder.ios = ios;
		}

		public static ActionController getActionController() {
			return actionController;
		}

		public static Board getBoard() {
			return board;
		}

		public static PropertyManager getPropertyManager() {
			return propertyManager;
		}

		public static List<IO> getIos() {
			return ios;
		}
	}

	public static SessionStatus getStatus() {
		return status;
	}

	public static void setStatus(SessionStatus status) {
		GameSession.status = status;
	}

	public static Board newBoard(List<Player> players, int startMoney) {
		Monopoly monopoly1 = new Monopoly("Monopoly1");
		Monopoly monopoly2 = new Monopoly("Monopoly2");
		Monopoly monopoly3 = new Monopoly("Monopoly3");
		Monopoly monopoly4 = new Monopoly("Monopoly4");
		Monopoly monopoly5 = new Monopoly("Monopoly5");
		Monopoly monopoly6 = new Monopoly("Monopoly6");
		Monopoly monopoly7 = new Monopoly("Monopoly7");
		Monopoly monopoly8 = new Monopoly("Monopoly8");
		Monopoly monopoly9 = new Monopoly("Monopoly9");

		List<Event> chanceEvents = new ArrayList<>();
		chanceEvents.add(new EmergencyEvent("Emergency Event", "Случился пожар. Ваше здание Сгорело"));
		chanceEvents.add(new MoneyEvent("Получите деньги", "Получите $200"));
		MoneyEvent additionalOutcome = new MoneyEvent("Заплатите", "У Вас дополнителные расходы. Заплатите $200");
		additionalOutcome.setStartCash(-200);
		chanceEvents.add(additionalOutcome);
		chanceEvents.add(new RandomMoneyEvent("Казино", "Может выиграете, а может и проиграете"));
		// chanceEvents.add(new ExtraTurnEvent("Дополнителный ход",
		// "Получите дополнительный ход"));

		List<Cell> cells = new ArrayList<>();
		cells.add(new SingleEventCell("Начало", "Начало", 0, new MoneyEvent("Begin Event",
				"Вы прошли полный круг!!! Получите $200")));
		cells.add(new PropertyCell("c1m1", "c1m1 desc", cells.size(), new ArrayList<Building>(), 1000, 200, monopoly1));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m1", "c2m1 desc", cells.size(), new ArrayList<Building>(), 1000, 200, monopoly1));
		cells.add(new PropertyCell("c3m1", "c3m1 desc", cells.size(), new ArrayList<Building>(), 1000, 200, monopoly1));
		cells.add(new PropertyCell("c1m9", "c1m9 desc", cells.size(), new ArrayList<Building>(), 2000, 250, monopoly9));
		cells.add(new PropertyCell("c1m2", "c1m2 desc", cells.size(), new ArrayList<Building>(), 1500, 300, monopoly2));
		cells.add(new PropertyCell("c2m2", "c2m2 desc", cells.size(), new ArrayList<Building>(), 1500, 300, monopoly2));
		MoneyEvent incomeTax = new MoneyEvent("Уплатите налог", "Подоходный налог -$200");
		incomeTax.setStartCash(-200);
		cells.add(new SingleEventCell("Уплатите налог", "Подоходный налог -$200", cells.size(), incomeTax));
		cells.add(new PropertyCell("c3m2", "c3m2 desc", cells.size(), new ArrayList<Building>(), 1500, 300, monopoly2));
		MoneyEvent jailEvent = new MoneyEvent("Тюрьма", "Вы можете кого то посетить");
		jailEvent.setStartCash(0);
		cells.add(new SingleEventCell("Тюрьма", "Вы можете кого то посетить", cells.size(), jailEvent));
		cells.add(new PropertyCell("c1m3", "c1m3 desc", cells.size(), new ArrayList<Building>(), 3000, 300, monopoly3));
		cells.add(new PropertyCell("c2m3", "c2m3 desc", cells.size(), new ArrayList<Building>(), 3000, 300, monopoly3));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c3m3", "c3m3 desc", cells.size(), new ArrayList<Building>(), 3000, 300, monopoly3));
		cells.add(new PropertyCell("c2m9", "c2m9 desc", cells.size(), new ArrayList<Building>(), 2000, 250, monopoly9));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c1m4", "c1m4 desc", cells.size(), new ArrayList<Building>(), 3300, 330, monopoly4));
		MoneyEvent luxTax = new MoneyEvent("Уплатите налог", "Налог на роскошь");
		luxTax.setStartCash(-100);
		cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь -$100", cells.size(), luxTax));
		cells.add(new PropertyCell("c2m4", "c2m4 desc", cells.size(), new ArrayList<Building>(), 3300, 330, monopoly4));
		MoneyEvent freePlace = new MoneyEvent("Событие в Тюрьму", "Место для События В тюрьму");
		freePlace.setStartCash(0);
		cells.add(new SingleEventCell("Бесплатная стоянка", "Можете передохнуть", cells.size(), freePlace));
		cells.add(new PropertyCell("c1m5", "c1m5 desc", cells.size(), new ArrayList<Building>(), 3500, 350, monopoly5));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m5", "c2m5 desc", cells.size(), new ArrayList<Building>(), 3500, 350, monopoly5));
		MoneyEvent turnEvent = new MoneyEvent("Событие в Тюрьму", "Место для События В тюрьму");
		turnEvent.setStartCash(0);
		cells.add(new SingleEventCell("Событие Хода", "Место для События хода", cells.size(), turnEvent));
		cells.add(new PropertyCell("c3m9", "c3m9 desc", cells.size(), new ArrayList<Building>(), 2000, 250, monopoly9));
		cells.add(new PropertyCell("c1m6", "c1m6 desc", cells.size(), new ArrayList<Building>(), 4000, 400, monopoly6));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m6", "c2m6 desc", cells.size(), new ArrayList<Building>(), 4000, 400, monopoly6));
		cells.add(new PropertyCell("c3m6", "c3m6 desc", cells.size(), new ArrayList<Building>(), 4000, 400, monopoly6));
		JailEvent goToJail = new JailEvent();
		cells.add(new SingleEventCell("Событие в Тюрьму", "Место для События В тюрьму", cells.size(), goToJail));
		cells.add(new PropertyCell("c1m7", "c1m7 desc", cells.size(), new ArrayList<Building>(), 4200, 420, monopoly7));
		cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь", cells.size(), luxTax));
		cells.add(new PropertyCell("c2m7", "c2m7 desc", cells.size(), new ArrayList<Building>(), 4200, 420, monopoly7));
		cells.add(new PropertyCell("c3m7", "c3m7 desc", cells.size(), new ArrayList<Building>(), 4200, 420, monopoly7));
		cells.add(new PropertyCell("c4m9", "c4m9 desc", cells.size(), new ArrayList<Building>(), 2000, 250, monopoly9));
		cells.add(new PropertyCell("c1m8", "c1m8 desc", cells.size(), new ArrayList<Building>(), 4500, 450, monopoly8));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m8", "c2m8 desc", cells.size(), new ArrayList<Building>(), 4500, 450, monopoly8));
		cells.add(new PropertyCell("c3m8", "c3m8 desc", cells.size(), new ArrayList<Building>(), 4500, 450, monopoly8));

		for (Player player : players) {
			player.addMoney(startMoney);
		}

		return new Board(players, cells);
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	@Override
	public ActionController getActionController() {
		return actionController;
	}

	@Override
	public List<IO> getIO() {
		return ios;
	}

	@Override
	public void close() {
		session = null;
		setStatus(SessionStatus.NOT_EXISTS);
		GameSessionBuilder.setActionController(null);
		GameSessionBuilder.setBoard(null);;
		GameSessionBuilder.setIOs(null);
		GameSessionBuilder.setPropertyManager(null);		
	}
}
