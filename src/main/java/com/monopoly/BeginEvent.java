package com.monopoly;

public class BeginEvent implements Event {

    private static final int START_CASH = 1500;
    private String eventName;
    private String description;

    public BeginEvent(String eventName, String description) {
        this.eventName = eventName;
        this.description = description;
    }

    @Override
    public void performEvent(Board board) {
        for (Player player : board.getPlayers())
            player.getWallet().addMoney(START_CASH);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return eventName;
    }
}
