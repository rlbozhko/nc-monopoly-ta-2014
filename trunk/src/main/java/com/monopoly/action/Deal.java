package com.monopoly.action;

import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;

import java.util.List;

/**
 * Created by Roma on 24.11.2014.
 */
public class Deal {
    private int askMoney;
    private int giveMoney;
    private List<Property> askProperties;
    private List<Property> giveProperties;
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

    public List<Property> getAskProperties() {
        return askProperties;
    }

    public void addAskProperty(Property askProperty) {
        this.askProperties.add(askProperty);
    }

    public List<Property> getGiveProperties() {
        return giveProperties;
    }

    public void addGiveProperty(Property giveProperty) {
        this.giveProperties.add(giveProperty);
    }

    public String message() {
        StringBuilder message = new StringBuilder();
        message.append(source.getName()).append(" ").append("Предлагает\n")
                .append("Денег: ").append(getGiveMoney()).append("\n")
                .append("Собственность: ").append(getGiveProperties()).append("\n")
                .append("Просит\n")
                .append("Денег: ").append(getAskMoney()).append("\n")
                .append("Собственность: ").append(getAskProperties()).append("\n");
        return message.toString();
    }

}
