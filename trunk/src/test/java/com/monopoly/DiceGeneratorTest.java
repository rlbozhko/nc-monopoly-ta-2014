package com.monopoly;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.monopoly.board.dice.Dice;
import com.monopoly.board.dice.DiceGenerator;
/**
 * Create By Kulikovsky Anton
 * */
public class DiceGeneratorTest {
	private DiceGenerator diceGenerator;
	private Dice die1;
	private Dice die2;

	@Before
	public void before() {
		die1 = new Dice();
		die2 = new Dice();
		diceGenerator = new DiceGenerator(die1, die2);
		Thread diceThred = new Thread(diceGenerator);
		diceThred.start();

	}
	
	@Test
	public void generateValue() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		Assert.assertTrue(die1.getFace() > 0 && die2.getFace() > 0);
	}

	@Test
	public void generateValidValue() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		boolean isValid = true;
		for (int i = 0; i < 100; i++) {
			if (die1.getFace() > 6 || die2.getFace() > 6) {
				isValid = false;
				break;
			}
			if (die1.getFace() == 0 || die2.getFace() == 0) {
				isValid = false;
				break;
			}
			TimeUnit.SECONDS.sleep(2);
		}
		Assert.assertTrue(isValid);
	}
	
	@Test
	public void finishThread() throws InterruptedException {
		Dice die3 = new Dice();
		Dice die4 = new Dice();
		DiceGenerator diceGenerator2 = new DiceGenerator(die3, die4);
		Thread diceThred2 = new Thread(diceGenerator2);
		diceThred2.start();
		TimeUnit.SECONDS.sleep(5);
		diceGenerator.finish();
		TimeUnit.SECONDS.sleep(2);
		boolean isStop = true;
		int temp = die3.getFace();
		for (int i = 0; i < 10; i++) {
			if (temp != die3.getFace()) {
				isStop = false;
			}
		}
		Assert.assertTrue(isStop);
	}
}
