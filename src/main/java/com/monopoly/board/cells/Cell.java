package com.monopoly.board.cells;

/**
 * Created by Roma on 31.10.2014.
 */

/**
 * Ячейка
 */
public abstract class Cell {
    private String name;
    private String description;
    private CellType cellType;
    private int position;

    public Cell(String name, String description, CellType cellType, int position) {
        this.name = name;
        this.description = description;
        this.cellType = cellType;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CellType getCellType() {
        return cellType;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return name;
    }
}

