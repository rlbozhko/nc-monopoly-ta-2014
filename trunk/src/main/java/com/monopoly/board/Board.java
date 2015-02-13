package com.monopoly.board;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.events.GoToJailEvent;
import com.monopoly.board.events.JailEvent;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.entity.BoardEntity;
import com.monopoly.game.session.GameSession;

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
    
    public Board(BoardEntity entity) {
        this.cells = entity.getCells();
        updateCellLists();
        this.players = entity.getPlayers();
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
            if (CellType.PROPERTY_CELL == cell.getCellType()) {
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
        if (next.hasPledgedProperty()) {
			pledgedPropertyCheck(next);
		}
        if (Status.FINISH == next.getStatus()) {
            next = nextPlayer(cursor);
        } else if (Status.SKIP_TURN == next.getStatus()) {
        	next.setStatus(Status.WAIT);
        	next = nextPlayer(cursor);
        }
        return next;
    }
    
    private void pledgedPropertyCheck(Player player) {
		for (Property property : getProperties(player)) {
			if (property.isPledged()) {
				property.decrementTurnsToPayBack();
				property.risePayBackMoney();
				propertyWarning(player, property);
			}
		}
	}

	private List<Property> getProperties(Player player) {
		return GameSession.getInstance().getPropertyManager().getPlayerProperties(player);
	}

	private void propertyWarning(Player player, Property property) {
		if (property.getTurnsToPayBack() == 0) {
			ActionUtils.getPlayerIO(player).showWarning(
					"ВНИМАНИЕ!!!" + "Срок погашения заема истек для " + ((Cell) property).getName() + "."
							+ "Если вы не погасите задолженность, то по окончанию хода начнется аукцион");
		}
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
