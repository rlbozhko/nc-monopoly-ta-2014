package com.monopoly.board.building;

/**
 * Created by Roma on 31.10.2014.
 */
public class Building implements BuildingOperations {
	
    public static final float PERCENTAGE_FOR_NEW_BUILD = 0.1F;

    //TODO еобходимо еще реализовать поле по которому возможно узнавать и
    //использовать бонусы от постройки
    
    private int currentPrice;
    private int primaryCost;
    private String name;
    private String description;
    private int currentLevel;
    private int maxLevel;
    
    public Building(BuildingType type, int cost) {
    	if (cost < 0){
    		throw new RuntimeException("You should pay money!");
    	}
    	
    	Settings settings = BuildingSettings.getInstance()
    										.getSettingsByType(type);
    	
    	this.name = settings.getName();
    	this.description = settings.getDescription();
    	this.maxLevel = settings.getMaxLevel();
    	this.primaryCost = cost;
        this.currentPrice = cost;
        this.currentLevel = 1;
    }
    
	public boolean levelUp() {
		if (this.currentLevel <= this.maxLevel && this.currentLevel > 0) {
			this.currentLevel++;
			this.currentPrice = Math.round(this.currentPrice + this.currentPrice
					* PERCENTAGE_FOR_NEW_BUILD);
			return true;
		}
		return false;
	}

	public boolean levelDown() {
		if (this.currentLevel > 0) {
			//TODO подумать над тем когда будет продаваться последнее здание
			//и ячейка должна будет быть пустой значит ее надо делать null
			//и удалять из коллекции
			this.currentLevel--;
			this.currentPrice = Math.round(this.currentPrice - PERCENTAGE_FOR_NEW_BUILD * this.currentPrice);
			return true;
		}
		return false;
	}

    public int currentPrice() {
        return currentPrice;
    }

    public String getBuildingName() {
        return name;
    }

    public String getBuildingDescription() {
        return description;
    }

    public int currentLevel() {
        return currentLevel;
    }
    
    public int getPrimaryCost() {
		return primaryCost;
	}

	public int getMaxLevel() {
        return maxLevel;
    }

}
