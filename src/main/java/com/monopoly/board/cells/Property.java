package com.monopoly.board.cells;

import com.monopoly.board.building.Building;
import com.monopoly.board.player.Player;

import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */

/**
 * Собственность
 */
public interface Property {
    void setOwner(Player player);

    Player getOwner();

    List<Building> getBuildings();

    boolean buildBuilding(Building building, int listIndex);

    boolean upgradeBuilding(Building building);

    boolean sellBuilding(Building building);

    int getPrice();

    int getRent();

    Monopoly getMonopoly();

    boolean isPledged();

    int getMaxLevel();

    int getMaxBuildings();
}