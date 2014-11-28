package com.monopoly.board.building;

class Park implements AvailableBuilding {
	
	private String name;
	private String description;
	
	public Park (){
		this.name = "Park";
		this.description = 
			"Добавляет к данной ячейке  случайное Событие из списка Шанс";
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	
	
}
