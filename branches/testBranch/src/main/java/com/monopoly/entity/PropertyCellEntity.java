package com.monopoly.entity;

import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.PropertyStatus;

public class PropertyCellEntity {
	private Building building;
	private int basePrice;
	private int baseRent;
	private Monopoly monopoly;
	private PropertyStatus status;
	private int maxLevel;	
	private int turnsToPayBack;
	private int payBackMoney;
	private double pledgePercent;
	private int position;
	private String description;
	private String name;

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public int getBaseRent() {
		return baseRent;
	}

	public void setBaseRent(int baseRent) {
		this.baseRent = baseRent;
	}

	public Monopoly getMonopoly() {
		return monopoly;
	}

	public void setMonopoly(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	public PropertyStatus getStatus() {
		return status;
	}

	public void setStatus(PropertyStatus status) {
		this.status = status;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public int getTurnsToPayBack() {
		return turnsToPayBack;
	}

	public void setTurnsToPayBack(int turnsToPayBack) {
		this.turnsToPayBack = turnsToPayBack;
	}

	public int getPayBackMoney() {
		return payBackMoney;
	}

	public void setPayBackMoney(int payBackMoney) {
		this.payBackMoney = payBackMoney;
	}

	public double getPledgePercent() {
		return pledgePercent;
	}

	public void setPledgePercent(double pledgePercent) {
		this.pledgePercent = pledgePercent;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
