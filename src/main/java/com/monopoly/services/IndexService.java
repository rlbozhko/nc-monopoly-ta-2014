package com.monopoly.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.SessionStatus;

@Service
public class IndexService {
	
	public List<Cell> getCells() {
		Session session = GameSession.getInstance();
		List<Cell> cellsList = session.getBoard().getEventCell();
		return cellsList;
	}

	public List<Player> getPlayers(Integer countPlayers) {
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < countPlayers; i++) {
			players.add(new Player("Player " + (i + 1)));
		}
		
		players.get(0).setStatus(Status.START_TURN);
		return players;
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

}
