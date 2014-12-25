package com.monopoly.action;

import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class ServeJailTermAction implements Action {

    EndTurnAction end = new EndTurnAction();

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);
        player.substructJailTerm();
        //добавить склонение слова ход
        playerIO.showMessage("Вам еще сидеть " + player.getJailTerm() + " ходов");
        new EndTurnAction().performAction(player);
    }

    @Override
    public String getName() {
        return "Кантоваться(отсидеть срок)";
    }
}