	package com.monopoly.game.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.monopoly.action.controller.ActionController;
import com.monopoly.action.controller.PlayerActionController;
import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.cells.RandomEventCell;
import com.monopoly.board.cells.SingleEventCell;
import com.monopoly.board.events.EmergencyEvent;
import com.monopoly.board.events.Event;
import com.monopoly.board.events.GoToJailEvent;
import com.monopoly.board.events.JailEvent;
import com.monopoly.board.events.MoneyEvent;
import com.monopoly.board.events.RandomMoneyEvent;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.io.ConsoleIO;
import com.monopoly.io.DummyIO;
import com.monopoly.io.IO;

/**
 * Created by Roma on 20.11.2014.
 */
public class GameSession implements Session {
	private static volatile Session session;
	private static SessionStatus status = SessionStatus.NOT_EXISTS;	

	private Board board;
	private ActionController actionController;
	private PropertyManager propertyManager;
	//private List<IO> ios;
	private Map<User, IO> userIO;

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
		
		Map<User, IO> usersIO = new HashMap<User, IO>();
		usersIO.put(new User("p1@test.ua", "123", "hash1"), new ConsoleIO(p1));
		usersIO.put(new User("p2@test.ua", "123", "hash2"), new DummyIO(p2));
		usersIO.put(new User("p3@test.ua", "123", "hash3"), new DummyIO(p3));
		
		
		GameSessionBuilder.setBoard(newBoard(players, START_MONEY));
		GameSessionBuilder.setActionController(new PlayerActionController());
		GameSessionBuilder.setPropertyManager(new PropertyManager(players));
		GameSessionBuilder.setUsersIO(usersIO);

		Session gameSession = GameSession.getInstance();

		ConsoleIO consoleIO = (ConsoleIO) gameSession.getIO().get(0);
		DummyIO dummyIO1 = (DummyIO) gameSession.getIO().get(1);
		DummyIO dummyIO2 = (DummyIO) gameSession.getIO().get(2);
		Thread player = new Thread(consoleIO);
		Thread dummy1 = new Thread(dummyIO1);
		Thread dummy2 = new Thread(dummyIO2);

		gameSession.getBoard().getCurrentPlayer().startTimer();
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
					session = localInstance = new GameSession();
				}
			}
		}
		return localInstance;
	}

	private GameSession() {
		this.board = GameSessionBuilder.getBoard();
		this.actionController = GameSessionBuilder.getActionController();		
		this.propertyManager = GameSessionBuilder.getPropertyManager();
		this.userIO = GameSessionBuilder.getUsersIO();
	}

	public static class GameSessionBuilder {
		private static Board board;
		private static PropertyManager propertyManager;
		private static ActionController actionController;		
		private static Map<User, IO> userIO = new HashMap<User, IO>();
		private static int maxPlayers;
		private static int startMoney;

		private GameSessionBuilder() {
		}

		public static void setBoard(Board board) {
			GameSessionBuilder.board = board;
		}

		public static Board getBoard() {
			return board;
		}

		public static void setPropertyManager(PropertyManager propertyManager) {
			GameSessionBuilder.propertyManager = propertyManager;
		}

		public static PropertyManager getPropertyManager() {
			return propertyManager;
		}

		public static void setActionController(ActionController actionController) {
			GameSessionBuilder.actionController = actionController;
		}

		public static ActionController getActionController() {
			return actionController;
		}		

		public static void setUsersIO(Map<User, IO> userIO) {
			GameSessionBuilder.userIO = userIO;
		}

		public static Map<User, IO> getUsersIO() {
			return userIO;
		}

		public static int getMaxPlayers() {
			return maxPlayers;
		}

		public static void setMaxPlayers(int maxPlayers) {
			GameSessionBuilder.maxPlayers = maxPlayers;
		}

		public static int getStartMoney() {
			return startMoney;
		}

		public static void setStartMoney(int startMoney) {
			GameSessionBuilder.startMoney = startMoney;
		}
	}

	public synchronized static SessionStatus getStatus() {
		return status;
	}

	public synchronized static void setStatus(SessionStatus status) {
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
		cells.add(new PropertyCell("c1m1", "c1m1 desc", cells.size(), null, 1000, 200, monopoly1));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m1", "c2m1 desc", cells.size(), null, 1000, 200, monopoly1));
		cells.add(new PropertyCell("c3m1", "c3m1 desc", cells.size(), null, 1000, 200, monopoly1));
		cells.add(new PropertyCell("c1m9", "c1m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new PropertyCell("c1m2", "c1m2 desc", cells.size(), null, 1500, 300, monopoly2));
		cells.add(new PropertyCell("c2m2", "c2m2 desc", cells.size(), null, 1500, 300, monopoly2));
		MoneyEvent incomeTax = new MoneyEvent("Уплатите налог", "Подоходный налог -$200");
		incomeTax.setStartCash(-200);
		cells.add(new SingleEventCell("Уплатите налог", "Подоходный налог -$200", cells.size(), incomeTax));
		cells.add(new PropertyCell("c3m2", "c3m2 desc", cells.size(), null, 1500, 300, monopoly2));
		Event jailEvent = new JailEvent("Тюрьма", "Это место лучше пройти мимо");
		cells.add(new SingleEventCell("Тюрьма", "Это место лучше пройти мимо", cells.size(), jailEvent));
		cells.add(new PropertyCell("c1m3", "c1m3 desc", cells.size(), null, 3000, 300, monopoly3));
		cells.add(new PropertyCell("c2m3", "c2m3 desc", cells.size(), null, 3000, 300, monopoly3));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c3m3", "c3m3 desc", cells.size(), null, 3000, 300, monopoly3));
		cells.add(new PropertyCell("c2m9", "c2m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c1m4", "c1m4 desc", cells.size(), null, 3300, 330, monopoly4));
		MoneyEvent luxTax = new MoneyEvent("Уплатите налог", "Налог на роскошь");
		luxTax.setStartCash(-100);
		cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь -$100", cells.size(), luxTax));
		cells.add(new PropertyCell("c2m4", "c2m4 desc", cells.size(), null, 3300, 330, monopoly4));
		MoneyEvent freePlace = new MoneyEvent("Событие в Тюрьму", "Место для События В тюрьму");
		freePlace.setStartCash(0);
		cells.add(new SingleEventCell("Бесплатная стоянка", "Можете передохнуть", cells.size(), freePlace));
		cells.add(new PropertyCell("c1m5", "c1m5 desc", cells.size(), null, 3500, 350, monopoly5));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m5", "c2m5 desc", cells.size(), null, 3500, 350, monopoly5));
		MoneyEvent turnEvent = new MoneyEvent("Событие в Тюрьму", "Место для События В тюрьму");
		turnEvent.setStartCash(0);
		cells.add(new SingleEventCell("Событие Хода", "Место для События хода", cells.size(), turnEvent));
		cells.add(new PropertyCell("c3m9", "c3m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new PropertyCell("c1m6", "c1m6 desc", cells.size(), null, 4000, 400, monopoly6));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m6", "c2m6 desc", cells.size(), null, 4000, 400, monopoly6));
		cells.add(new PropertyCell("c3m6", "c3m6 desc", cells.size(), null, 4000, 400, monopoly6));
		Event goToJail = new GoToJailEvent("Событие в Тюрьму", "Отправляйтесь в тюьму");
		cells.add(new SingleEventCell("Событие в Тюрьму", "Отправляйтесь в тюьму", cells.size(), goToJail));
		cells.add(new PropertyCell("c1m7", "c1m7 desc", cells.size(), null, 4200, 420, monopoly7));
		cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь", cells.size(), luxTax));
		cells.add(new PropertyCell("c2m7", "c2m7 desc", cells.size(), null, 4200, 420, monopoly7));
		cells.add(new PropertyCell("c3m7", "c3m7 desc", cells.size(), null, 4200, 420, monopoly7));
		cells.add(new PropertyCell("c4m9", "c4m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new PropertyCell("c1m8", "c1m8 desc", cells.size(), null, 4500, 450, monopoly8));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("c2m8", "c2m8 desc", cells.size(), null, 4500, 450, monopoly8));
		cells.add(new PropertyCell("c3m8", "c3m8 desc", cells.size(), null, 4500, 450, monopoly8));

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
		return new ArrayList<>(userIO.values());
	}

	@Override
	public synchronized void close() {
		session = null;
		setStatus(SessionStatus.NOT_EXISTS);
		GameSessionBuilder.setActionController(null);
		GameSessionBuilder.setBoard(null);
		GameSessionBuilder.getUsersIO().clear();
		GameSessionBuilder.setPropertyManager(null);
	}

	@Override
	public IO getUserIO(User user) {
		return userIO.get(user);
	}
	
	@Override
	public Set<User> getUsers() {
		return userIO.keySet();
	}
}
