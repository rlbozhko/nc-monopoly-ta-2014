package com.monopoly;

import java.util.List;

public interface Game {
	public void createGmae(List<Player> players, List<Cell> cells);
	public void stopGame();
	public void saveGame();
}
