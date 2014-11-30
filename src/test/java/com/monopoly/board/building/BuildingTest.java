package com.monopoly.board.building;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.monopoly.board.building.Building;
import com.monopoly.board.building.BuildingType;

public class BuildingTest {
	
	private Building building;
	private int primaryCost = 1000;
	private int maxLevel = 5;
	//private int lowLevel = 1;
	
	@Before
	public void prepareForTest (){
		this.building = new Building(BuildingType.CLUB, this.primaryCost
									, this.maxLevel);
	}
	
	@Test
	public void initPrimaryCostTest() {
		int expected = 1000;
		assertEquals(expected, building.getPrimaryCost());
	}

	@Test
	public void initPrimaryCostTest1() {
		int expected = -1000;
		assertNotEquals(expected, building.getPrimaryCost());
	}
	
	@Test
	public void initMaxLevelTest() {
		int expected = 5;
		assertEquals(expected, building.getMaxLevel());
	}
	
	@Test
	public void initMaxLevelTest1() {
		int expected = -5;
		assertNotEquals(expected, building.getMaxLevel());
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
}
