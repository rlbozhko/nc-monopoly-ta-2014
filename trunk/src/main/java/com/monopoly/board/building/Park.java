package com.monopoly.board.building;

class Park implements AvailableBuilding {
	
	private String name;
	private String description;
	private int maxLevel;
	
	public Park (){
		this.name = "Park";
		this.description = 
			"Добавляет к данной ячейке  случайное Событие из списка Шанс";
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
