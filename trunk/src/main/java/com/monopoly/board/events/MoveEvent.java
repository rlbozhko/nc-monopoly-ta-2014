package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;
import com.monopoly.tools.XORShiftRandom;

import java.util.Random;

public class MoveEvent implements Event {
	private final int MAX_VALUE_MOVE_PLAYER = 7;
	private String name;
	private String description;

	public MoveEvent(String eventName, String description) {
		this.name = eventName;
		this.description = description;
	}

	Random random = new Random();
	XORShiftRandom xorShiftRandom = new XORShiftRandom();

	@Override
	public void performEvent() {
		Board board = TestSession.getInstance().getBoard();
		Player player = board.getCurrentPlayer();
		IO playerIO = ActionUtils.getPlayerIO(player);
		boolean isAdvance = random.nextBoolean();

		int valueMove = xorShiftRandom.nextInt(MAX_VALUE_MOVE_PLAYER);
		if (isAdvance) {
			player.goToPosition(valueMove);
			playerIO.showMessage("Отправляйтесь на " + valueMove + " клеток вперед.");
		} else {
			player.goToPosition(-valueMove);
			playerIO.showMessage("Отправляйтесь на " + valueMove + " клеток назад.");
		}
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

}
