package com.monopoly.board.building;

class Castle implements AvailableBuilding {
	
	private String name;
	private String description;
	private int maxLevel;
	
	public Castle (){
		this.name = "Castle";
		this.description = 
			"Добавляют к Стоимости Аренды больше, но Стоят тоже больше";
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
