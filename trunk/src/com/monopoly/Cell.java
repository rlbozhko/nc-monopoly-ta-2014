package com.monopoly;

import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Cell implements Property, Event {
    public List<BuildingYard> yards;


    @Override
    public boolean performEvent() {
        return false;
    }

    @Override
    public boolean setOwner(Player player) {
        return false;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public List<BuildingYard> getBuildingYards() {
        return yards;
    }
}
