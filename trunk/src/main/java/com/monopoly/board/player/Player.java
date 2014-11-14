package com.monopoly.board.player;

import com.monopoly.board.cells.Property;

import java.util.List;

public class Player implements MoneyOperations, MoveOperations, TurnOperations {
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

    @Override
    public void surrender() {
        finishTurn();
        status = Status.FINISH;
    }

    @Override
    public void startTurn() {
        //TODO
    }

    @Override
    public void finishTurn() {
        //TODO
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void goToPosition(int position) {
// ?
    }

    @Override
    public Wallet getWallet() {
        return money;
    }



    public List<Property> getProperty() {
        return property;
    }


    public void move(int cellId) {
        position = cellId;
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }
}
