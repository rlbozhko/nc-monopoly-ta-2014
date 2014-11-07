package com.monopoly;

import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Player {
    private int position;
    private String name;
    private Status status;
    private Wallet money;
    private List<Property> property;

    public Player(String name, Wallet money) {
        position = 0;
        this.name = name;
        this.money = money;
        status = Status.WAIT;
    }

    public void surrender() {
        finishTurn();
        status = Status.FINISH;
    };

    public boolean startTurn() {
        return false;
    }

    public boolean finishTurn() {
        return false;
    }

    public Wallet getMoney() {
        return money;
    }

    public List<Property> getProperty() {
        return property;
    }

    public void move(int cellId) {
        position = cellId;
    }

    public int getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
