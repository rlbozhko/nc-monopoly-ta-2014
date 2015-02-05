package com.monopoly.board.events;

/**
 * Create By Kulikovsky Anton
 * */

import java.util.Random;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;
import com.monopoly.tools.XORShiftStrategy;

public class MoveEvent extends BaseEvent {
	private static final int MAX_VALUE_MOVE_PLAYER = 7;

	public MoveEvent(String name, String description) {
		this.name = name;
		this.description = description;
	}

	Random random = new Random();
	XORShiftStrategy xorShiftRandom = new XORShiftStrategy();

	@Override
	public void performEvent() {
		Board board = GameSession.getInstance().getBoard();
		Player player = board.getCurrentPlayer();		
		boolean isAdvance = random.nextBoolean();

		int valueMove = xorShiftRandom.nextInt(MAX_VALUE_MOVE_PLAYER);
		if (isAdvance) {
			player.goToPosition(valueMove);
			ActionUtils.sendMessageToAll(player.getName() + "отправился на " + valueMove
					+ " клеток вперед.");
		} else {
			player.goToPosition(-valueMove);
			ActionUtils.sendMessageToAll(player.getName() + "отправился на " + valueMove
					+ " клеток назад.");			
		}
	}
}
