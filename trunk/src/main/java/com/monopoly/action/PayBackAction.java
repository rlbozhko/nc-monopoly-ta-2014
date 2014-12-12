package com.monopoly.action;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyStatus;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 12.12.2014.
 */
public class PayBackAction implements Action {

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);
        Property property = playerIO.selectProperty(player);
        if (property != null && property.isPledged()) {
            int payBackMoney = property.getPayBackMoney();
            if (playerIO.yesNoDialog("Выкупить " + ((Cell) property).getName() + " за $" + payBackMoney + "?")) {
                if (payBackMoney <= player.getMoney()) {
                    player.subtractMoney(payBackMoney);
                    property.setStatus(PropertyStatus.UNPLEDGED);
                    playerIO.showMessage(((Cell) property).getName() + " выкуплено за " + property.getPayBackMoney());
                    property.setPledgePercent(0);
                    property.setTurnsToPayBack(0);
                    property.setPledgePercent(0);
                } else {
                    playerIO.showMessage("У Вас не достаточно средств!");
                }
            }
        } else {
            playerIO.showMessage("Собственность должна быть заложена");
        }
    }

    @Override
    public String getName() {
        return "Pay Back";
    }
}
