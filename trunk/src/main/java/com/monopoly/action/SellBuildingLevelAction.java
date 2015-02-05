package com.monopoly.action;

import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class SellBuildingLevelAction implements Action {
	private IO io;
	private Property property;
	private Player player;
	public final static ActionType type = ActionType.SELL_BUILDING;

	@Override
	public void performAction(Player aPlayer) {
		player = aPlayer;
		io = ActionUtils.getPlayerIO(player);
		io.showWarning("Где понизить уровень здания?");
		property = io.selectProperty(player);
		if (property == null) {
			io.showWarning("Собственность не выбрана");
			return;
		}
		Building building = property.getBuilding();
		if (building == null) {
			io.showWarning("Нечего понижать");
			return;
		}
		if (buildingCheck(building)) {
			ActionUtils.sendMessageToAll(player.getName() + " понизил уровень Здания на ячейке " + property.getName());
		}
	}

	private boolean buildingCheck(Building building) {
		return io.yesNoDialog("Понизить уровень " + building.getBuildingName() + "?") && property.sellBuilding();
	}

	@Override
	public String getName() {
		return "Sell one Building level";
	}
	
	@Override
	public int hashCode() {		
		return type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {		
		return type.equals(obj);
	}
	
	@Override
	public ActionType getType() {		
		return type;
	}
}
