package com.monopoly.action;

import java.util.HashSet;
import java.util.Set;

import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class UpgradeBuildingAction implements Action {
	private IO io;
	private Property property;
	private Player player;
	private int currentPrice;

	public final static ActionType type = ActionType.UPGRADE_BUILDING;

	private static Set<Player> players = new HashSet<Player>();

	@Override
	public void performAction(Player aPlayer) {
		player = aPlayer;
		io = ActionUtils.getPlayerIO(player);
		
		synchronized (UpgradeBuildingAction.class) {
			if (players.contains(player)) {
				return;
			} else if (player.isBuildPerformed()) {
				io.showWarning("Построить здание или Повысить уровень задния можно только один раз за ход!");
				return;
			} 
			players.add(player);
		}
		
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
		currentPrice = building.getNextPrice();
		if (buildingCheck(building)) {
			io.showMessage("Вы повысили здание до " + building.getCurrentLevel() + " уровня за $" + currentPrice);
			ActionUtils.sendMessageToAll(player.getName() + " повысил здание до " + building.getCurrentLevel()
					+ " уровня на " + property.getName());
		} else {
			showErrorMessage(building);
		}

		synchronized (UpgradeBuildingAction.class) {
			players.remove(player);
		}

	}

	private boolean buildingCheck(Building building) {
		return !player.isBuildPerformed() && building.getMaxLevel() > building.getCurrentLevel()
				&& io.yesNoDialog("Повысить уровень Здания за $" + currentPrice) && property.upgradeBuilding();
	}

	private void showErrorMessage(Building building) {
		if (building.getMaxLevel() == building.getCurrentLevel()) {
			io.showWarning("Нельзя повысить! Максимальный Уровень здания");
		} else if (player.getMoney() < building.getCurrentPrice()) {
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
