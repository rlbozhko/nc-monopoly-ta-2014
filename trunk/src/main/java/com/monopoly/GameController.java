package com.monopoly;

import java.util.ArrayList;
import java.util.List;

public class GameController implements Game {

	public static void main(String[] args) {
		List<Player> players = new ArrayList<>();
		List<PropertyCell> propertyCells = new ArrayList<>();
//		List<EventCell> eventCell = new ArrayList<>(); ???
		GameController gameController = new GameController();
		gameController.newGame(players, propertyCells);
	}

	@Override
	public void newGame(List<Player> players, List<PropertyCell> propertyCells) {
//		Board newBoard = new Board(players, propertyCells);
//		Thread gameThread = new Thread(newBoard);
//		gameThread.start();
	}

	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
	}

}
