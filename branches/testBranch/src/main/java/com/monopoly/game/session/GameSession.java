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
import com.monopoly.board.cells.MonopolyType;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.cells.RandomEventCell;
import com.monopoly.board.cells.SingleEventCell;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.events.EmergencyEvent;
import com.monopoly.board.events.Event;
import com.monopoly.board.events.ExtraTurnEvent;
import com.monopoly.board.events.GoToJailEvent;
import com.monopoly.board.events.JailEvent;
import com.monopoly.board.events.MoneyEvent;
import com.monopoly.board.events.MoveEvent;
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
	private long id;
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
		usersIO.put(new User("p1@test.ua", "123", "hash1", "p1"), new ConsoleIO(p1));
		usersIO.put(new User("p2@test.ua", "123", "hash2", "p2"), new DummyIO(p2));
		usersIO.put(new User("p3@test.ua", "123", "hash3","p3"), new DummyIO(p3));
		
		
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
					Dice.getInstance();
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
		this.id = GameSessionBuilder.getId();
//		this.id = System.currentTimeMillis();
	}

	public static class GameSessionBuilder {
		private static Board board;
		private static PropertyManager propertyManager;
		private static ActionController actionController;		
		private static Map<User, IO> userIO = new HashMap<User, IO>();
		private static int maxPlayers;
		private static int startMoney;
		private static long id;

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

		public static long getId() {
			return id;
		}

		public static void setId(long id) {
			GameSessionBuilder.id = id;
		}
	}

	public synchronized static SessionStatus getStatus() {
		return status;
	}

	public synchronized static void setStatus(SessionStatus status) {
		GameSession.status = status;
	}

	public static Board newBoard(List<Player> players, int startMoney) {
		Monopoly monopoly1 = new Monopoly(MonopolyType.MONOPOLY1);
		Monopoly monopoly2 = new Monopoly(MonopolyType.MONOPOLY2);
		Monopoly monopoly3 = new Monopoly(MonopolyType.MONOPOLY3);
		Monopoly monopoly4 = new Monopoly(MonopolyType.MONOPOLY4);
		Monopoly monopoly5 = new Monopoly(MonopolyType.MONOPOLY5);
		Monopoly monopoly6 = new Monopoly(MonopolyType.MONOPOLY6);
		Monopoly monopoly7 = new Monopoly(MonopolyType.MONOPOLY7);
		Monopoly monopoly8 = new Monopoly(MonopolyType.MONOPOLY8);
		Monopoly monopoly9 = new Monopoly(MonopolyType.MONOPOLY9);

		List<Event> chanceEvents = new ArrayList<>();
		chanceEvents.add(new EmergencyEvent("Случился пожар"));
		chanceEvents.add(new MoneyEvent(" повезло и он получил $200"));
		MoneyEvent additionalOutcome = new MoneyEvent("проиграл $200");
		additionalOutcome.setStartCash(-200);
		chanceEvents.add(additionalOutcome);
		Event goToJail = new GoToJailEvent("Отправляйтесь в тюьму");
		chanceEvents.add(goToJail);
		chanceEvents.add(new RandomMoneyEvent("Может выиграете, а может и проиграете"));
		Event moveEvent = new MoveEvent("Перейти на другую ячейку");
		chanceEvents.add(moveEvent);
		chanceEvents.add(new ExtraTurnEvent("Получите дополнительный ход"));
		
		List<Cell> cells = new ArrayList<>();
		cells.add(new SingleEventCell("Начало", "Начало", 0, new MoneyEvent("прошел полный круг и Получил $200")));
		cells.add(new PropertyCell("Microsoft", "c1m1 desc", cells.size(), null, 1000, 200, monopoly1));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("Adobe", "c2m1 desc", cells.size(), null, 1000, 200, monopoly1));
		cells.add(new PropertyCell("Oracle", "c3m1 desc", cells.size(), null, 1000, 200, monopoly1));
		cells.add(new PropertyCell("Google", "c1m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new PropertyCell("Яндекс", "c1m2 desc", cells.size(), null, 1500, 300, monopoly2));
		cells.add(new PropertyCell("YouTube", "c2m2 desc", cells.size(), null, 1500, 300, monopoly2));
		MoneyEvent incomeTax = new MoneyEvent("уплатил подоходный налог -$200");
		incomeTax.setStartCash(-200);
		cells.add(new SingleEventCell("Уплатите налог", "Подоходный налог -$200", cells.size(), incomeTax));
		cells.add(new PropertyCell("IBM", "c3m2 desc", cells.size(), null, 1500, 300, monopoly2));
		Event jailEvent = new JailEvent("Это место лучше пройти мимо");
		cells.add(new SingleEventCell("Тюрьма", "Это место лучше пройти мимо", cells.size(), jailEvent));
		cells.add(new PropertyCell("Intel", "c1m3 desc", cells.size(), null, 3000, 300, monopoly3));
		cells.add(new PropertyCell("Nokia", "c2m3 desc", cells.size(), null, 3000, 300, monopoly3));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("Sony", "c3m3 desc", cells.size(), null, 3000, 300, monopoly3));
		cells.add(new PropertyCell("AT&T", "c2m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("DataGroup", "c1m4 desc", cells.size(), null, 3300, 330, monopoly4));
		MoneyEvent luxTax = new MoneyEvent("уплатил налог на роскошь -$100");
		luxTax.setStartCash(-100);
		cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь -$100", cells.size(), luxTax));
		cells.add(new PropertyCell("Life", "c2m4 desc", cells.size(), null, 3300, 330, monopoly4));
		MoneyEvent freePlace = new MoneyEvent("Можете передохнуть");
		freePlace.setStartCash(0);
		cells.add(new SingleEventCell("Бесплатная стоянка", "Можете передохнуть", cells.size(), freePlace));
		cells.add(new PropertyCell("Amazon", "c1m5 desc", cells.size(), null, 3500, 350, monopoly5));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("Ebay", "c2m5 desc", cells.size(), null, 3500, 350, monopoly5));
		cells.add(new SingleEventCell("Событие Хода", "Переместит вперед или назад", cells.size(), moveEvent));
		cells.add(new PropertyCell("Facebook", "c3m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new PropertyCell("VK", "c1m6 desc", cells.size(), null, 4000, 400, monopoly6));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("Twitter", "c2m6 desc", cells.size(), null, 4000, 400, monopoly6));
		cells.add(new PropertyCell("Apple", "c3m6 desc", cells.size(), null, 4000, 400, monopoly6));
		cells.add(new SingleEventCell("В Тюрьму", "Отправляйтесь в тюрьму", cells.size(), goToJail));
		cells.add(new PropertyCell("Lenovo", "c1m7 desc", cells.size(), null, 4200, 420, monopoly7));
		cells.add(new SingleEventCell("Уплатите налог", "Налог на роскошь", cells.size(), luxTax));
		cells.add(new PropertyCell("Udacity", "c2m7 desc", cells.size(), null, 4200, 420, monopoly7));
		cells.add(new PropertyCell("Cryteck", "c3m7 desc", cells.size(), null, 4200, 420, monopoly7));
		cells.add(new PropertyCell("Avast", "c4m9 desc", cells.size(), null, 2000, 250, monopoly9));
		cells.add(new PropertyCell("Instagram", "c1m8 desc", cells.size(), null, 4500, 450, monopoly8));
		cells.add(new RandomEventCell("Шанс", "Случайное событие", cells.size(), chanceEvents));
		cells.add(new PropertyCell("EA", "c2m8 desc", cells.size(), null, 4500, 450, monopoly8));
		cells.add(new PropertyCell("Dr.Web", "c3m8 desc", cells.size(), null, 4500, 450, monopoly8));

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
	public void close() {
		synchronized (GameSession.class) {
			setStatus(SessionStatus.NOT_EXISTS);
			session = null;
			GameSessionBuilder.setActionController(null);
			GameSessionBuilder.setBoard(null);
			GameSessionBuilder.getUsersIO().clear();
			GameSessionBuilder.setPropertyManager(null);
			Monopoly.resetAll();
			Dice.getInstance().finish();
		}
	}

	@Override
	public IO getUserIO(User user) {
		return userIO.get(user);
	}
	
	@Override
	public Set<User> getUsers() {
		return userIO.keySet();
	}

	@Override
	public long getId() {
		return id;
	}
}
