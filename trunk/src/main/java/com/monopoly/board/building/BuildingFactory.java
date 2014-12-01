package com.monopoly.board.building;

public class BuildingFactory {
	
	public static AvailableBuilding getCurrentBuilding (BuildingType type){
		switch (type) {
		
		case CLUB:
			return new Club();

		case PARK:
			return new Park();

		case MARKET:
			return new Market();

		case LAW_DEPARTMENT:
			return new LawDepartment();

		case CASTLE:
			return new Castle();
		
		default:
			return null;
		}
	}
	/*
	public BuildingFactory(BuildingType type) {
		switch (type) {
		
		case CLUB:
			building = new Club();
			break;

		case PARK:
			building = new Park();
			break;

		case MARKET:
			building = new Market();
			break;

		case LAW_DEPARTMENT:
			building = new LawDepartment();
			break;

		case CASTLE:
			building = new Castle();
			break;
		
		default:
			building = null;
			break;
		}
	}*/
	/*
	public String getName() {
		return building.getName();
	}
	public String getDescription() {
		return building.getDescription();
	}
	*/
}
