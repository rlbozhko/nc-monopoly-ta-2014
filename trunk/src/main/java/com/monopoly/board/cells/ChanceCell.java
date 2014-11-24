package com.monopoly.board.cells;

import com.monopoly.board.events.Event;
import com.monopoly.tools.XORShiftRandom;

import java.util.List;

public class ChanceCell extends Cell {

    private List<Event> events;
    private XORShiftRandom random;

    public ChanceCell(String name, String description, int position, List<Event> events) {
        super(name, description, CellType.EVENT_CELL, position);
        this.events = events;
        random = new XORShiftRandom();
    }

    public Event getEvent() {
        return events.get(random.nextInt(events.size()) - 1);
    }
}
