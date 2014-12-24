package com.monopoly.board.player;

/**
 * Created by mdolina on 07.11.2014.
 */

/**
 * Деньги
 */
public interface MoneyOperations {
    void addMoney(int money);
    void subtractMoney(int money);
    int getMoney();
}
