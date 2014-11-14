package com.monopoly;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.players.Player;

import java.util.List;

public interface Game {
	public void createGmae(List<Player> players, List<Cell> cells);
	public void stopGame();
	public void saveGame();
}
