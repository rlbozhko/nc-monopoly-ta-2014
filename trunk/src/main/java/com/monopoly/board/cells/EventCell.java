package com.monopoly.board.cells;

import com.monopoly.board.events.Event;

/**
 * Created by Roma on 26.11.2014.
 */
public abstract class EventCell extends Cell {

	public EventCell(String name, String description, int position) {
		super(name, description, CellType.EVENT_CELL, position);
	}

	public abstract Event getEvent();
}
