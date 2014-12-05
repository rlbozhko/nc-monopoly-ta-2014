package com.monopoly.board.events;

import com.monopoly.board.Board;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;
import com.monopoly.game.session.TestSession;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Roma on 06.11.2014.
 */

/**
 * ЧПСобытие
 */
public class EmergencyEvent extends BaseEvent {
    private int cellCount;
    private Random random;

    public EmergencyEvent(String name, String description, int cellCount) {
        this.name = name;
        this.description = description;
        this.cellCount = cellCount;
        random = new Random(cellCount);
    }

    @Override
    public void performEvent() {
        Board board = TestSession.getInstance().getBoard();
        List<Cell> cells = board.getPropertyCell();
        for (int i = 0; i < cellCount; i++) {
            randomBuildingLevelDown(getRandomCellBuildings(cells));
        }
    }

    private List<Building> getRandomCellBuildings(List<Cell> cells) {
        int randomIndex = random.nextInt(cells.size());
        List<Building> buildings = ((Property) cells.get(randomIndex)).getBuildings();
        buildings.removeAll(Collections.singleton(null));
        return buildings;
    }

    private void randomBuildingLevelDown(List<Building> buildings) {
        int randomIndex;
        if (!isEmpty(buildings)) {
            randomIndex = random.nextInt(buildings.size());
            buildings.get(randomIndex).levelDown();
        }
    }
}
