package com.monopoly;

/**
 * Created by Roma on 31.10.2014.
 */
/**
 * Стройплощадка
 */
public class BuildingYard {
    private Building building;

    public void buildBuilding(Building building) {
        this.building = building;
    }

    public boolean sellBuilding() {
        if (building != null) {
            if (building.currentLevel() > 1) {
                building.levelDown();
            } else {
                building = null;
            }
            return true;
        }
        return false;
    }

    public Building getBuilding() {
        return building;
    }
}
