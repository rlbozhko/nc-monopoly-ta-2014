package com.monopoly.board.building;

public enum BuildingType {

	CLUB,
	PARK,
	LAW_DEPARTMENT,
	CASTLE,
	MARKET;
	
	public static BuildingType getTypeByText(String text) {
		for (BuildingType type : BuildingType.values()) {
			if (type.name().toLowerCase().equals(text.toLowerCase())) {
				return type;
			}
		}
		throw new RuntimeException("Not supported building type " + text);
	}
}
