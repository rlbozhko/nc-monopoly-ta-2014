package com.monopoly.board.dice;

/**
 * Create By Kulikovsky Anton
 * */
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {
	private Dice dice;

	@Before
	public void before() {
		dice = Dice.getInstance();
	}

	@Test()
	public void singeltone() {
		Dice dice2 = Dice.getInstance();
		Assert.assertTrue(dice2 == dice);
	}
	
	@Test(timeout = 1000)
	public void initializationDie() {
		Assert.assertTrue(dice.getFaceDie1() == 0 && dice.getFaceDie2() == 0);
	}

	@Test(timeout = 10000)
	public void generateDiceValue() throws InterruptedException {
		dice.generateNewDiceValue();
		Assert.assertTrue(dice.getFaceDie1() > 0 && dice.getFaceDie2() > 0);
	}

	@Test(timeout = 10000)
	public void same() throws InterruptedException {
		boolean isSame = false;
		while (!isSame) {
			dice.generateNewDiceValue();
			if (dice.getFaceDie1() == dice.getFaceDie2()) {
				isSame = true;
			}
		}
		Assert.assertTrue(isSame);
	}
}
