package com.monopoly.action;

import com.monopoly.action.deal.Deal;
import com.monopoly.action.deal.DealContainer;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 24.11.2014.
 */
public class DealAction implements Action {
    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);
        Player otherPlayer = playerIO.selectPlayer();
        if (otherPlayer == null) {
            playerIO.showMessage("Вы не выбрали Игрока для сделки");
            return;
        }

        IO otherIO = ActionUtils.getPlayerIO(otherPlayer);
        Deal deal = playerIO.dealDialog(otherPlayer);
        if (deal == null) {
            return;
        }

        boolean answer = otherIO.yesNoDialog(deal.message());
        if (answer && deal.isValid()) {
            deal.performDeal();
            playerIO.showMessage("Сделка состоялась");
            otherIO.showMessage("Сделка состоялась");
        } else {
            playerIO.showMessage("Сделка не состоялась");
            otherIO.showMessage("Сделка не состоялась");
        }
    }

    @Override
    public String getName() {
        return "Deal";
    }
}
