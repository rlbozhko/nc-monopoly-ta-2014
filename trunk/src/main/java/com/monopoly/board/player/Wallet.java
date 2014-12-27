package com.monopoly.board.player;

public class Wallet {
    int money;

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

	public boolean subtractMoney(int amount) {
        if (money >= amount) {
        	money -= amount;
        	return true;
		}
    	return false;
    }
}
