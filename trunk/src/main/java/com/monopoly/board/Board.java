package com.monopoly.board;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Board implements CellOperations, PlayerOperations {
    private List<Player> players;
    private Player currentPlayer;
    private List<Cell> cells;
    private List<Cell> propertyCells = new ArrayList<>();
    private List<Cell> eventCells = new ArrayList<>();

    public Board(List<Player> players, List<Cell> cells) {
        this.cells = cells;
        updatePropertyEventCells();
        this.players = players;
        currentPlayer = players.get(0);
    }

    public Board(List<Player> players, Player currentPlayer, List<Cell> cells) {
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.cells = cells;
        updatePropertyEventCells();
    }

    private void updatePropertyEventCells() {
        for (Cell cell : cells) {
            if (CellType.PROPERTY_CELL.equals(cell.getCellType())) {
                propertyCells.add(cell);
            } else {
                eventCells.add(cell);
            }
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    @Override
    public List<Cell> getPropertyCell() {
        return propertyCells;
    }

    @Override
    public List<Cell> getEventCell() {
        return eventCells;
    }

    @Override
    public void performStartEvent() {
        ((EventCell) cells.get(0)).getEvent().performEvent();
    }

    @Override
    public Cell getJailCell() {
        return cells.get(10);
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Player getNextPlayer() {
        int index = players.indexOf(currentPlayer);
        Player next;
        if (index == players.size() - 1) {
            next = players.get(0);
        } else {
            next = players.get(index + 1);
        }
        currentPlayer = next;
        if (Status.FINISH == next.getStatus()) {
            next = getNextPlayer();
        }
        return next;
    }
}
