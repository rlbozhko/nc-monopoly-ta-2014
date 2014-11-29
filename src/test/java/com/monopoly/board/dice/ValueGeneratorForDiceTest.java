package com.monopoly.board.dice;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.monopoly.board.dice.Dice;
import com.monopoly.board.dice.ValueGeneratorForDice;
/**
 * Create By Kulikovsky Anton
 * */
public class ValueGeneratorForDiceTest {
//	private ValueGeneratorForDice diceGenerator;
//	private Dice die1;
//	private Dice die2;
//
//	@Before
//	public void before() {
//		die1 = new Dice();
//		die2 = new Dice();
//		diceGenerator = new ValueGeneratorForDice();
//		Thread diceThred = new Thread(diceGenerator);
//		diceThred.start();
//
//	}
//	
//	@Test
//	public void generateValue() throws InterruptedException {
//		TimeUnit.SECONDS.sleep(1);
//		die1.setFace(diceGenerator.getValue1()); 
//		die2.setFace(diceGenerator.getValue2());
//		Assert.assertTrue(die1.getFace() > 0 && die2.getFace() > 0);
//	}
//
//	@Test
//	public void generateValidValue() throws InterruptedException {
//		TimeUnit.SECONDS.sleep(1);
//		boolean isValid = true;
//		for (int i = 0; i < 100; i++) {
//			die1.setFace(diceGenerator.getValue1());
//			die2.setFace(diceGenerator.getValue2());
//			if (die1.getFace() > 6 || die2.getFace() > 6) {
//				System.out.println(die1.getFace() + " " + die2.getFace());
//				isValid = false;
//				break;
//			}
//			if (die1.getFace() == 0 || die2.getFace() == 0) {
//				System.out.println(die1.getFace() + " " + die2.getFace());
//				isValid = false;
//				break;
//			}
//			TimeUnit.SECONDS.sleep(2);
//		}
//		Assert.assertTrue(isValid);
//	}
//	
//	@Test
//	public void finishThread() throws InterruptedException {
//		Dice die3 = new Dice();
//		ValueGeneratorForDice diceGenerator2 = new ValueGeneratorForDice();
//		Thread diceThred2 = new Thread(diceGenerator2);
//		diceThred2.start();
//		for(int i = 0; i < 20; i++) {
//			die3.setFace(diceGenerator2.getValue1());
//			TimeUnit.SECONDS.sleep(1);
//		}
//		diceGenerator2.finish();
//		TimeUnit.SECONDS.sleep(2);
//		boolean isStop = true;
//		int temp = die3.getFace();
//		System.out.println(temp + " " + die3.getFace());
//		for (int i = 0; i < 10; i++) {
//			die3.setFace(diceGenerator2.getValue1());
//			System.out.println(temp + " " + die3.getFace());
//			if (temp != die3.getFace()) {
//				isStop = false;
//				break;
//			}
//			TimeUnit.SECONDS.sleep(1);
//		}
//		Assert.assertTrue(isStop);
//	}
}
