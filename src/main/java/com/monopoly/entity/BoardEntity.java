package com.monopoly.entity;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;

public class BoardEntity {
	private List<Player> players;
	private List<Cell> cells;

	public BoardEntity() {
		players = new ArrayList<Player>();
		cells = new ArrayList<Cell>(40);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
	
	public void addCell(Cell cell) {
		cells.add(cell);
	}
}
