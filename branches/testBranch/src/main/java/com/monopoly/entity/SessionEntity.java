package com.monopoly.entity;

import java.util.HashMap;
import java.util.Map;

import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.io.IO;

public class SessionEntity {
	private Board board;
	private PropertyManager propertyManager;	
	private Map<User, IO> userIO = new HashMap<User, IO>();		
	private long id;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	public void setPropertyManager(PropertyManager propertyManager) {
		this.propertyManager = propertyManager;
	}
	
	public Map<User, IO> getUserIO() {
		return userIO;
	}

	public void setUserIO(Map<User, IO> userIO) {
		this.userIO = userIO;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
