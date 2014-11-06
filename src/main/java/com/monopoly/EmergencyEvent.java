package com.monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Roma on 06.11.2014.
 */
/**
* ЧПСобытие
*/
public class EmergencyEvent implements Event {
    private final String name;
    private final String description;
    private final int cellCount;
    private final Board board;
    Random random;
    public EmergencyEvent(String name, String description, int cellCount, Board board) {
        this.name = name;
        this.description = description;
        this.cellCount = cellCount;
        this.board = board;
        random = new Random(cellCount);
    }

    @Override
    public void performEvent(List<Player> players) {
        List<Cell> cells = board.getCells();
        for (int i = 0; i < cellCount; i++) {
            int randomIndex = random.nextInt(cells.size());
            Cell cell = board.getCells().get(randomIndex);
            if (cell.getCellType() == CellType.PropertyCell) {
                Property property = (Property) cell;
                List<BuildingYard> buildingYards = property.getBuildingYards();
                List<Building> buildings = new ArrayList<>();
                for (BuildingYard by: buildingYards) {
                    Building building = by.getBuilding();
                    if (building != null) {
                        buildings.add(building);
                    }
                }
                if (!buildings.isEmpty()) {
                    randomIndex = random.nextInt(buildings.size());
                    BuildingOps b = (BuildingOps) buildingYards.get(randomIndex).getBuilding();
                    b.levelDown();
                }
            }
        }
    }

    @Override
    public String getEventDescription() {
        return description;
    }

    @Override
    public String getEventName() {
        return name;
    }
}
