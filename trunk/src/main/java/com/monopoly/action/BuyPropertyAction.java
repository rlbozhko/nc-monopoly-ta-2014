package com.monopoly.action;

import java.util.HashSet;
import java.util.Set;

import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

/**
 * Created by Roma on 28.11.2014.
 */

public class BuyPropertyAction implements Action {	
	public final static ActionType type = ActionType.BUY_PROPERTY;
	private static Set<Player> buyers = new HashSet<Player>();
	
    @Override
    public void performAction(Player player) {
    	IO playerIO = ActionUtils.getPlayerIO(player);
    	if (buyers.contains(player)) {
    		playerIO.showWarning("Можно совершать только одну покупку одновременно");
    		return;
    	}
    	
    	buyers.add(player);
    	
        PropertyCell property = (PropertyCell) player.getCurrentCell();        
        PropertyManager pm = GameSession.getInstance().getPropertyManager();
        if (playerIO.yesNoDialog("Хотите купить " + property.getName() + " за $" + property.getPrice() + "?") 
        		&& pm.getPropertyOwner(property) == null) {
            if (player.buyProperty(property)) {
                ActionUtils.sendMessageToAll(player.getName() + " купил " + property.getName() + " за $" + property.getPrice());
            } else {
                playerIO.showWarning("У Вас не достаточно средств");
            }
        } else {
        	playerIO.showWarning("Не удалось купить " + property.getName());
        }
        
        buyers.remove(player);
    }
		
    @Override
    public String getName() {
        return "Buy Property";
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
