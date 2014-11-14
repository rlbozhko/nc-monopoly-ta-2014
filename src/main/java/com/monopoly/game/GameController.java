package com.monopoly.game;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameController implements Game {

	public static void main(String[] args) {
		List<Player> players = new ArrayList<>();
		List<Cell> cells = new ArrayList<>();
		GameController gameController = new GameController();
		gameController.createGmae(players, cells);
		
	}

	@Override
	public void createGmae(List<Player> players, List<Cell> cells) {
		Board board = new Board(players, cells);
		Thread gameThread = new Thread(board);
		gameThread.start();
	}

	@Override
	public void stopGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
	}

}
