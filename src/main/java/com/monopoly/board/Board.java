package com.monopoly.board;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Board implements CellOperations, PlayerOperations {
    private List<Player> players;
    private Player currentPlayer;
    private Player previousPlayer;
    private List<Cell> cells;
    private List<Cell> propertyCells = new ArrayList<>();
    private List<Cell> eventCells = new ArrayList<>();

    private Iterator<Player> playerIter;

    public Board(List<Player> players, List<Cell> cells) {
        this.cells = cells;
        updatePropertyEventCells();
        this.players = players;
        this.playerIter = players.iterator();
        currentPlayer = players.get(0);
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
        if (Status.FINISH.equals(next.getStatus())) {
            next = getNextPlayer();
        }
        return next;
    }

    @Override
    public Player getPreviousPlayer() {
        return previousPlayer;
    }
}
