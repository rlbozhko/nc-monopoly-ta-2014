package com.monopoly.board.cells;

import com.monopoly.board.building.Building;

/**
 * Created by Roma on 31.10.2014.
 */

/**
 * Собственность
 */
public interface Property {

	Building getBuilding();

	boolean buildBuilding(Building building);

	boolean upgradeBuilding();

	boolean sellBuilding();

	int getPrice();

	int getRent();

	Monopoly getMonopoly();

	boolean isPledged();

	void setStatus(PropertyStatus propertyStatus);

	PropertyStatus getStatus();

	boolean hasBuilding();

	int getMaxLevel();	

	void setTurnsToPayBack(int turnsToPayBack);

	void decrementTurnsToPayBack();

	int getTurnsToPayBack();

	int getPayBackMoney();

	void setPayBackMoney(int payBackMoney);

	void risePayBackMoney();

	double getPledgePercent();

	void setPledgePercent(double pledgePercent);

	void resetPledge();
}
