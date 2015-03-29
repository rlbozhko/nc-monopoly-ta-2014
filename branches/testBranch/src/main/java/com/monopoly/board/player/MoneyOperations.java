package com.monopoly.board.player;

/**
 * Created by mdolina on 07.11.2014.
 */

/**
 * Деньги
 */
public interface MoneyOperations {
    void addMoney(int money);
    boolean subtractMoney(int money);
    int getMoney();
}
