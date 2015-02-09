package com.monopoly.board.events;

public abstract class Event {	
	protected String description;
	
	public abstract void performEvent();	
	
	public synchronized String getDescription() {
		return description;
	}

}
