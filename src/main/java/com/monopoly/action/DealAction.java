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
        Deal deal = playerIO.dealDialog(otherPlayer);
        if (deal != null) {
            IO otherIO = ActionUtils.getPlayerIO(otherPlayer);
            boolean answer = otherIO.yesNoDialog(deal.message());

            if (otherPlayer.getWallet().getMoney() < deal.getAskMoney()) {
                otherIO.showMessage("У Вас не достаточно денег");
                answer = false;
            }
            if (answer) {
                player.getWallet().subtractMoney(deal.getGiveMoney());
                otherPlayer.getWallet().addMoney(deal.getGiveMoney());

                player.getWallet().addMoney(deal.getAskMoney());
                otherPlayer.getWallet().subtractMoney(deal.getAskMoney());

                player.getProperty().removeAll(deal.getGiveProperties());
                otherPlayer.getProperty().addAll(deal.getGiveProperties());

                otherPlayer.getProperty().removeAll(deal.getAskProperties());
                player.getProperty().addAll(deal.getAskProperties());

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
