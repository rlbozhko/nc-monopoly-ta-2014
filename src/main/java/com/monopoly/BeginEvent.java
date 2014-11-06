package com.monopoly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misha on 06-Nov-14.
 */
public class BeginEvent implements Event {

    private static final int STARTCASH = 1500;
    private String eventName;
    String description;
    private List<Wallet> money;

    public BeginEvent(String eventName, String description){
        this.eventName = eventName;
        this.description = description;
    }

    @Override
    public void performEvent(List<Player> players) {
        money = new ArrayList<Wallet>();
        for (int i = 0; i<players.size(); i++){
            Wallet playerMoney = players.get(i).getMoney();
            playerMoney.addMoney(STARTCASH);
            money.set(i, playerMoney);
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
