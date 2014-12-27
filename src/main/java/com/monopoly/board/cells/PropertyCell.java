package com.monopoly.board.cells;

import com.monopoly.board.building.Building;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.game.session.GameSession;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * Created by Roma on 06.11.2014.
 */

/**
 * ЯчейкаСобственности
 */
public class PropertyCell extends Cell implements Property {
	private List<Building> buildings;
	private int basePrice;
	private int baseRent;
	private Monopoly monopoly;
	private PropertyStatus status;
	private int maxLevel = 5;// временно
	private int maxBuildings = 1;// временно
	private int turnsToPayBack;
	private int payBackMoney;
	private double pledgePercent;

	public static class PropertyBuilder {
		private final static int DEFAULT_BASE_PRICE = 1000;
		private final static int DEFAULT_BASE_RENT = 100;
		public static final int DEFAULT_MAX_LEVEL = 5;
		public static final int DEFAULT_MAX_BUILDINGS = 1;

		private String name;
		private String description;
		private int position;
		private List<Building> buildings;
		private int basePrice;
		private int baseRent;
		private Monopoly monopoly;
		private PropertyStatus status;
		private int maxLevel;
		private int maxBuildings;
		private int turnsToPayBack;

		public PropertyBuilder(String name, Monopoly monopoly, int position) {
			this.name = name;
			this.description = name;
			this.position = position;
			this.buildings = new ArrayList<>();
			this.basePrice = DEFAULT_BASE_PRICE;
			this.baseRent = DEFAULT_BASE_RENT;
			this.monopoly = monopoly;
			this.maxLevel = DEFAULT_MAX_LEVEL;
			this.maxBuildings = DEFAULT_MAX_BUILDINGS;
			this.status = PropertyStatus.PLEDGED;
			this.turnsToPayBack = 0;
		}

		public PropertyCell getPropertyCell() {
			PropertyCell propertyCell = new PropertyCell(name, description, position, buildings, basePrice, baseRent,
					monopoly);
			propertyCell.maxLevel = maxLevel;
			propertyCell.maxBuildings = maxBuildings;
			propertyCell.status = status;
			propertyCell.turnsToPayBack = turnsToPayBack;
			return propertyCell;
		}

		public PropertyBuilder setMaxLevel(int maxLevel) {
			this.maxLevel = maxLevel;
			return this;
		}

		public PropertyBuilder setMaxBuildings(int maxBuildings) {
			this.maxBuildings = maxBuildings;
			return this;
		}

		public PropertyBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public PropertyBuilder setBasePrice(int basePrice) {
			this.basePrice = basePrice;
			return this;
		}

		public PropertyBuilder setBaseRent(int baseRent) {
			this.baseRent = baseRent;
			return this;
		}

		public PropertyBuilder setBuildings(List<Building> buildings) {
			this.buildings = buildings;
			return this;
		}

		public PropertyBuilder setTurnsToPayBack(int turnsToPayBack) {
			this.turnsToPayBack = turnsToPayBack;
			return this;
		}
	}

	public PropertyCell(String name, String description, int position, List<Building> buildings, int basePrice,
			int baseRent, Monopoly monopoly) {
		super(name, description, CellType.PROPERTY_CELL, position);
		this.basePrice = basePrice;
		this.baseRent = baseRent;
		this.monopoly = monopoly;
		monopoly.addProperty(this);
		this.buildings = buildings;
		this.status = PropertyStatus.UNPLEDGED;
	}

	@Override
	public List<Building> getBuildings() {
		return buildings;
	}

	@Override
	public boolean buildBuilding(Building building, int listIndex) {
		if (buildings.get(listIndex) == null) {
			buildings.set(listIndex, building);
		}
		return false;
	}

	@Override
	public boolean upgradeBuilding(Building building) {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		if (building != null && building.currentLevel() < building.getMaxLevel()
				&& propertyManager.getPropertyOwner(this).subtractMoney(building.currentPrice())) {
			building.levelUp();
			return true;
		}
		return false;
	}

	@Override
	public boolean sellBuilding(Building building) {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		if (building != null) {
			if (building.currentLevel() > 1) {
				building.levelDown();
			} else {
				building = null;
			}
			propertyManager.getPropertyOwner(this).addMoney(building.currentPrice() / 2);
			return true;
		}
		return false;
	}

	@Override
	public int getPrice() {
		return basePrice;
	}

	@Override
	public int getRent() {
		return baseRent;
	}

	@Override
	public Monopoly getMonopoly() {
		return monopoly;
	}

	@Override
	public boolean isPledged() {
		return PropertyStatus.PLEDGED == status;
	}

	@Override
	public void setStatus(PropertyStatus propertyStatus) {
		this.status = propertyStatus;
	}

	@Override
	public PropertyStatus getStatus() {
		return status;
	}

	@Override
	public boolean hasBuildings() {
		if (!isEmpty(buildings)) {
			for (Building building : buildings) {
				if (building != null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getMaxLevel() {
		return maxLevel;
	}

	@Override
	public int getMaxBuildings() {
		return maxBuildings;
	}

	@Override
	public void setTurnsToPayBack(int turnsToPayBack) {
		this.turnsToPayBack = turnsToPayBack;
	}

	@Override
	public void decrementTurnsToPayBack() {
		turnsToPayBack--;
	}

	@Override
	public int getTurnsToPayBack() {
		return turnsToPayBack;
	}

	@Override
	public int getPayBackMoney() {
		return payBackMoney;
	}

	@Override
	public void setPayBackMoney(int payBackMoney) {
		this.payBackMoney = payBackMoney;
	}

	@Override
	public void risePayBackMoney() {
		payBackMoney = payBackMoney * (1 + payBackMoney);
	}

	@Override
	public double getPledgePercent() {
		return pledgePercent;
	}

	@Override
	public void setPledgePercent(double pledgePercent) {
		this.pledgePercent = pledgePercent;
	}

	@Override
	public void resetPledge() {
		setStatus(PropertyStatus.UNPLEDGED);
		setTurnsToPayBack(0);
		setPledgePercent(0);
		setPayBackMoney(0);
	}
}
