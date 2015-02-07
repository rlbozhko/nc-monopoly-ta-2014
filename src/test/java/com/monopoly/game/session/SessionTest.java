package com.monopoly.game.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.monopoly.action.ActionType;
import com.monopoly.action.controller.PlayerActionController;
import com.monopoly.bean.User;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.events.GoToJailEvent;
import com.monopoly.board.events.MoneyEvent;
import com.monopoly.board.events.MoveEvent;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession.GameSessionBuilder;
import com.monopoly.io.ConsoleIO;
import com.monopoly.io.DummyIO;
import com.monopoly.io.IO;

public class SessionTest {
	private static final int START_MONEY = 5000;

	public static void main(String[] args) {
		List<Player> players = new ArrayList<>();
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		Player p3 = new Player("Player 3");

		p1.setStatus(Status.START_TURN);
		//p1.setStatus(Status.SKIP_TURN);
		//p2.setStatus(Status.START_TURN);
		players.add(p1);
		players.add(p2);
		players.add(p3);

		Map<User, IO> usersIO = new HashMap<User, IO>();
		usersIO.put(new User("p1@test.ua", "123", "hash1", "p1"), new ConsoleIO(p1));
		usersIO.put(new User("p2@test.ua", "123", "hash2", "p2"), new DummyIO(p2));
		usersIO.put(new User("p3@test.ua", "123", "hash3", "p3"), new DummyIO(p3));
		
		GameSessionBuilder.setBoard(GameSession.newBoard(players, START_MONEY));
		GameSessionBuilder.setActionController(new PlayerActionController());
		GameSessionBuilder.setPropertyManager(new PropertyManager(players));
		GameSessionBuilder.setUsersIO(usersIO);

		Session test = GameSession.getInstance();

		ConsoleIO consoleIO = (ConsoleIO) test.getIO().get(0);
		DummyIO dummyIO1 = (DummyIO) test.getIO().get(1);
		DummyIO dummyIO2 = (DummyIO) test.getIO().get(2);
		Thread player = new Thread(consoleIO);
		Thread dummy1 = new Thread(dummyIO1);
		Thread dummy2 = new Thread(dummyIO2);

		
		PropertyManager testPropertyManager = test.getPropertyManager();

		Property testProperty1 = (Property) test.getBoard().getCells().get(1);
		Property testProperty2 = (Property) test.getBoard().getCells().get(3);
		Property testProperty3 = (Property) test.getBoard().getCells().get(4);

		testPropertyManager.setPropertyOwner(p1, testProperty1);
		testPropertyManager.setPropertyOwner(p1, testProperty2);
		testPropertyManager.setPropertyOwner(p1, testProperty3);
		//p1.addMoney(-10000);
		//p1.subtractMoney(START_MONEY - 1);
		// p3.addMoney(5000);*/
		//
		test.getBoard().getCurrentPlayer().startTimer();
		player.start();
		dummy1.start();
		dummy2.start();
		//((EventCell) GameSession.getInstance().getBoard().getGoToJailCell()).getEvent().performEvent();
		new MoveEvent("", "").performEvent();
	}
}
