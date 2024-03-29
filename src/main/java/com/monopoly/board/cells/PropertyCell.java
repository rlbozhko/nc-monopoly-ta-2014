package com.monopoly.board.cells;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.building.Building;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.entity.PropertyCellEntity;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

/**
 * Created by Roma on 06.11.2014.
 */

/**
 * ЯчейкаСобственности
 */
public class PropertyCell extends Property {
	private static final double PAY_BACK_RATE = 0.1;
	private static final float RENT_RATE = 0.4F;
	private Building building;
	private int basePrice;
	private int baseRent;
	private Monopoly monopoly;
	private PropertyStatus status;
	private int maxLevel = 5;// временно	
	private int turnsToPayBack;
	private int payBackMoney;
	private double pledgePercent;	

	public static class PropertyBuilder {
		private final static int DEFAULT_BASE_PRICE = 1000;
		private final static int DEFAULT_BASE_RENT = 100;
		public static final int DEFAULT_MAX_LEVEL = 5;		

		private String name;
		private String description;
		private int position;
		private Building building;
		private int basePrice;
		private int baseRent;
		private Monopoly monopoly;
		private PropertyStatus status;
		private int maxLevel;		
		private int turnsToPayBack;

		public PropertyBuilder(String name, Monopoly monopoly, int position) {
			this.name = name;
			this.description = name;
			this.position = position;
			this.building = null;
			this.basePrice = DEFAULT_BASE_PRICE;
			this.baseRent = DEFAULT_BASE_RENT;
			this.monopoly = monopoly;
			this.maxLevel = DEFAULT_MAX_LEVEL;			
			this.status = PropertyStatus.PLEDGED;
			this.turnsToPayBack = 0;
		}

		public PropertyCell getPropertyCell() {
			PropertyCell propertyCell = new PropertyCell(name, description, position, building, basePrice, baseRent,
					monopoly);
			propertyCell.maxLevel = maxLevel;			
			propertyCell.status = status;
			propertyCell.turnsToPayBack = turnsToPayBack;
			return propertyCell;
		}

		public PropertyBuilder setMaxLevel(int maxLevel) {
			this.maxLevel = maxLevel;
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

		public PropertyBuilder setBuilding(Building building) {
			this.building = building;
			return this;
		}

		public PropertyBuilder setTurnsToPayBack(int turnsToPayBack) {
			this.turnsToPayBack = turnsToPayBack;
			return this;
		}
	}

	public PropertyCell(String name, String description, int position, Building building, int basePrice, int baseRent,
			Monopoly monopoly) {
		super(name, description, CellType.PROPERTY_CELL, position);
		this.basePrice = basePrice;
		this.baseRent = baseRent;
		this.monopoly = monopoly;
		monopoly.addProperty(this);
		this.building = building;
		this.status = PropertyStatus.UNPLEDGED;
	}	
	
	public PropertyCell(PropertyCellEntity entity) {
		super(entity.getName(), entity.getDescription(), CellType.PROPERTY_CELL, entity.getPosition());
		this.basePrice = entity.getBasePrice();
		this.baseRent = entity.getBaseRent();
		this.building = entity.getBuilding();
		this.maxLevel = entity.getMaxLevel();
		this.monopoly = entity.getMonopoly();
		this.monopoly.addProperty(this);
		this.payBackMoney = entity.getPayBackMoney();
		this.pledgePercent = entity.getPledgePercent();
		this.status = entity.getStatus();
		this.turnsToPayBack = entity.getTurnsToPayBack();
	}

	@Override
	public Building getBuilding() {
		return building;
	}

	@Override
	public boolean buildBuilding(Building building) {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		Player owner = propertyManager.getPropertyOwner(this);
		if (this.building == null && monopoly.hasSameOwner(owner) && owner.subtractMoney(building.getPrimaryCost())) {
			this.building = building;
			return true;
		}
		return false;
	}

	@Override
	public boolean upgradeBuilding() {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		if (building != null && building.getCurrentLevel() < building.getMaxLevel()
				&& propertyManager.getPropertyOwner(this).subtractMoney(building.getNextPrice())) {
			building.levelUp();
			return true;
		}
		return false;
	}

	@Override
	public boolean sellBuilding() {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		if (building != null) {
			int money = building.getCurrentPrice() / 2;
			Player owner = propertyManager.getPropertyOwner(this);
			owner.addMoney(money);
			IO io = ActionUtils.getPlayerIO(owner);
			if (building.getCurrentLevel() > 1) {
				building.levelDown();
				io.showMessage("Вы понизили Здание на " + getName() + " до " + building.getCurrentLevel() 
						+ " уровня за $" + money);
			} else {
				io.showMessage("Вы продали Здание на " + getName() + " за $" + money);
				building = null;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean destroyBuilding() {
		if (building != null) {
			building = null;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getPrice() {
		return basePrice;
	}

	@Override
	public int getRent() {
		int result = baseRent;
		if (hasBuilding()) {
			result = Math.round(baseRent * (1 + building.getCurrentLevel() * RENT_RATE));
		}
		return result;
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
	public boolean hasBuilding() {
		if (building != null) {
			return true;
		}
		return false;
	}

	@Override
	public int getMaxLevel() {
		return maxLevel;
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
		payBackMoney = (int) (payBackMoney * (1 + PAY_BACK_RATE));
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

	@Override
	public String getDescription() {		
		return "Стоимость: $" + getPrice() + " Аренда: $" + getRent() 
				+ ((hasBuilding()) ? ", Здание Ур. " + getBuilding().getCurrentLevel() : ", Без Здания");
	}

	@Override
	public String getColor() {		
		return monopoly.getMonopolyType().getColor();
	}

	@Override
	public String getOwnerColor() {		
		Player owner = GameSession.getInstance().getPropertyManager().getPropertyOwner(this);
		if (owner != null) {
			return owner.getPlayerColor();
		} else {
			return "#FFFAF0";
		}
	}
}
