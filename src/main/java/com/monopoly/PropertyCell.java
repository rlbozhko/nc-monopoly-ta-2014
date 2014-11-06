package com.monopoly;

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
    private List<BuildingYard> buildingYards;
    private int basePrice;
    private int baseRent;
    private Monopoly monopoly;
    private PropertyStatus status;

    public PropertyCell(String name, String description, CellType cellType,
                        Player owner, int buildingYardsCount, int basePrice, int baseRent, Monopoly monopoly) {
        super(name, description, cellType);
        this.owner = owner;
        this.basePrice = basePrice;
        this.baseRent = baseRent;
        this.monopoly = monopoly;
        buildingYards = new ArrayList<>(buildingYardsCount);
        for (int i = 0; i < buildingYardsCount; i++) {
            buildingYards.add(new BuildingYard());
        }

    }

    @Override
    public void setOwner(Player player) {

    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public List<BuildingYard> getBuildingYards() {
        return null;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getRent() {
        return 0;
    }

    @Override
    public PropertyStatus getPropertyStatus() {
        return null;
    }

    @Override
    public Monopoly getMonopoly() {
        return null;
    }
}
