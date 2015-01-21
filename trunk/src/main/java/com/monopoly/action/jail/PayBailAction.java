package com.monopoly.action.jail;

import com.monopoly.action.Action;
import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class PayBailAction implements Action {

	private final int BAIL_RATE = 400;
	private final int RESET_JAIL_TERM = 0;

	@Override
	public void performAction(Player player) {
		IO playerIO = ActionUtils.getPlayerIO(player);
		int bail = BAIL_RATE * player.getJailTerm();
		if (player.subtractMoney(bail)) {
			playerIO.showMessage("Вы заплатили залог в размере $" + bail + ".\n "
					+ "Впредь будьте более удачливы. Можете быть свободны.");
			player.setJailTerm(RESET_JAIL_TERM);
		} else {
			playerIO.showMessage("Недостаточно денег для выплаты залога. Требуется $" + bail);
		}
	}

	@Override
	public String getName() {
		return "Pay bail";
	}
}
