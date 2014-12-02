package com.monopoly.board.cells;

import com.monopoly.board.events.Event;
import com.monopoly.tools.XORShiftStrategy;

import java.util.List;

public class RandomEventCell extends Cell implements EventCell {

    private List<Event> events;
    private XORShiftStrategy random;

    public RandomEventCell(String name, String description, int position, List<Event> events) {
        super(name, description, CellType.EVENT_CELL, position);
        this.events = events;
        random = new XORShiftStrategy();
    }

    public Event getEvent() {
        return events.get(random.nextInt(events.size()) - 1);
    }
}
