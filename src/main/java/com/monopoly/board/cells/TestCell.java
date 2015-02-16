package com.monopoly.board.cells;

/**
 * Created by Roma on 20.11.2014.
 */
public class TestCell extends Cell {
    public TestCell(String name, String description, CellType cellType, int position) {
        super(name, description, cellType, position);
    }

	@Override
	public String getColor() {		
		return "white";
	}
}
