package com.monopoly.board.dice;

/**
 * Create By Kulikovsky Anton
 * */
import com.monopoly.tools.XORShiftRandom;

public class ValueGeneratorForDice implements Runnable {
	private final int MAX_VALUE_ON_FACE = 6;
	private int value1;
	private int value2;
	private boolean flag;
	private XORShiftRandom xorShiftRandom = new XORShiftRandom();

	public synchronized boolean finish(){
		this.flag = true;
		return flag;
	}

	@Override
	public void run() {
		while (!flag) {
			value1 = xorShiftRandom.nextInt(MAX_VALUE_ON_FACE);
			value2 = xorShiftRandom.nextInt(MAX_VALUE_ON_FACE);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized int getValue1() {
		return value1;
	}

	public synchronized int getValue2() {
		return value2;
	}

}
