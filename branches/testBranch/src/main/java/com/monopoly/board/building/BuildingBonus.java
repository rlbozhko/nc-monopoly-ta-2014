package com.monopoly.board.building;

public class BuildingBonus {

	private static BuildingBonus instance = new BuildingBonus();
		
	private BuildingBonus (){
		
	}
	
	public static BuildingBonus getInstance (){
		return instance;
	}
	
	private BonusLevel LevelBonus (int buildingLevel) {
		if (buildingLevel < 3){
			return BonusLevel.LOW;
		} else if (buildingLevel < 5){
			return BonusLevel.MEDIUM;
		} 
		return BonusLevel.HIGH;
	}
		
	public void getBuildingBonus(BonusType bonusType, int buildingLevel) {
						
		switch (bonusType) {
		
		case DECREASE_JAIL_TERM:
			 	
			break;

		case ADD_EVENT:
			//Cell bbb = new Cell (this.LevelBonus(buildingLevel);)
			break;

		case INCREASE_PAY_RENT:

			break;

		case INCREASE_SABOTAGE:

			break;

		case ADD_MONEY_TO_OWNER:

			break;

		default:
			throw new RuntimeException("Not supported building bonus "
					+ bonusType.toString());
		}
	}
	
}
