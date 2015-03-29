package com.monopoly.board.dice;

import java.util.concurrent.TimeUnit;

import com.monopoly.tools.RandomStrategy;
import com.monopoly.tools.XORShiftStrategy;

/**
 * Create By Kulikovsky Anton
 * */

public class Dice {
	private static final int NEW_DICE_VALUE_SLEEP = 100;
	private static volatile Dice dice;
	private int faceDie1;
	private int faceDie2;
	private ValueGeneratorForDice valueGeneratorForDice;

	private Dice() {
		this.valueGeneratorForDice = new ValueGeneratorForDice(
				new XORShiftStrategy());
		Thread threadGenerator = new Thread(valueGeneratorForDice);
		threadGenerator.start();
	}

	public static Dice getInstance() {
		Dice localInstance = dice;
		if (dice == null) {
			synchronized (Dice.class) {
				localInstance = dice;
				if (localInstance == null) {
					dice = localInstance = new Dice();
				}
			}
		}
		return dice;
	}

	public void changeRandomStrategy(RandomStrategy strategy) {
		valueGeneratorForDice.changeRandomStrategy(strategy);
	}

	public synchronized boolean isSame() {
		return faceDie1 == faceDie2;
	}

	public synchronized void generateNewDiceValue() {
		this.faceDie1 = valueGeneratorForDice.getValue1();
		try {
			TimeUnit.MILLISECONDS.sleep(NEW_DICE_VALUE_SLEEP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.faceDie2 = valueGeneratorForDice.getValue2();
	}
	
	public void finish() {
		synchronized (Dice.class) {
			valueGeneratorForDice.finish();
			dice = null;
		}
	}

	public synchronized int getFaceDie1() {
		return faceDie1;
	}

	public synchronized int getFaceDie2() {
		return faceDie2;
	}
}
