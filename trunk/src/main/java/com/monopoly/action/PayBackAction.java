package com.monopoly.action;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 12.12.2014.
 */
public class PayBackAction implements Action {
	private Player player;
	private IO playerIO;
	private Property property;

	@Override
	public void performAction(Player aPlayer) {
		this.player = aPlayer;
		playerIO = ActionUtils.getPlayerIO(player);
		property = playerIO.selectProperty(player);

		if (property != null && property.isPledged()) {
			int payBackMoney = property.getPayBackMoney();
			if (playerIO.yesNoDialog("Выкупить " + ((Cell) property).getName() + " за $" + payBackMoney + "?")) {
				tryPayBack(payBackMoney);
			}
		} else {
			playerIO.showWarning("Эта собственность не заложена!");
		}
	}

	private void tryPayBack(int payBackMoney) {
		if (player.subtractMoney(payBackMoney)) {
			property.resetPledge();
			playerIO.showMessage(((Cell) property).getName() + " выкуплено за " + payBackMoney);
		} else {
			playerIO.showMessage("У Вас не достаточно средств!");
		}
	}

	@Override
	public String getName() {
		return "Pay Back";
	}
}
