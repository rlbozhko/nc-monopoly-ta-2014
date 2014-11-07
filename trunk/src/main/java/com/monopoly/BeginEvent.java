package com.monopoly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 06-Nov-14.
 */
public class BeginEvent implements Event {

        private static final int START_CASH = 1500;
        private String eventName;
        String description;

        public BeginEvent(String eventName, String description){
            this.eventName = eventName;
            this.description = description;
        }

        @Override
        public void performEvent(List<Player> players) {
            for (Player player: players){
                player.getMoney().addMoney(START_CASH);
            }
        }

    @Override
    public String getEventDescription() {
        return description;
    }

    @Override
    public String getEventName() {
        return eventName;
    }
}