package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.cells.PropertyStatus;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Roma on 04.12.2014.
 */
public class AuctionAction implements Action {

    public static final double NEXT_RATE_COEFFICIENT = 1.1;

    private PropertyCell property;

    public AuctionAction(Property property) {
        this.property = (PropertyCell) property;
    }

    @Override
    public void performAction(Player player) {
        List<Player> participants = new ArrayList<>(GameSession.getInstance().getBoard().getPlayers());
        ActionUtils.sendMessageToAll("Открыт аукцион на " + property.getName());

        Player winner = null;
        int lastRate = 0;
        int currentRate = property.getPayBackMoney();
        while (participants.size() > 1) {
            Iterator<Player> participantsIterator = participants.iterator();
            while (participantsIterator.hasNext()) {
                Player participant = participantsIterator.next();
                if (Status.FINISH != participant.getStatus() && !participant.equals(winner)) {
                    IO participantIO = ActionUtils.getPlayerIO(participant);
                    if (currentRate <= participant.getMoney() &&
                            participantIO.yesNoDialog("Принимаете ставку $" + currentRate + "?")) {
                        winner = participant;
                        lastRate = currentRate;
                        currentRate *= NEXT_RATE_COEFFICIENT;
                        ActionUtils.sendMessageToAll(winner.getName() + " принял ставку $" + lastRate);
                    } else {
                        participantsIterator.remove();
                    }
                } else {
                    participantsIterator.remove();
                }
            }
        }
        if (winner != null && lastRate <= winner.getMoney()) {
            winner.subtractMoney(lastRate);
            property.setAndAddToOwner(winner);
            property.setStatus(PropertyStatus.UNPLEDGED);
            ActionUtils.sendMessageToAll(winner.getName() + " победил в аукционе за " + ((PropertyCell) property).getName());
        } else {
            //назначить Штраф или в Тюрьму
            property.setAndAddToOwner(null);
        }
    }

    @Override
    public String getName() {
        return "Auction";
    }
}
