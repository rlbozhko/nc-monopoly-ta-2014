package com.monopoly.board.dice;

import java.util.concurrent.TimeUnit;

/**
 * Create By Kulikovsky Anton
 * */
import com.monopoly.tools.RandomStrategy;

public class ValueGeneratorForDice implements Runnable {
	private static final int MAX_VALUE_ON_FACE = 6;
	private static final int GENERATE_SLEEP_TIME = 100;
	private int value1;
	private int value2;
	private boolean isGeneratorEnabled;
	private RandomStrategy xorShiftRandomStrategy;
	
	public ValueGeneratorForDice (RandomStrategy xorShiftRandomStrategy) {
		this.xorShiftRandomStrategy = xorShiftRandomStrategy;
		this.value1 = 1;
		this.value2 = 1;
	}

	public synchronized boolean finish(){
		this.isGeneratorEnabled = true;
		return isGeneratorEnabled;
	}

	@Override
	public void run() {
		while (!isGeneratorEnabled) {
			value1 = xorShiftRandomStrategy.nextInt(MAX_VALUE_ON_FACE);
				try {
					TimeUnit.MILLISECONDS.sleep(GENERATE_SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			value2 = xorShiftRandomStrategy.nextInt(MAX_VALUE_ON_FACE);
		}
	}
	
	public void changeRandomStrategy(RandomStrategy xorShiftRandomStrategy) {
		this.xorShiftRandomStrategy = xorShiftRandomStrategy;
	}

	public synchronized int getValue1() {
		return value1;
	}

	public synchronized int getValue2() {
		return value2;
	}

}
