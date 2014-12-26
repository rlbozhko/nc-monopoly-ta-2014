package com.monopoly.action.jail;

import com.monopoly.action.Action;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.EndTurnAction;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.io.IO;

public class ServeJailTermAction implements Action {

    EndTurnAction end = new EndTurnAction();

    @Override
    public void performAction(Player player) {
        if (player.getJailTerm() > 0) {
            IO playerIO = ActionUtils.getPlayerIO(player);
            //добавить склонение слова ход
            playerIO.showMessage("Вам еще сидеть " + player.getJailTerm() + " ходов");
            player.subtractJailTerm();
            new EndTurnAction().performAction(player);
            if (player.getJailTerm() == 0) {
                player.setJailStatus(Status.CLEAN);
            }
        }
    }

    @Override
    public String getName() {
        return "Кантоваться(отсидеть срок)";
    }
}
