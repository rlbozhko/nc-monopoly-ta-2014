package com.monopoly;

import java.util.*;

/**
 * Created by Roma on 06.11.2014.
 */

/**
 * ЧПСобытие
 */
public class EmergencyEvent implements Event {
    private String name;
    private String description;
    private int cellCount;
    private Random random;

    public EmergencyEvent(String name, String description, int cellCount) {
        this.name = name;
        this.description = description;
        this.cellCount = cellCount;
        random = new Random(cellCount);
    }

    @Override
    public void performEvent(Board board) {
        List<Cell> cells = ((BoardCellOperations) board).getPropertyCell();
        for (int i = 0; i < cellCount; i++) {
            int randomIndex = random.nextInt(cells.size());
            Property property = (Property) cells.get(randomIndex);
            List<Building> buildings = property.getBuildings();

            List<Building> nullList = new ArrayList<Building>();
            nullList.add(null);
            buildings.removeAll(nullList);

            if (!buildings.isEmpty()) {
                randomIndex = random.nextInt(buildings.size());
                BuildingOperations building = buildings.get(randomIndex);
                building.levelDown();
            }

        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }
}
