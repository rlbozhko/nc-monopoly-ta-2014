package com.monopoly.board;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.players.Player;

import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Board implements Runnable {
    private List<Cell> cells;
    private List<Player> players;
    private Player currentPlayer;

    public Board(List<Player> players, List<Cell> cells){
        this.cells = cells;
        this.players = players;
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}