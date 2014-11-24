package com.monopoly;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.monopoly.board.dice.Dice;

public class DiceTest {
	private Dice die;

	@Before
	public void before() {
		die = new Dice();
	}

	@Test(timeout = 1000)
	public void initializationDie() {
		Assert.assertTrue(die.getFace() == 0);
	}
	
	@Test(timeout = 1000)
	public void setDie() {
		die.setFace(5);
		Assert.assertTrue(die.getFace() == 5);
	}
}
