package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * Created by Roma on 04.12.2014.
 */
public class AuctionAction implements Action {

    public static final double NEXT_RATE_COEFFICIENT = 1.1;

    @Override
    public void performAction(Player player) {
        List<Property> playerProperty = player.getProperty();
        while (!isEmpty(playerProperty)) {

            List<Player> participants = new ArrayList<>(TestSession.getInstance().getBoard().getPlayers());
            PropertyCell currentProperty = (PropertyCell) playerProperty.get(0);

            for (IO io : TestSession.getInstance().getIO()) {
                io.showMessage("Открыт аукцион на " + currentProperty.getName());
            }

            Player winner = null;
            int lastRate = 0;
            int currentRate = currentProperty.getPrice() / 2;
            while (participants.size() > 1) {
                Iterator<Player> participantsIterator = participants.iterator();
                while (participantsIterator.hasNext()) {
                    Player participant = participantsIterator.next();
                    if (!Status.FINISH.equals(participant.getStatus())) {
                        IO participantIO = ActionUtils.getPlayerIO(participant);
                        if (currentRate <= participant.getWallet().getMoney() &&
                                participantIO.yesNoDialog("Принимаете ставку $" + currentRate + "?")) {
                            winner = participant;
                            lastRate = currentRate;
                            currentRate *= NEXT_RATE_COEFFICIENT;
                        } else {
                            participantsIterator.remove();
                        }
                    } else {
                        participantsIterator.remove();
                    }
                }
            }
            if (winner != null && lastRate <= winner.getWallet().getMoney()) {
                winner.getWallet().subtractMoney(lastRate);
                currentProperty.setAndAddToOwner(winner);
            } else {
                currentProperty.setAndAddToOwner(null);
            }
        }
    }

    @Override
    public String getName() {
        return "Auction";
    }
}
