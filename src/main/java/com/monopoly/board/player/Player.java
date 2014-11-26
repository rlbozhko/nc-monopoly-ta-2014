package com.monopoly.board.player;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.game.session.TestSession;

import java.util.List;

public class Player implements MoneyOperations, MoveOperations, PropertyOperations {
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

    /*
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
        }
    */
    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void goToPosition(int position) {
        int boardSize = TestSession.getInstance().getBoard().getCells().size();
        this.position = position % boardSize;
    }

    @Override
    public Wallet getWallet() {
        return money;
    }

    public List<Property> getProperty() {
        return property;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void buyProperty(PropertyCell propertyCell) {
        if (propertyCell.getPrice() <= money.getMoney()) {
            money.subtractMoney(propertyCell.getPrice());
            property.add(propertyCell);
            propertyCell.setOwner(this);
        }
    }

    @Override
    public void sellProperty(PropertyCell propertyCell) {
        if (!propertyCell.isPledged()) {

        }
    }

    @Override
    public void putUpProperty(PropertyCell propertyCell) {

    }

    @Override
    public void buyBackProperty(PropertyCell propertyCell) {

    }
}
