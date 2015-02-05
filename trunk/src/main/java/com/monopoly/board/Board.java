package com.monopoly.board;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.events.GoToJailEvent;
import com.monopoly.board.events.JailEvent;
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
    private Cell jailCell;
    private Cell goToJailCell;

    public Board(List<Player> players, List<Cell> cells) {
        this.cells = cells;
        updateCellLists();
        this.players = players;
        currentPlayer = findStartPlayer();
    }

    public Board(List<Player> players, Player currentPlayer, List<Cell> cells) {
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.cells = cells;
        updateCellLists();
    }

    private void updateCellLists() {
        for (Cell cell : cells) {
            if (CellType.PROPERTY_CELL.equals(cell.getCellType())) {
                propertyCells.add(cell);
            } else {
                eventCells.add(cell);
                EventCell eventCell = (EventCell) cell;
                if (eventCell.getEvent() instanceof JailEvent) {
                	jailCell = eventCell;
                } else if (eventCell.getEvent() instanceof GoToJailEvent) {
                	goToJailCell = eventCell;
                }
            }
        }
    }
    
    private Player findStartPlayer() {
    	for (Player player : players) {
			if (player.getStatus() == Status.START_TURN) {
				return player;
			}			
		}
    	Player defaultPlayer = players.get(0);
    	defaultPlayer.setStatus(Status.START_TURN);
    	return defaultPlayer;
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
        return jailCell;
    }

    @Override
	public Cell getGoToJailCell() {		
		return goToJailCell;
	}

	@Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public synchronized Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public synchronized Player getNextPlayer() {        
        currentPlayer.resetTimer();
    	currentPlayer = nextPlayer(currentPlayer);
        currentPlayer.startTimer();
        return currentPlayer;
    }
    
    private Player nextPlayer(Player cursor) {
        int index = players.indexOf(cursor);
        Player next;
        if (index == players.size() - 1) {
            next = players.get(0);
        } else {
            next = players.get(index + 1);
        }
        cursor = next;
        next.setOfferADeal(false);
        if (Status.FINISH == next.getStatus()) {
            next = nextPlayer(cursor);
        } else if (Status.SKIP_TURN == next.getStatus()) {
        	next.setStatus(Status.WAIT);
        	next = nextPlayer(cursor);
        }
        return next;
    }

	@Override
	public List<Player> getActivePlayers() {		
		ArrayList<Player> activePlayers = new ArrayList<>();
		for (Player player : players) {
			if (player.getStatus() != Status.FINISH) {
				activePlayers.add(player);
			}
		}
		return activePlayers;
	}
}
