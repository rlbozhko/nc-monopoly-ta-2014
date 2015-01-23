package com.monopoly.board.building;

import org.junit.Test;
import org.junit.Assert;

public class BuildingTest {

	Building building = new Building(BuildingType.getTypeByText("CLUB"), 100);

	@Test
	public void PrimaryCostTest() {
		Assert.assertEquals(100, building.getPrimaryCost());
	}

	@Test
	public void getCurrentNameTest() {
		Assert.assertEquals("Club", building.getBuildingName());
	}

	@Test
	public void getCurrentPriceTest() {
		Assert.assertEquals(100, building.currentPrice());
	}

	@Test
	public void getCurrentLevelTest() {
		Assert.assertEquals(1, building.currentLevel());
	}

	@Test
	public void getBuildingDescriptionTest() {
		Assert.assertEquals("Увеличивают шансы Диверсии заказанной Владельцем", building.getBuildingDescription());
	}

	@Test
	public void getMaxLevelTest() {
		Assert.assertEquals(5, building.getMaxLevel());
	}

	@Test
	public void getLevelUpTest() {
		building.levelUp();
		Assert.assertEquals(2, building.currentLevel());
	}

	@Test
	public void getLevelDownTest() {
		building.levelDown();
		Assert.assertEquals(0, building.currentLevel());
	}
}
	/*@Test
	public void initPrimaryCostTest1() {
		int expected = -1000;
		Assert.assertNotEquals(expected, building.getPrimaryCost());
	}

	@Test
	public void initMaxLevelTest() {
		int expected = 5;
		Assert.assertEquals(expected, building.getMaxLevel());
	}

	@Test
	public void initMaxLevelTest1() {
		int expected = -5;
		Assert.assertNotEquals(expected, building.getMaxLevel());
	}
	/*
	@Test
	public void initNameTest() {
		String expected = "Club";
		assertEquals(expected, building.getPrimaryCost());
	}

	@Test
	public void initNameTest1() {
		String expected = "club";
		assertNotEquals(expected, building.getPrimaryCost());
	}
	*/

