package com.monopoly.action.jail;

import com.monopoly.action.Action;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.EndTurnAction;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.io.IO;
import com.monopoly.tools.XORShiftStrategy;

public class EscapeAction implements Action {

    private final int ESCAPE_COMBINATION = 11;
    private final int PUNISHMENT = 3;

    XORShiftStrategy combination = new XORShiftStrategy();

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);
        int goodLuckCombination = combination.nextInt(12);
        if (goodLuckCombination >= ESCAPE_COMBINATION) {
            player.setJailStatus(Status.ESCAPE);
            playerIO.showMessage("Поздравляем!!! Вам удалось совершить побег. но находитесь в рoзыске"+ player.getJailTerm());
            player.goToPosition(ESCAPE_COMBINATION);
        } else {
            playerIO.showMessage("План побега не удался. За попытку побега вам " +
                    "добавили к сроку + " + PUNISHMENT);
            player.setJailTerm(player.getJailTerm() + PUNISHMENT);
            new EndTurnAction().performAction(player);
        }
    }

    @Override
    public String getName() {
        return "Escape from jail";
    }
}
