package com.monopoly.action;

import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

/**
 * Created by Roma on 28.11.2014.
 */
public class BuyPropertyAction implements Action {

    @Override
    public void performAction(Player player) {
        PropertyCell property = (PropertyCell) TestSession.getInstance().getBoard().getCells().get(player.getPosition());
        IO playerIO = ActionUtils.getPlayerIO(player);
        if (playerIO.yesNoDialog("Хотите купить " + property.getName() + " за $" + property.getPrice() + "?")) {
            if (player.buyProperty(property)) {
                playerIO.showMessage("Вы купили " + property.getName() + " за $" + property.getPrice());
            } else {
                playerIO.showMessage("У Вас не достаточно средств");
            }
        }
    }

    @Override
    public String getName() {
        return "Buy Property";
    }
}
