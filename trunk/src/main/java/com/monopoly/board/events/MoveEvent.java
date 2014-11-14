package com.monopoly.board.events;

import java.util.Random;

import com.monopoly.board.Board;
import com.monopoly.board.players.Player;
import com.monopoly.tools.XORShiftRandom;

public class MoveEvent {
	private final int MAX_VALUE_MOVE_PLAYER = 7;
	private String eventName;
	private String descrition;

	public MoveEvent(String eventName, String descrition) {
		this.eventName = eventName;
		this.descrition = descrition;
	}

	Random random = new Random();
	XORShiftRandom xorShiftRandom = new XORShiftRandom();

	// TODO if (position + MAX_VALUE_MOVE_PLAYER) > board lenght
	public void doAction(Board board) {
		Player player = board.currentPlayer();
		boolean isAdvance = random.nextBoolean();
		if (isAdvance) {
			player.goToPosition(player.getPosition()
					+ xorShiftRandom.nextInt(MAX_VALUE_MOVE_PLAYER));
		} else {
			player.goToPosition(player.getPosition()
					- xorShiftRandom.nextInt(MAX_VALUE_MOVE_PLAYER));
			
		}

	}

}
