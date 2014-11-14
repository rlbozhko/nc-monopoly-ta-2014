package com.monopoly.board.events;

import java.util.Random;

import com.monopoly.board.Board;
import com.monopoly.board.players.Player;
import com.monopoly.tools.XORShiftRandom;

public class MoneyEvent {
	private final int MAX_VALUE_MONEY_FOR_MONEYEVENT = 200;
	private String eventName;
	private String descrition;

	public MoneyEvent(String eventName, String descrition) {
		this.eventName = eventName;
		this.descrition = descrition;
	}

	Random random = new Random();
	XORShiftRandom xorShiftRandom = new XORShiftRandom();
	
	public void doAction(Board board) {
		Player player = board.currentPlayer();
		boolean isAddMoney = random.nextBoolean();
		if (isAddMoney) {
			player.getWallet().addMoney(xorShiftRandom.nextInt(MAX_VALUE_MONEY_FOR_MONEYEVENT));
		} else {
			player.getWallet().subtractMoney(xorShiftRandom.nextInt(MAX_VALUE_MONEY_FOR_MONEYEVENT));;
		}

	}

}
