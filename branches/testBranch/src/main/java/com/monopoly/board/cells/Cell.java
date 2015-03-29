package com.monopoly.board.cells;

import java.util.List;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;

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
    
    public abstract String getColor();
    
    public abstract String getOwnerColor();

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
    
    public boolean hasEscapedPlayers() {
    	List<Player> players = GameSession.getInstance().getBoard().getPlayers(); 
    	for (Player player : players) {
    		if (player.getPosition() == position && player.getJailStatus() == Status.ESCAPE) {
    			return true;
    		}
    	}
    	return false;
    }

    @Override
    public String toString() {
        return name;
    }

	public boolean isBuildPerformed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setBuildPerformed(boolean buildPerformed) {
		// TODO Auto-generated method stub
		
	}
}

