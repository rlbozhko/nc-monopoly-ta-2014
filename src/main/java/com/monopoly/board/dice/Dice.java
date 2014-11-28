package com.monopoly.board.dice;
/**
 * Create By Kulikovsky Anton
 * */

public class Dice {
    private int faceDie1;
    private int faceDie2;
    ValueGeneratorForDice valueGeneratorForDice;

    public Dice(ValueGeneratorForDice valueGeneratorForDice) {
       this.valueGeneratorForDice = valueGeneratorForDice;
    }

    public synchronized boolean isSame() {
    	return faceDie1 == faceDie2;
    }
    
    public synchronized int getNewFaceDie1() {
		this.faceDie1 = valueGeneratorForDice.getValue1();
		return this.faceDie1;
	}

	public synchronized int getNewFaceDie2() {
		this.faceDie2 = valueGeneratorForDice.getValue2();
		return this.faceDie2;
	}

	public synchronized int getFaceDie1() {
		return faceDie1;
	}

	public synchronized int getFaceDie2() {
		return faceDie2;
	}
}
