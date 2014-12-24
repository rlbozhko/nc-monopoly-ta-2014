package com.monopoly.board.player;

import com.monopoly.board.cells.Property;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by Roma on 19.12.2014.
 */
public class PropertyManager {
    private Map<Player, List<Property>> playersProperties = new HashMap<>();

    public PropertyManager(Collection<Player> players) {
        for (Player player : players) {
            playersProperties.put(player, new LinkedList<Property>());
        }
    }

    public Player getPropertyOwner(Property property) {
        if (property == null) {
            return null;
        }

        Set<Entry<Player, List<Property>>> entrySet = playersProperties.entrySet();
        for (Entry<Player, List<Property>> entry : entrySet) {
            for (Property currentProperty : entry.getValue()) {
                Player currentPlayer = entry.getKey();
                if (property.equals(currentProperty)) {
                    return currentPlayer;
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
        addPropertyToPlayer(newOwner, property);
    }

    private void addPropertyToPlayer(Player player, Property property) {
        playersProperties.get(player).add(property);
    }


}
