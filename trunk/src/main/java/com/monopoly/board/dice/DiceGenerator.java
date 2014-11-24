package com.monopoly.board.dice;

import com.monopoly.tools.XORShiftRandom;

public class DiceGenerator implements Runnable {
	private final int MAX_VALUE_ON_FACE = 6;
	private Dice die1;
	private Dice die2;
	private boolean flag;
	private XORShiftRandom xorShiftRandom = new XORShiftRandom();

	public DiceGenerator(Dice die1, Dice die2) {
		this.die1 = die1;
		this.die2 = die2;
	}
	
	public synchronized boolean finish(){
		this.flag = true;
		return flag;
	}

	@Override
	public void run() {
		while (!flag) {
			die1.setFace(xorShiftRandom.nextInt(MAX_VALUE_ON_FACE));
			die2.setFace(xorShiftRandom.nextInt(MAX_VALUE_ON_FACE));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
