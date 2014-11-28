package com.monopoly.board.building;

class Club implements AvailableBuilding {

	private String name;
	private String description;
	
	public Club (){
		this.name = "Club";
		this.description = "Увеличивают шансы Диверсии заказанной Владельцем";
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
}
