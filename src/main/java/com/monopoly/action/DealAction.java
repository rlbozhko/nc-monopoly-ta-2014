package com.monopoly.action;

import com.monopoly.board.player.Player;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

import java.util.List;

/**
 * Created by Roma on 24.11.2014.
 */
public class DealAction implements Action {
    @Override
    public void performAction(Player player) {
        IO playerIO = getPlayerIO(player);
        Player otherPlayer = playerIO.selectPlayer();
        Deal deal = playerIO.dealDialog(otherPlayer);
        IO otherIO = getPlayerIO(otherPlayer);
        boolean answer = otherIO.yesNoDialog(deal.message());
        if (answer) {
            player.getWallet().subtractMoney(deal.getGiveMoney());
            otherPlayer.getWallet().addMoney(deal.getGiveMoney());

            player.getWallet().addMoney(deal.getAskMoney());
            otherPlayer.getWallet().subtractMoney(deal.getAskMoney());

            player.getProperty().remove(deal.getGiveProperty());
            otherPlayer.getProperty().add(deal.getGiveProperty());

            otherPlayer.getProperty().remove(deal.getAskProperty());
            player.getProperty().add(deal.getAskProperty());

            System.out.println("Сделка состоялась");
        } else {
            System.out.println("Сделка не состоялась");
        }



    }

    private IO getPlayerIO(Player player) {
        int index = -1;
        List<IO> ioList = TestSession.getInstance().getIO();
        for (int i = 0; i < ioList.size(); i++) {
            Player user = ioList.get(i).getUser();
            if (player.equals(user)) {
                return ioList.get(i);
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "Deal";
    }
}
