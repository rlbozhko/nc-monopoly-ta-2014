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
	private RandomStrategy randomStrategy;
	
	public ValueGeneratorForDice (RandomStrategy randomStrategy) {
		this.randomStrategy = randomStrategy;
		this.value1 = 1;
		this.value2 = 1;
		this.isGeneratorEnabled = true;
	}

	public synchronized void finish(){
		this.isGeneratorEnabled = false;
		
	}

	@Override
	public void run() {
		while (isGeneratorEnabled) {
			value1 = randomStrategy.nextInt(MAX_VALUE_ON_FACE);
				try {
					TimeUnit.MILLISECONDS.sleep(GENERATE_SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			value2 = randomStrategy.nextInt(MAX_VALUE_ON_FACE);
		}
	}
	
	public void changeRandomStrategy(RandomStrategy strategy) {
		this.randomStrategy = strategy;
	}

	public synchronized int getValue1() {
		return value1;
	}

	public synchronized int getValue2() {
		return value2;
	}

}
