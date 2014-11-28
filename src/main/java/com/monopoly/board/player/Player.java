package com.monopoly.board.player;

import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.game.session.TestSession;

import java.util.ArrayList;
import java.util.List;

public class Player implements MoneyOperations, MoveOperations, PropertyOperations {
    private int position;
    private int lastPosition;
    private String name;
    private Status status;
    private Wallet wallet;
    private List<Property> property;
    private boolean mustPayRent;
    private boolean nextCircle;
    private final int MAX_MOVE = 12;

    public Player(String name, Wallet money) {
        position = 0;
        lastPosition = 0;
        this.name = name;
        this.wallet = money;
        this.property = new ArrayList<>();
        status = Status.WAIT;
        mustPayRent = false;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int getLastPosition() {
        return lastPosition;
    }

    @Override
    public void goToPosition(int position) {
        int boardSize = TestSession.getInstance().getBoard().getCells().size();
        this.lastPosition = position;
        this.position = position % boardSize;
    }

    @Override
    public boolean isNextCircle() {
        return (this.getLastPosition() > (TestSession.getInstance().getBoard().getCells().size() - MAX_MOVE))
                && (this.getPosition() < MAX_MOVE)
                && (this.getPosition() != 0);
    }

    @Override
    public Wallet getWallet() {
        return wallet;
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
    public boolean buyProperty(PropertyCell propertyCell) {
        if (propertyCell.getPrice() <= wallet.getMoney()) {
            wallet.subtractMoney(propertyCell.getPrice());
            //property.add(propertyCell);
            propertyCell.setOwner(this);
            return true;
        }
        return false;
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

    @Override
    public boolean mustPayRent() {
        return mustPayRent;
    }

    @Override
    public void setMustPayRent(boolean mustPayRent) {
        this.mustPayRent = mustPayRent;
    }
}
