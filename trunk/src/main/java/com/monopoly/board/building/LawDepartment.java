package com.monopoly.board.building;

class LawDepartment implements AvailableBuilding {
	
	private String name;
	private String description;
	private int maxLevel;
	
	public LawDepartment (){
		this.name = "LawDepartment";
		this.description = 
			"Уменьшается количество ходов при отсидке в тюрьме."
			+ "Игрок может владеть только Одним Юр.отделом";
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
