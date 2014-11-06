package com.monopoly;

import java.util.List;
import java.util.Random;

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
    Random random = new Random(cellCount);
    public EmergencyEvent(String name, String description, int cellCount) {
        this.name = name;
        this.description = description;
        this.cellCount = cellCount;
    }

    @Override
    public void performEvent(List<Player> players) {

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
