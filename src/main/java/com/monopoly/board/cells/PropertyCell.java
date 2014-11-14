package com.monopoly.board.cells;

import com.monopoly.board.bildings.Building;
import com.monopoly.board.players.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 06.11.2014.
 */
/**
 * ЯчейкаСобственности
 */
public class PropertyCell extends Cell implements Property {
    private Player owner = null;
    private List<Building> buildings;
    private int basePrice;
    private int baseRent;
    private Monopoly monopoly;
    private PropertyStatus status;

    public PropertyCell(String name, String description,
                        Player owner, int buildingsCount, int basePrice, int baseRent, Monopoly monopoly) {
        super(name, description, CellType.PROPERTY_CELL);
        this.owner = owner;
        this.basePrice = basePrice;
        this.baseRent = baseRent;
        this.monopoly = monopoly;
        monopoly.addProperty(this);
        buildings = new ArrayList<>(buildingsCount);
        for (int i = 0; i < buildingsCount; i++) {
            buildings.add(null);
        }
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public List<Building> getBuildings() {
        return buildings;
    }

    @Override
    public boolean buildBuilding(Building building, int listIndex) {
        if (buildings.get(listIndex) == null) {
            buildings.set(listIndex, building);
        }
        return false;
    }

    @Override
    public boolean upgradeBuilding(Building building) {
        if (building != null && building.currentLevel() < building.getMaxLevel()) {
            building.levelUp();
            owner.getWallet().subtractMoney(building.currentPrice());
            return true;
        }
        return false;
    }

    @Override
    public boolean sellBuilding(Building building) {
        if (building != null) {
            if (building.currentLevel() > 1) {
                building.levelDown();
            } else {
                building = null;
            }
            owner.getWallet().addMoney(building.currentPrice()/2);
            return true;
        }
        return false;
    }

    @Override
    public int getPrice() {
        return basePrice;
    }

    @Override
    public int getRent() {
        return baseRent;
    }

    @Override
    public Monopoly getMonopoly() {
        return monopoly;
    }

    @Override
    public boolean isPledged() {
        return false;
    }
}
