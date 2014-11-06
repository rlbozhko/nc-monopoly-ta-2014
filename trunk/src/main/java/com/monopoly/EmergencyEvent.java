package com.monopoly;

import java.util.List;

/**
 * Created by Roma on 06.11.2014.
 */

/**
* ЧПСобытие
*/
public class EmergencyEvent implements Event {
    private String name;
    private String description;

    public EmergencyEvent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void performEvent(List<Player> players) {

    }

    @Override
    public String getEventDescription() {
        return null;
    }

    @Override
    public String getEventName() {
        return null;
    }
}
