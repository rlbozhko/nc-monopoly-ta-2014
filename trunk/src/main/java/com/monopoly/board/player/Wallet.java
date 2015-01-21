package com.monopoly.board.player;

public class Wallet {
    private int money;

    public synchronized int getMoney() {
        return money;
    }

    public synchronized void addMoney(int amount) {
        money += amount;
    }

	public synchronized boolean subtractMoney(int amount) {
        if (money >= amount) {
        	money -= amount;
        	return true;
		}
    	return false;
    }
}
