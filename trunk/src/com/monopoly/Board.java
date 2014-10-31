package com.monopoly;

import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Board {
    private List<Cell> cells;
    private List<Player> players;
    private Player currentPlayer;
    public void addCell(Cell cell) {

    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public Player nextPlayer() {
        return null;
    }
}
