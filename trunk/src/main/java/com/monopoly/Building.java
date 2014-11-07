package com.monopoly;

/**
 * Created by Roma on 31.10.2014.
 */
public class Building implements BuildingOps {
	
	public static final double PERCENTAGE_FOR_NEW_BUILD = 0.1;
	
	private Event event;
	private int currentPrice;
	private String name;
	private String description;
	private int currentLevel;
	
	public Building (Event event, int cost){
		this.event = event;
		this.name = event.getName();
		this.description = event.getDescription();
		this.currentPrice = cost;
		this.currentLevel = 1;
	}

	public boolean levelUp (){
		this.currentLevel ++;
		this.currentPrice = (int) (this.currentPrice + 
				this.currentPrice * PERCENTAGE_FOR_NEW_BUILD);
		return true;
	}
	
	public boolean levelDown (){
		this.currentLevel --;
		this.currentPrice = (int) (this.currentPrice - 
				this.currentPrice * PERCENTAGE_FOR_NEW_BUILD);
		return true;
	}
	
	public int currentPrice() {
		return currentPrice;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Event getEvent() {
		return event;
	}

	public int currentLevel() {
		return currentLevel;
	}
	    
}
