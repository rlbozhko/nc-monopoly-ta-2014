package com.monopoly.board.events;

import java.util.Random;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;
import com.monopoly.tools.XORShiftStrategy;

public class RandomMoneyEvent extends BaseEvent {
	private static final int MAX_VALUE_MONEY_FOR_MONEYEVENT = 200;

	public RandomMoneyEvent(String name, String description) {
		this.name = name;
		this.description = description;
	}

	Random random = new Random();
	XORShiftStrategy xorShiftRandom = new XORShiftStrategy();

	@Override
	public void performEvent() {
		Board board = GameSession.getInstance().getBoard();
		Player player = board.getCurrentPlayer();
		IO playerIO = ActionUtils.getPlayerIO(player);
		boolean isAddMoney = random.nextBoolean();
		int amountMoney = xorShiftRandom
				.nextInt(MAX_VALUE_MONEY_FOR_MONEYEVENT);

		if (isAddMoney) {
			player.addMoney(amountMoney);
			playerIO.showMessage("Поздравляем! Вы выйграли $" + amountMoney
					+ ".");
		} else {
			player.addMoney(-amountMoney);
			playerIO.showMessage("Сеголня был не Ваш день! Вы проиграли $"
					+ amountMoney + ".");
		}
	}

}
