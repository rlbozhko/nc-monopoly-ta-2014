package com.monopoly.board;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Roma on 31.10.2014.
 */
public class Board implements DiceOperations, CellOperations, PlayerOperations, Runnable {
    private LinkedList<Player> players;
    private Player currentPlayer;
    private Player previousPlayer;
    private LinkedList<Cell> cells;
    private List<Cell> propertyCells;
    private List<Cell> eventCells;
    private List <Dice> dices;

    private Iterator<Player> playerIter;

    public Board(LinkedList<Player> players, LinkedList<Cell> cells, List<Cell> propertyCells, List<Cell> eventCells, LinkedList<Dice> dices){
        this.cells = cells;
        this.propertyCells = propertyCells;
        this.eventCells = eventCells;
        this.players = players;
        this.dices = dices;
        this.playerIter = players.iterator();
    }

    public LinkedList<Cell> getCells() {
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
    public LinkedList<Player> getPlayers() {
        return players;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Player getNextPlayer() {
        if(playerIter.hasNext()) {
            previousPlayer = currentPlayer;
            currentPlayer = playerIter.next();
        }
        return currentPlayer;
    }

    @Override
    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    @Override
    public List<Dice> getDice() {
        return dices;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
