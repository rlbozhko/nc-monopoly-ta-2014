package com.monopoly.game.session;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.action.controller.PlayerActionController;
import com.monopoly.board.cells.Property;
import com.monopoly.board.events.JailEvent;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession.TestSessionBuilder;
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
		players.add(p1);
		players.add(p2);
		players.add(p3);

		List<IO> ios = new ArrayList<>();
		ios.add(new ConsoleIO(p1));
		ios.add(new DummyIO(p2));
		ios.add(new DummyIO(p3));

		TestSessionBuilder.setBoard(GameSession.newBoard(players, START_MONEY));
		TestSessionBuilder.setActionController(new PlayerActionController());
		TestSessionBuilder.setPropertyManager(new PropertyManager(players));
		TestSessionBuilder.setIOs(ios);

		Session test = GameSession.getInstance();

		ConsoleIO consoleIO = (ConsoleIO) test.getIO().get(0);
		DummyIO dummyIO1 = (DummyIO) test.getIO().get(1);
		DummyIO dummyIO2 = (DummyIO) test.getIO().get(2);
		Thread player = new Thread(consoleIO);
		Thread dummy1 = new Thread(dummyIO1);
		Thread dummy2 = new Thread(dummyIO2);

		// Для тестирования Действий с собственностью
		/*
		 * for (Cell property : test.getBoard().getPropertyCell()) { ((Property)
		 * property).setAndAddToOwner(p1); }
		 */
		PropertyManager testPropertyManager = test.getPropertyManager();

		Property testProperty1 = (Property) test.getBoard().getCells().get(1);
		Property testProperty2 = (Property) test.getBoard().getCells().get(3);

		testPropertyManager.setPropertyOwner(p1, testProperty1);
		testPropertyManager.setPropertyOwner(p1, testProperty2);
		// p2.subtractMoney(START_MONEY);
		// p3.addMoney(5000);*/
		//

		player.start();
		dummy1.start();
		dummy2.start();
		new JailEvent().performEvent();
	}
}
