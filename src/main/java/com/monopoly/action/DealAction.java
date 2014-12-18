package com.monopoly.action;

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
        Deal deal = playerIO.dealDialog(otherPlayer);
        if (deal != null) {
            IO otherIO = ActionUtils.getPlayerIO(otherPlayer);
            boolean answer = otherIO.yesNoDialog(deal.message());

            if (otherPlayer.getMoney() < deal.getAskMoney()) {
                otherIO.showMessage("У Вас не достаточно денег");
                answer = false;
            }
            if (answer) {
                player.subtractMoney(deal.getGiveMoney());
                otherPlayer.addMoney(deal.getGiveMoney());

                player.addMoney(deal.getAskMoney());
                otherPlayer.subtractMoney(deal.getAskMoney());

                for (Property property : deal.getGiveProperties()) {
                    property.setAndAddToOwner(otherPlayer);
                }

                for (Property property : deal.getAskProperties()) {
                    property.setAndAddToOwner(player);
                }

                playerIO.showMessage("Сделка состоялась");
                otherIO.showMessage("Сделка состоялась");
            } else {
                playerIO.showMessage("Сделка не состоялась");
                otherIO.showMessage("Сделка не состоялась");
            }
        }
    }

    @Override
    public String getName() {
        return "Deal";
    }
}
