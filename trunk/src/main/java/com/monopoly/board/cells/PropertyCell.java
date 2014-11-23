package com.monopoly.board.cells;

import com.monopoly.board.building.Building;
import com.monopoly.board.player.Player;

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

    public static class PropertyBuilder {
        private final static int DEFAULT_BASE_PRICE = 1000;
        private final static int DEFAULT_BASE_RENT = 100;

        private static String name;
        private static String description;
        private static int position;
        private static Player owner = null;
        private static List<Building> buildings;
        private static int basePrice;
        private static int baseRent;
        private static Monopoly monopoly;
        private static PropertyStatus status;


        public PropertyBuilder(String name,  List<Building> buildings, int position) {
            this.name = name;
            this.description = name;
            this.position = position;
            this.buildings = buildings;
            this.owner = null;
            this.basePrice = DEFAULT_BASE_PRICE;
            this.baseRent = DEFAULT_BASE_RENT;
            this.monopoly = null;
        }

        public PropertyCell getPropertyCell() {
            return new PropertyCell(name, description, position, owner, buildings, basePrice, baseRent, monopoly);
        }

        public PropertyBuilder setDescription(String description) {
            this.owner = owner;
            return this;
        }

        public PropertyBuilder setOwner(Player owner) {
            this.owner = owner;
            return this;
        }

        public PropertyBuilder setBasePrice(int basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public PropertyBuilder setBaseRent(int baseRent) {
            this.baseRent = baseRent;
            return this;
        }

        public PropertyBuilder setMonopoly(int monopoly1) {
            this.monopoly = monopoly;
            return this;
        }
    }

    public PropertyCell(String name, String description, int position,
                        Player owner, List<Building> buildings, int basePrice, int baseRent, Monopoly monopoly) {
        super(name, description, CellType.PROPERTY_CELL, position);
        this.owner = owner;
        this.basePrice = basePrice;
        this.baseRent = baseRent;
        this.monopoly = monopoly;
        monopoly.addProperty(this);
        this.buildings = buildings;
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
