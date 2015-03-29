package com.monopoly.entity;

import com.monopoly.board.player.Status;

public class PlayerEntity {
	private int position;
	private int lastPosition;
	private String name;
	private Status status;
	private int wallet;
	private boolean payRent;
	private int jailTerm;
	private Status jailStatus;
	private boolean offerADeal;
	private int doublesCount;
	private String playerColor;
	private boolean extraTurn;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(int lastPosition) {
		this.lastPosition = lastPosition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public boolean isPayRent() {
		return payRent;
	}

	public void setPayRent(boolean payRent) {
		this.payRent = payRent;
	}

	public int getJailTerm() {
		return jailTerm;
	}

	public void setJailTerm(int jailTerm) {
		this.jailTerm = jailTerm;
	}

	public Status getJailStatus() {
		return jailStatus;
	}

	public void setJailStatus(Status jailStatus) {
		this.jailStatus = jailStatus;
	}

	public boolean isOfferADeal() {
		return offerADeal;
	}

	public void setOfferADeal(boolean offerADeal) {
		this.offerADeal = offerADeal;
	}

	public int getDoublesCount() {
		return doublesCount;
	}

	public void setDoublesCount(int doublesCount) {
		this.doublesCount = doublesCount;
	}

	public String getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}

	public boolean isExtraTurn() {
		return extraTurn;
	}

	public void setExtraTurn(boolean extraTurn) {
		this.extraTurn = extraTurn;
	}
}
