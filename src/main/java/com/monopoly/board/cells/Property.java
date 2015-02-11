package com.monopoly.board.cells;

import com.monopoly.board.building.Building;

/**
 * Created by Roma on 31.10.2014.
 */

/**
 * Собственность
 */
public abstract class Property extends Cell {

	public Property(String name, String description, CellType cellType, int position) {
		super(name, description, cellType, position);		
	}

	abstract public Building getBuilding();

	abstract public boolean buildBuilding(Building building);

	abstract public boolean upgradeBuilding();

	abstract public boolean sellBuilding();

	abstract public int getPrice();

	abstract public int getRent();

	abstract public Monopoly getMonopoly();

	abstract public boolean isPledged();

	abstract public void setStatus(PropertyStatus propertyStatus);

	abstract public PropertyStatus getStatus();

	abstract public boolean hasBuilding();

	abstract public int getMaxLevel();	

	abstract public void setTurnsToPayBack(int turnsToPayBack);

	abstract public void decrementTurnsToPayBack();

	abstract public int getTurnsToPayBack();

	abstract public int getPayBackMoney();

	abstract public void setPayBackMoney(int payBackMoney);

	abstract public void risePayBackMoney();

	abstract public double getPledgePercent();

	abstract public void setPledgePercent(double pledgePercent);

	abstract public void resetPledge();

	abstract public boolean destroyBuilding();
}
