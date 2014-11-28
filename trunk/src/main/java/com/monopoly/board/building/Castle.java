package com.monopoly.board.building;

class Castle implements AvailableBuilding {
	
	private String name;
	private String description;
	
	public Castle (){
		this.name = "Castle";
		this.description = 
			"Добавляют к Стоимости Аренды больше, но Стоят тоже больше";
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
}
