package com.monopoly.board.dice;

/**
 * Create By Kulikovsky Anton
 * */
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.monopoly.board.dice.Dice;
import com.monopoly.board.dice.ValueGeneratorForDice;

public class DiceTest {
	private Dice dice;
	private ValueGeneratorForDice valueGeneratorForDice;
	private Thread diceThread;

	@Before
	public void before() {
		valueGeneratorForDice = new ValueGeneratorForDice();
		dice = new Dice(valueGeneratorForDice);
		diceThread = new Thread(valueGeneratorForDice);
	}

	@Test(timeout = 1000)
	public void initializationDie() {
		Assert.assertTrue(dice.getFaceDie1() == 0 && dice.getFaceDie2() == 0);
	}

	@Test(timeout = 10000)
	public void generateDice() throws InterruptedException {
		diceThread.start();
		TimeUnit.MILLISECONDS.sleep(200);
		valueGeneratorForDice.finish();
		TimeUnit.MILLISECONDS.sleep(200);
		dice.generateNewDiceValue();
		Assert.assertTrue(dice.getFaceDie1() > 0 && dice.getFaceDie2() > 0);
	}

	@Test()
	public void same() throws InterruptedException {
		boolean flag = false;
		diceThread.start();
		while (!flag) {
			TimeUnit.SECONDS.sleep(1);
			dice.generateNewDiceValue();
			System.out.println(dice.getFaceDie1() + " " + dice.getFaceDie2());
			if (dice.isSame()) {
				valueGeneratorForDice.finish();
				flag = true;
			}

		}

	}
}
