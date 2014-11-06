package com.monopoly;

public class Wallet {
	int money;

	public int getMoney() {
		return money;
	}

	public void addMoney(int amount) {
		money = money + amount;
	}

	public void subtractMoney(int amount) {
		money = money - amount;
	}
}
