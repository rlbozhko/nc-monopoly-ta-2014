package com.monopoly.action;

import com.monopoly.board.building.Building;
import com.monopoly.board.building.BuildingType;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class BuildAction implements Action {
	public final static ActionType type = ActionType.BUILD;
	
	private final int BUILDING_PRICE = 1000;
	private IO io;
	private Property property;
	private Player player;	
	
	@Override
	public void performAction(Player aPlayer) {
		player = aPlayer;
		io = ActionUtils.getPlayerIO(player);
		io.showWarning("Где построить здание?");
		property = io.selectProperty(player);
		if (property == null) {
			io.showWarning("Собственность не выбрана");
			return;
		}
		
		if (io.yesNoDialog("Построить здание за " + BUILDING_PRICE + "?")
				&& property.buildBuilding(new Building(BuildingType.CASTLE, BUILDING_PRICE))) {
			io.showMessage("Здание построено");
		} else {
			showErrorMessage();
		}		
	}

	private void showErrorMessage() {
		if (!property.getMonopoly().hasSameOwner(player)) {
			io.showWarning("Здание не построено! Вся монополия должна принадлежать Вам");
		} else if (player.getMoney() < BUILDING_PRICE) {
			io.showWarning("Здание не построено! У Вас не достатточно средств");
		} else if (property.hasBuilding()) {
			io.showWarning("Тут уже есть здание!");
		}		
	}

	@Override
	public String getName() {		
		return "Build";
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
