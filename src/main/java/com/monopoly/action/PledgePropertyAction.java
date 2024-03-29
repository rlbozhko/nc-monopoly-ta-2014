package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.cells.PropertyStatus;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 11.12.2014.
 */  

public class PledgePropertyAction implements Action {
    public static final double MAX_PERCENT_FROM_PROPERTY = 0.7;
    public static final int MAX_TURNS_TO_PAY_BACK = 1;
    public static final double MAX_PLEDGE_PERCENT_PER_TURN = 0.1;
    public static final ActionType type = ActionType.PLEDGE_PROPERTY;

    @Override
    public void performAction(Player player) {
        IO playerIO = ActionUtils.getPlayerIO(player);

        PropertyCell property = (PropertyCell) selectPropertyToPledge(player, playerIO);
        if (property != null) {
            //input possible
        	int turnsToPayBack = MAX_TURNS_TO_PAY_BACK;
            int payment = (int) (property.getPrice() * MAX_PERCENT_FROM_PROPERTY);
            double pledgePercent = MAX_PLEDGE_PERCENT_PER_TURN;
            
            if (playerIO.yesNoDialog("Хотите заложить " + property.getName() + " за $" + payment + "." +
                    " На " + turnsToPayBack + " ходов")) {
                player.addMoney(payment);
                property.setStatus(PropertyStatus.PLEDGED);
                property.setTurnsToPayBack(turnsToPayBack);
                property.setPledgePercent(pledgePercent);
                property.setPayBackMoney(payment);
                ActionUtils.sendMessageToAll(player.getName() + " заложил " + property.getName() + " за $" + payment 
                		+ ". На " + turnsToPayBack + " ходов");
            }
        }
    }

    private Property selectPropertyToPledge(Player player, IO playerIO) {
        Property property = playerIO.selectProperty(player);
        if (property != null) {
            if (property.isPledged()) {
                playerIO.showWarning("Нельзя заложить уже заложеную собственность!!!");
            } else if (property.hasBuilding()) {
                playerIO.showWarning("Нельзя заложить собственность с постройками!!!");
            } else {
                return property;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "Pledge Property";
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
