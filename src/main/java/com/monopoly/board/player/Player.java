package com.monopoly.board.player;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.cells.*;
import com.monopoly.game.session.TestSession;

import java.util.ArrayList;
import java.util.List;

public class Player implements MoneyOperations, MoveOperations, PropertyOperations {
    private int position;
    private int lastPosition;
    private String name;
    private Status status;
    private Wallet wallet;
    private List<Property> propertyList;
    private boolean payRent;

    public Player(String name) {
        position = 0;
        lastPosition = 0;
        this.name = name;
        this.wallet = new Wallet();
        this.propertyList = new ArrayList<>();
        status = Status.WAIT;
        payRent = false;
    }

    private Player(String name, int position, int lastPosition, Status status, Wallet wallet, List<Property> property,
                   boolean payRent) {
        this.name = name;
        this.position = position;
        this.lastPosition = lastPosition;
        this.status = status;
        this.wallet = wallet;
        this.propertyList = property;
        this.payRent = payRent;
    }

    @Override
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getLastPosition() {
        return lastPosition;
    }

    @Override
    public void goToPosition(int position) {
        int boardSize = TestSession.getInstance().getBoard().getCells().size();
        this.lastPosition = this.position;
        boolean nextCircle = isNextCircle(position);
        this.position = position % boardSize;
        if (nextCircle) {
            ((EventCell) TestSession.getInstance().getBoard().getCells().get(0)).getEvent().performEvent();
        }

        if (hasPledgedProperty()) {
            for (Property property : propertyList) {
                if (property.isPledged()) {
                    property.setTurnsToPayBack(property.getTurnsToPayBack() - 1);
                    if (property.getTurnsToPayBack() == 0) {
                        ActionUtils.getPlayerIO(this).showMessage("ВНИМАНИЕ!!!\n" +
                                "Срок погашения заема истек для " + ((Cell)property).getName() + ".\n" +
                                "Если вы не погасите задолженность, то по окончанию хода начнется аукцион");
                    }
                }
            }
        }

        Cell currentCell = TestSession.getInstance().getBoard().getCells().get(this.getPosition());
        if (CellType.EVENT_CELL.equals(currentCell.getCellType())) {
            ((EventCell) currentCell).getEvent().performEvent();
        } else if (CellType.PROPERTY_CELL.equals(currentCell.getCellType())) {
            Property property = (Property) currentCell;
            if (null != property.getOwner() && !this.equals(property.getOwner())) {
                this.setPayRent(true);
            }
        }
    }

    private boolean isNextCircle(int position) {
        return (position > TestSession.getInstance().getBoard().getCells().size());
    }

    @Override
    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public void addMoney(int money) {
        wallet.addMoney(money);
    }

    @Override
    public void subtractMoney(int money) {
        wallet.subtractMoney(money);
    }

    @Override
    public int getMoney() {
        return wallet.getMoney();
    }

    public List<Property> getPropertyList() {
        return propertyList;
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
            propertyCell.setAndAddToOwner(this);
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
    public boolean isPayRent() {
        return payRent;
    }

    @Override
    public void setPayRent(boolean payRent) {
        this.payRent = payRent;
    }

    @Override
    public boolean hasPledgedProperty() {
        for (Property property : propertyList) {
            if (property.isPledged()) {
                return true;
            }
        }
        return false;
    }

    public static class PlayerBuilder {
        private int position;
        private int lastPosition;
        private String name;
        private Status status;
        private Wallet wallet;
        private List<Property> propertyList;
        private boolean payRent;

        public PlayerBuilder(String name) {
            this.name = name;
            status = Status.WAIT;
            wallet = new Wallet();
            propertyList = new ArrayList<>();
        }

        public Player getPlayer() {
            return new Player(name, position, lastPosition, status, wallet, propertyList, payRent);
        }

        public PlayerBuilder lastPosition(int lastPosition) {
            this.lastPosition = lastPosition;
            return this;
        }

        public PlayerBuilder position(int position) {
            this.position = position;
            return this;
        }

        public PlayerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PlayerBuilder payRent(boolean payRent) {
            this.payRent = payRent;
            return this;
        }

        public PlayerBuilder propertyList(List<Property> property) {
            this.propertyList = property;
            return this;
        }

        public PlayerBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public PlayerBuilder wallet(Wallet wallet) {
            this.wallet = wallet;
            return this;
        }
    }
}
