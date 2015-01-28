package com.monopoly.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.GameSession.GameSessionBuilder;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.SessionStatus;

@Service
public class IndexService {
	
	public List<Cell> getCells() {
		Session session = GameSession.getInstance();
		List<Cell> cellsList = session.getBoard().getEventCell();
		return cellsList;
	}

	public List<Player> getPlayers() {
		Board board = GameSessionBuilder.getBoard();
		if (board != null) {
			return board.getPlayers();
		} else {
			return new ArrayList<Player>();
		}		
	}

	public Board createBoard(List<Player> players) {
		int startMoney = 5000;

		Board board = GameSession.newBoard(players, startMoney);
		return board;
	}

	public SessionStatus getStatus() {
		SessionStatus sessionStatus = GameSession.getStatus();
		return sessionStatus;
	}

	public void setStatus(String sessionStatusText) {
		GameSession.setStatus(SessionStatus.valueOf(sessionStatusText));
	}	
	
	public int getPlayersCount() {
		return GameSessionBuilder.getBoard().getPlayers().size();
	}	

	public int getMaxPlayers() {
		return GameSessionBuilder.getMaxPlayers();
	}

	public void setMaxPlayers(int maxPlayers) {
		GameSessionBuilder.setMaxPlayers(maxPlayers);
	}

}
