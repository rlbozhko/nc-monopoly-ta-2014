package com.monopoly.board.events;

import com.monopoly.board.*;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;

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
            randomBildingLevelDown(getRandomCellBildings(cells));
        }
    }

    private List<Building> getRandomCellBildings(List<Cell> cells) {
        int randomIndex = random.nextInt(cells.size());
        List<Building> buildings = ((Property) cells.get(randomIndex)).getBuildings();
        buildings.removeAll(Collections.singleton(null));
        return buildings;
    }

    private void randomBildingLevelDown(List<Building> buildings) {
        int randomIndex;
        if (!buildings.isEmpty()) {
            randomIndex = random.nextInt(buildings.size());
            buildings.get(randomIndex).levelDown();
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
