package com.monopoly.board.building;

public class BuildingStore {

	private AvailableBuilding building;
	
	public BuildingStore(BuildingType type) {
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
	}
	
	public String getName() {
		return building.getName();
	}
	public String getDescription() {
		return building.getDescription();
	}
	
}
