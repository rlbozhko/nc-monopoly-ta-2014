package com.monopoly.board.events;

public abstract class BaseEvent implements Event {
	String name;
	String description;

	@Override
	public abstract void performEvent();

	@Override
	public synchronized String getName() {
		return name;
	}

	@Override
	public synchronized String getDescription() {
		return description;
	}

}
