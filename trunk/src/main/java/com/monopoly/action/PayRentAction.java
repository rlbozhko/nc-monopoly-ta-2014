package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

/**
 * Created by Roma on 27.11.2014.
 */
public class PayRentAction implements Action {

    @Override
    public void performAction(Player player) {
        Property property = (Property) TestSession.getInstance().getBoard().getCells().get(player.getPosition());
        Player owner = property.getOwner();
        IO playerIO = ActionUtils.getPlayerIO(player);
        IO ownerIO = ActionUtils.getPlayerIO(owner);

        int rent = property.getRent();
        if (player.getWallet().getMoney() >= rent) {
            player.getWallet().subtractMoney(rent);
            owner.getWallet().addMoney(rent);
            player.setMustPayRent(false);
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
