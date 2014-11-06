package com.monopoly;

import com.monopoly.tools.XORShiftRandom;

public class DiceGenerator implements Runnable {
	private final int MAX_VALUE_ON_FACE = 6;
	private Dice die1;
	private Dice die2;
	private XORShiftRandom xorShiftRandom = new XORShiftRandom();

	public DiceGenerator(Dice die1, Dice die2) {
		this.die1 = die1;
		this.die2 = die2;
	}

	@Override
	public void run() {
		while (true) {
			die1.setFace(xorShiftRandom.nextInt(MAX_VALUE_ON_FACE));
			die2.setFace(xorShiftRandom.nextInt(MAX_VALUE_ON_FACE));
		}
	}
}
