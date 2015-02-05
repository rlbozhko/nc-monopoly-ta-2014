package com.monopoly.action;

import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class UpgradeBuildingAction implements Action {
	private IO io;
	private Property property;
	private Player player;
	public final static ActionType type = ActionType.UPGRADE_BUILDING;

	@Override
	public void performAction(Player aPlayer) {
		player = aPlayer;
		io = ActionUtils.getPlayerIO(player);
		io.showWarning("Где повысить уровень здания?");
		property = io.selectProperty(player);
		if (property == null) {
			io.showWarning("Собственность не выбрана");
			return;
		}		
		Building building = property.getBuilding();
		if (building == null) {
			io.showWarning("Нечего повышать");
			return;
		}
		if (buildingCheck(building)) {
			ActionUtils.sendMessageToAll(player.getName() + "повысил здание до " + building.currentLevel() + " уровня.");
		} else {
			showErrorMessage(building);
		}
	}

	private boolean buildingCheck(Building building) {
		return building.getMaxLevel() > building.currentLevel()
				&& io.yesNoDialog("Повысить уровень " + building.getBuildingName() + "за $" + building.currentPrice())
				&& property.upgradeBuilding();
	}

	private void showErrorMessage(Building building) {
		if (building.getMaxLevel() == building.currentLevel()) {
			io.showWarning("Нельзя повысить! Максимальный Уровень здания");
		} else if (player.getMoney() < building.currentPrice()) {
			io.showWarning("Не достаточно средств!");
		}
	}

	@Override
	public String getName() {
		return "Upgrade Building";
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
