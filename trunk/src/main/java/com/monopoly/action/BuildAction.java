package com.monopoly.action;

import java.util.HashSet;
import java.util.Set;

import com.monopoly.board.building.Building;
import com.monopoly.board.building.BuildingType;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

public class BuildAction implements Action {
	public final static ActionType type = ActionType.BUILD;
	
	private int buildingPrice;
	private IO io;
	private Property property;
	private Player player;	
	
	private static Set<Player> players = new HashSet<Player>();
	
	@Override
	public void performAction(Player aPlayer) {
		player = aPlayer;
		
		synchronized (BuildAction.class) {
			if (players.contains(player)) {				
				return;
			}
			players.add(player);
		}
		
		io = ActionUtils.getPlayerIO(player);
		io.showWarning("Где построить здание?");
		property = io.selectProperty(player);
		if (property == null) {
			io.showWarning("Собственность не выбрана");
			return;
		}
		buildingPrice = property.getPrice() / 5;
		
		if (!player.isBuildPerformed() && io.yesNoDialog("Построить Здание за $" + buildingPrice + "?")				
				&& property.buildBuilding(new Building(BuildingType.CASTLE, buildingPrice))) {			
			player.setBuildPerformed(true);
			io.showMessage("Здание построено за $" + property.getBuilding().getPrimaryCost());			
			ActionUtils.sendMessageToAll(player.getName() + " построил Здание на " + property.getName());
		} else {
			showErrorMessage();
		}
		synchronized (BuildAction.class) {
			players.remove(player);
		}
	}

	private void showErrorMessage() {
		if (!property.getMonopoly().hasSameOwner(player)) {
			io.showWarning("Здание не построено! Вся монополия должна принадлежать Вам");
		} else if (player.isBuildPerformed()) {
			io.showWarning("Можно построить только один раз за ход");
		} else if (player.getMoney() < buildingPrice) {
			io.showWarning("Здание не построено! У Вас не достаточно средств");
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
