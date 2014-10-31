package com.monopoly;

import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public interface Property {
    boolean setOwner(Player player);
    Player getOwner();
    List<BuildingYard> getBuildingYards();
}
