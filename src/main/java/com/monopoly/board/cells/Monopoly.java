package com.monopoly.board.cells;

/**
 * Created by Roma on 06.11.2014.
 */

import java.util.ArrayList;
import java.util.List;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.game.session.GameSession;
import static com.monopoly.board.cells.MonopolyType.*;

/**
 * Монополия
 */
public class Monopoly {
	private static List<Monopoly> monopolies = new ArrayList<Monopoly>(9) {
		private static final long serialVersionUID = 1L;

		{
			add(new Monopoly(MONOPOLY1));
			add(new Monopoly(MONOPOLY2));
			add(new Monopoly(MONOPOLY3));
			add(new Monopoly(MONOPOLY4));
			add(new Monopoly(MONOPOLY5));
			add(new Monopoly(MONOPOLY6));
			add(new Monopoly(MONOPOLY7));
			add(new Monopoly(MONOPOLY8));
			add(new Monopoly(MONOPOLY9));
		}
	};

	private MonopolyType monopolyType;
	private List<Property> monopolyProperty;

	public Monopoly(MonopolyType monopolyType) {
		this.monopolyType = monopolyType;
		monopolyProperty = new ArrayList<>();
	}

	public List<Property> getMonopolyProperty() {
		return monopolyProperty;
	}

	public boolean addProperty(Property property) {
		return monopolyProperty.add(property);
	}

	public MonopolyType getMonopolyType() {
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
	
	private void clear() {
		monopolyProperty.clear();
	}

	public static Monopoly getMonopoly(MonopolyType monopolyType) {
		for (Monopoly monopoly : monopolies) {
			if (monopoly.getMonopolyType() == monopolyType) {
				return monopoly;
			}
		}
		return null;
	}
	
	public static void resetAll() {
		for (Monopoly monopoly : monopolies) {
			monopoly.clear();
		}
	}
}
