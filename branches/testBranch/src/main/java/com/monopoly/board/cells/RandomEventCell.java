package com.monopoly.board.cells;

import com.monopoly.board.events.Event;
import com.monopoly.tools.RandomStrategy;
import com.monopoly.tools.XORShiftStrategy;

import java.util.List;

public class RandomEventCell extends EventCell {

    private List<Event> events;
    private RandomStrategy random;

    public RandomEventCell(String name, String description, int position, List<Event> events) {
        super(name, description, position);
        this.events = events;
        random = new XORShiftStrategy();
    }

    public Event getEvent() {
        return events.get(random.nextInt(events.size()) - 1);
    }
}
