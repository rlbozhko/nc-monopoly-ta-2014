package com.monopoly.board.building;

class Market implements AvailableBuilding {
	
	private String name;
	private String description;
	private int maxLevel;
	
	public Market (){
		this.name = "Market";
		this.description = 
			"При попадании на Данную "
			+ "Собственность/Монополию Владельцу зачисляются доп средства";
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
