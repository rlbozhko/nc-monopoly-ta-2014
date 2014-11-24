package com.monopoly.board.events;

import java.util.Random;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;
import com.monopoly.tools.XORShiftRandom;

public class RandomMoneyEvent implements Event {
	private final int MAX_VALUE_MONEY_FOR_MONEYEVENT = 200;
	private String name;
	private String descrition;

	public RandomMoneyEvent(String eventName, String descrition) {
		this.name = eventName;
		this.descrition = descrition;
	}

	Random random = new Random();
	XORShiftRandom xorShiftRandom = new XORShiftRandom();

	@Override
	public void performEvent() {
		Board board = TestSession.getInstance().getBoard();
		Player player = board.getCurrentPlayer();
		IO playerIO = ActionUtils.getPlayerIO(player);
		boolean isAddMoney = random.nextBoolean();
		int amountMoney = xorShiftRandom
				.nextInt(MAX_VALUE_MONEY_FOR_MONEYEVENT);

		if (isAddMoney) {
			player.getWallet().addMoney(amountMoney);
			playerIO.showMessage("Поздравляем! Вы выйграли " + amountMoney
					+ "$.");
		} else {
			player.getWallet().subtractMoney(amountMoney);
			playerIO.showMessage("Сеголня был не Ваш день! Вы проиграли "
					+ amountMoney + "$.");
		}
	}

	@Override
	public String getDescription() {
		return descrition;
	}

	@Override
	public String getName() {
		return name;
	}

}
