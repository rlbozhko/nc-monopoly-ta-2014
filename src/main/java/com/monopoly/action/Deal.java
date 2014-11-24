package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;

/**
 * Created by Roma on 24.11.2014.
 */
public class Deal {
    private int askMoney;
    private int giveMoney;
    private Property askProperty;
    private Property giveProperty;
    private Player source;

    public Deal(Player source) {
        this.source = source;
    }

    public Player getSource() {
        return source;
    }

    public int getAskMoney() {
        return askMoney;
    }

    public void setAskMoney(int askMoney) {
        this.askMoney = askMoney;
    }

    public int getGiveMoney() {
        return giveMoney;
    }

    public void setGiveMoney(int giveMoney) {
        this.giveMoney = giveMoney;
    }

    public Property getAskProperty() {
        return askProperty;
    }

    public void setAskProperty(Property askProperty) {
        this.askProperty = askProperty;
    }

    public Property getGiveProperty() {
        return giveProperty;
    }

    public void setGiveProperty(Property giveProperty) {
        this.giveProperty = giveProperty;
    }

    public String message() {
        StringBuilder message = new StringBuilder();
        message.append(source.getName()).append(" ").append("Предлагает\n")
                .append("Денег: ").append(getGiveMoney()).append("\n")
                .append("Собственность: ").append(getGiveProperty()).append("\n")
                .append("Просит\n")
                .append("Денег: ").append(getAskMoney()).append("\n")
                .append("Собственность: ").append(getAskProperty()).append("\n");
        return message.toString();
    }

}
