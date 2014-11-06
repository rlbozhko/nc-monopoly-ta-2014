package com.monopoly;

import java.util.List;

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

    public Cell (String name, String description, CellType cellType) {
        this.name = name;
        this.description = description;
        this.cellType = cellType;
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
}

