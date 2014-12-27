package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
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

    private static final int PENALTY_TURNS = 3;
	private static final double NEXT_RATE_COEFFICIENT = 1.1;
    private int currentRate;
    private int lastRate;
    private Player winner = null;
    private List<Player> participants;
    private PropertyCell property;
    private PropertyManager propertyManager;

    public AuctionAction(Property property) {
        this.property = (PropertyCell) property;
    }

    @Override
    public void performAction(Player player) {
        propertyManager = GameSession.getInstance().getPropertyManager();

        participants = new ArrayList<>(GameSession.getInstance().getBoard().getPlayers());
        ActionUtils.sendMessageToAll("Открыт аукцион на " + property.getName());

        currentRate = property.getPayBackMoney();

        findWinner();
        manageProperty();
    }

    private void findWinner() {
        while (hasNotWinner()) {
            Iterator<Player> participantsIterator = participants.iterator();
            performAuctionCircle(participantsIterator);
        }
    }

    private boolean hasNotWinner() {
	    return participants.size() > 1;
	}

	private void performAuctionCircle(Iterator<Player> participantsIterator) {
	    while (participantsIterator.hasNext()) {
	        Player participant = participantsIterator.next();
	        if (Status.FINISH != participant.getStatus()) {
	            makeOffer(participantsIterator, participant);
	        } else {
	            participantsIterator.remove();
	        }
	    }
	}

	private void makeOffer(Iterator<Player> participantsIterator, Player participant) {
	    if (!participant.equals(winner)) {
	        return;
	    }
	    IO participantIO = ActionUtils.getPlayerIO(participant);
	    if (isAcceptOffer(participant, participantIO)) {
	        winner = participant;
	        lastRate = currentRate;
	        currentRate *= NEXT_RATE_COEFFICIENT;
	        ActionUtils.sendMessageToAll(winner.getName() + " принял ставку $" + lastRate);
	    } else {
	        participantsIterator.remove();
	    }
	}

	private boolean isAcceptOffer(Player participant, IO participantIO) {
		return currentRate <= participant.getMoney() &&
	            participantIO.yesNoDialog("Принимаете ставку $" + currentRate + "?");
	}

	private void manageProperty() {
	    if (winner != null) {
	    	if (winner.subtractMoney(lastRate)) {
	    		propertyManager.setPropertyOwner(winner, property);	        
		        ActionUtils.sendMessageToAll(winner.getName() + " победил в аукционе за " + property.getName());
			} else {				
				performPenalty();
			}	        
	    } else {	        
	        propertyManager.resetPropertyOwner(property);
	    }
	    property.resetPledge();
	}

	private void performPenalty() {
		if (winner.subtractMoney(lastRate)) {
			ActionUtils.sendMessageToAll(winner.getName() + " не смог заплатить за " + property.getName() + " и ему назначили штраф");
		} else {
			ActionUtils.sendMessageToAll(winner.getName() + " не смог заплатить за " + property.getName() + " и он отправился в тюрьму");
			new GoToJailAction(PENALTY_TURNS).performAction(winner);
		}
	}

	@Override
    public String getName() {
        return "Auction";
    }
}
