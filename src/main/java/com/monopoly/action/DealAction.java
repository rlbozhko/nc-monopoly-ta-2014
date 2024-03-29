package com.monopoly.action;

import com.monopoly.action.deal.Deal;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 24.11.2014.
 */
public class DealAction implements Action {
	public final static ActionType type = ActionType.DEAL;
	
	@Override
    public void performAction(Player player) {        
		player.setOfferADeal(true);
		IO playerIO = ActionUtils.getPlayerIO(player);
        Player otherPlayer = playerIO.selectPlayer();
        if (otherPlayer == null) {
            playerIO.showWarning("Вы не выбрали Игрока для сделки!");
            player.setOfferADeal(false);
            return;
        }

        IO otherIO = ActionUtils.getPlayerIO(otherPlayer);
        Deal deal = playerIO.dealDialog(otherPlayer);
        if (deal == null) {
        	player.setOfferADeal(false);
            return;
        }
        
        boolean answer = otherIO.yesNoDialog(deal.message());
        if (answer && deal.isValid()) {
            deal.performDeal();
            playerIO.showMessage("Сделка состоялась");
            otherIO.showMessage("Сделка состоялась");
            ActionUtils.sendMessageToAll(player.getName() + " и " + otherPlayer.getName() + " заключили сделку");
        } else {
            playerIO.showMessage("Сделка не состоялась");
            otherIO.showMessage("Сделка не состоялась");
        }
    }

    @Override
    public String getName() {
        return "Deal";
    }
    
    @Override
	public int hashCode() {		
		return type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {		
		return type.equals(obj);
	}
	
	@Override
	public ActionType getType() {		
		return type;
	}
}
