package com.monopoly.board.cells;

import com.monopoly.board.events.Event;

public class EventCell extends Cell {

    Event event;

    public EventCell(String name, String description, int position, Event event) {
        super(name, description, CellType.EVENT_CELL, position);
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
