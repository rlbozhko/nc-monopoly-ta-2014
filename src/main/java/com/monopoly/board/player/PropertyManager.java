package com.monopoly.board.player;

import com.monopoly.board.cells.Property;

import java.util.*;

/**
 * Created by Roma on 19.12.2014.
 */
public class PropertyManager {
	private Map<Player, List<Property>> playersProperties = new HashMap<>();

	public PropertyManager(Collection<Player> players) {
		for (Player player : players) {
			playersProperties.put(player, new ArrayList<Property>());
		}
	}

	public Player getPropertyOwner(Property property) {
		if (property == null) {
			return null;
		}

		for (Player player : playersProperties.keySet()) {
			for (Property currentProperty : playersProperties.get(player)) {
				if (property.equals(currentProperty)) {
					return player;
				}
			}
		}

		return null;
	}

	public List<Property> getPlayerProperties(Player player) {
		return playersProperties.get(player);
	}

	public void resetPropertyOwner(Property property) {
		Player previousOwner = getPropertyOwner(property);
		if (previousOwner != null) {
			getPlayerProperties(previousOwner).remove(property);
		}
	}

	public void setPropertyOwner(Player newOwner, Property property) {
		if (newOwner == null || property == null) {
			return;
		}
		resetPropertyOwner(property);		
		playersProperties.get(newOwner).add(property);
	}
}
