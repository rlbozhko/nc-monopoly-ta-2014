package com.monopoly.board;

import com.monopoly.board.cells.Cell;

import java.util.List;

/**
 * Created by Roma on 06.11.2014.
 */
/**
 * ОперацииСЯчейками
 */
public interface BoardCellOperations {
    List<Cell> getCells();
    List<Cell> getPropertyCell();
    List<Cell> getEventCell();
}
