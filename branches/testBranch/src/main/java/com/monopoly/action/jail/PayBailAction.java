package com.monopoly.action.jail;

import com.monopoly.action.Action;
import com.monopoly.action.ActionType;
import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.io.IO;

public class PayBailAction implements Action {
	public final static ActionType type = ActionType.PAY_BAIL;
	private final int BAIL_RATE = 400;
	private final int RESET_JAIL_TERM = 0;

	@Override
	public void performAction(Player player) {
		IO playerIO = ActionUtils.getPlayerIO(player);
		int bail = BAIL_RATE * player.getJailTerm();
		if (player.subtractMoney(bail)) {
			playerIO.showMessage("Вы заплатили залог в размере $" + bail + ". "
					+ "Впредь будьте более удачливы. Можете быть свободны.");
			ActionUtils.sendMessageToAll(player.getName() + " заплатил залог в размере $" + bail + " и вышел из тюрьмы");
			player.setJailTerm(RESET_JAIL_TERM);
			player.setJailStatus(Status.CLEAN);
		} else {
			playerIO.showWarning("Недостаточно денег для выплаты залога. Требуется $" + bail);
		}
	}

	@Override
	public String getName() {
		return "Pay bail";
	}
	
	@Override
	public ActionType getType() {		
		return type;
	}
}
