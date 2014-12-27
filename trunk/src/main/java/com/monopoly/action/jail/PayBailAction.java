package com.monopoly.action.jail;

import com.monopoly.action.Action;
import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class PayBailAction implements Action {

    private final int BAIL = 2000;
    private final int RESET_JAIL_TERM = 0;

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);
        if (player.subtractMoney(BAIL)) {            
            playerIO.showMessage("Вы заплатили залог в размере " + BAIL + "$.\n " +
                    "Впредь будьте более удачливы. Можете быть свободны.");
            player.setJailTerm(RESET_JAIL_TERM);

        } else {
            playerIO.showMessage("Недостаточно денег для выплаты залога.");
        }
    }

    @Override
    public String getName() {
        return "Pay bail";
    }
}

