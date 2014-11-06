package com.monopoly;

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
    List<BuildingYard> getBuildingYards();
    int getPrice();
    int getRent();
    PropertyStatus getPropertyStatus();
    Monopoly getMonopoly();
}
