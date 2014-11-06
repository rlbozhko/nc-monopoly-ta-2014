package com.monopoly;

import java.util.List;

public interface Game {
	public void newGame(List<Player> players, List<PropertyCell> propertyCells);
	public void stopGame();
	public void loadGame();
	public void saveGame();
}
