package com.monopoly.board.cells;

/**
 * Created by Roma on 06.11.2014.
 */

import java.util.ArrayList;
import java.util.List;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.game.session.GameSession;

/**
 * Монополия
 */
public class Monopoly {
	private String monopolyType;
	private List<Property> monopolyProperty;

	public Monopoly(String monopolyType) {
		this.monopolyType = monopolyType;
		monopolyProperty = new ArrayList<>();
	}

	public List<Property> getMonopolyProperty() {
		return monopolyProperty;
	}

	public boolean addProperty(Property property) {
		return monopolyProperty.add(property);
	}

	public String getMonopolyType() {
		return monopolyType;
	}

	public boolean hasSameOwner(Player owner) {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		for (Property property : monopolyProperty) {
			if (!owner.equals(propertyManager.getPropertyOwner(property))) {
				return false;
			}
		}
		return true;
	}
}
