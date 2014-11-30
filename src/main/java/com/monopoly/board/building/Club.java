package com.monopoly.board.building;

class Club implements AvailableBuilding {

	private String name;
	private String description;
	private int maxLevel;
	
	public Club (){
		this.name = "Club";
		this.description = "Увеличивают шансы Диверсии заказанной Владельцем";
		this.maxLevel = 5;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getMaxLevel(){
		return maxLevel;
	}
	
}
