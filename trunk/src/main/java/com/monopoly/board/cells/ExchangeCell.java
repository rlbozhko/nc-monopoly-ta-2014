package com.monopoly.board.cells;

import com.monopoly.board.events.Event;

public class ExchangeCell extends Cell implements EventCell {
	private String name;
	private String description;
	private int position;
	
	public ExchangeCell(String name, 
			String description, CellType cellType, int position) {
		super(name, description, cellType, position);
		
	}
	
	@Override
	public Event getEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
