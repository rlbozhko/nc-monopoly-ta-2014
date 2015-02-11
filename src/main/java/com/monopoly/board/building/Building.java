package com.monopoly.board.building;

/**
 * Created by Roma on 31.10.2014.
 */
public class Building implements BuildingOperations {
	
    public static final float PERCENTAGE_FOR_NEW_LEVEL = 0.5F;

    //TODO еобходимо еще реализовать поле по которому возможно узнавать и
    //использовать бонусы от постройки
        
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
        this.currentLevel = 1;        
    }
    
	public boolean levelUp() {
		if (this.currentLevel < this.maxLevel) {
			this.currentLevel++;
			return true;
		}
		return false;
	}

	public boolean levelDown() {
		if (this.currentLevel > 0) {			
			this.currentLevel--;			
			return true;
		}
		return false;
	}
    
    public String getBuildingName() {
        return name;
    }

    public String getBuildingDescription() {
        return description;
    }
        
    public int getPrimaryCost() {
		return primaryCost;
	}

	public int getMaxLevel() {
        return maxLevel;
    }

	public int getCurrentPrice() {
		return getPriceForLevel(currentLevel);
	}
	
	public int getNextPrice() {
		return getPriceForLevel(currentLevel + 1);
	}
	
	private int getPriceForLevel(int level) {
		return Math.round(this.primaryCost  + this.primaryCost * (level - 1) 
				* PERCENTAGE_FOR_NEW_LEVEL);
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void setPrimaryCost(int primaryCost) {
		this.primaryCost = primaryCost;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
}
