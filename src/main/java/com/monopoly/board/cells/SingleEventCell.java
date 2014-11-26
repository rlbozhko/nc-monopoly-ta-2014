package com.monopoly.board.cells;

import com.monopoly.board.events.Event;

public class SingleEventCell extends Cell implements EventCell {

    Event event;

    public SingleEventCell(String name, String description, int position, Event event) {
        super(name, description, CellType.EVENT_CELL, position);
        this.event = event;
    }

    @Override
    public Event getEvent() {
        return event;
    }
}
