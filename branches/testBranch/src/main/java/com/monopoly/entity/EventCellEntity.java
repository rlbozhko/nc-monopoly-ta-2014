package com.monopoly.entity;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.board.cells.EventCell;
import com.monopoly.board.cells.RandomEventCell;
import com.monopoly.board.cells.SingleEventCell;
import com.monopoly.board.events.Event;

public class EventCellEntity {
	private String name;
	private String description;
	private int position;
	private List<Event> events = new ArrayList<Event>();	

	public EventCell createEventCell() {
		if (events.size() == 1) {
			return new SingleEventCell(name, description, position, events.get(0));
		} else {
			return new RandomEventCell(name, description, position, events);
		}
	}
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
