package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

/**
 * Created by Roma on 27.11.2014.
 */
public class PayRentAction implements Action {

    @Override
    public void performAction(Player player) {
        PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
        Property property = (Property) player.getCurrentCell();
        Player owner = propertyManager.getPropertyOwner(property);
        IO playerIO = ActionUtils.getPlayerIO(player);
        IO ownerIO = ActionUtils.getPlayerIO(owner);

        int rent = property.getRent();
        if (player.getMoney() >= rent) {
            player.subtractMoney(rent);
            owner.addMoney(rent);
            player.setPayRent(false);
            playerIO.showMessage("Вы уплатили аренду в размере $" + rent);
            ownerIO.showMessage(player.getName() + " уплатил аренду в размере $" + rent);
        } else {
            playerIO.showMessage("Не достаточно денег для оплаты аренды");
        }
    }

    @Override
    public String getName() {
        return "Pay Rent";
    }
}
