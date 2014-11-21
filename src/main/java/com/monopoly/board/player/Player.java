package com.monopoly.board.player;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;

import java.util.List;

public class Player implements MoneyOperations, MoveOperations, TurnOperations, PropertyOperations {
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
        status = Status.START_TURN;



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
        this.position = this.position + position;
    }

    @Override
    public Wallet getWallet() {
        return money;
    }

    public List<Property> getProperty() {
        return property;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void buyProperty(PropertyCell propertyCell) {
        if(propertyCell.getPrice() <= money.getMoney()) {
            money.subtractMoney(propertyCell.getPrice());
            property.add(propertyCell);
//       что делать?     propertyCell.setOwner();
        }
    }

    @Override
    public void sellProperty(PropertyCell propertyCell) {
        if(!propertyCell.isPledged()) {

        }
    }

    @Override
    public void putUpProperty(PropertyCell propertyCell) {

    }

    @Override
    public void buyBackProperty(PropertyCell propertyCell) {

    }
}
