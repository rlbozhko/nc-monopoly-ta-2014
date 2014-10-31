package com.monopoly;

import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Player {
    private int position;
    private String name;
    private Status status;
    private Money money;
    private List<Property> property;

    public Player(String name, Money money) {
        position = 0;
        this.name = name;
        this.money = money;
        status = Status.Wait;
    }

    public void surrender() {
        finishTurn();
        status = Status.Finish;
    };

    public boolean startTurn() {
        return false;
    }

    public boolean finishTurn() {
        return false;
    }

    public Money getMoney() {
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
