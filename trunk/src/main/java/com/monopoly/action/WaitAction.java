package com.monopoly.action;

import com.monopoly.board.player.Player;

/**
 * Created by Roma on 21.11.2014.
 */

/**
 * Created by Roma on 20.11.2014.
 */
public class WaitAction implements Action {
	public final static ActionType type = ActionType.WAIT;
    
    @Override
    public void performAction(Player player) {
    }

    @Override
    public String getName() {
        return "Wait";
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