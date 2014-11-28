package com.monopoly.board.building;

class Market implements AvailableBuilding {
	
	private String name;
	private String description;
	
	public Market (){
		this.name = "Market";
		this.description = 
			"При попадании на Данную "
			+ "Собственность/Монополию Владельцу зачисляются доп средства";
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
