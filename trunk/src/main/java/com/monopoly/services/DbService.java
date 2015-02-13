package com.monopoly.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.database.dao.EventCellDao;
import com.monopoly.database.dao.PropertyCellDao;

@Component
public class DbService {

	@Autowired
	private EventCellDao eventCellDao;

	@Autowired
	private PropertyCellDao propertyCellDao;

	public Board newBoard(List<Player> players, int startMoney) {
		List<PropertyCell> propertyCells = propertyCellDao.getAllByParentKey(101);
		List<EventCell> eventCells = eventCellDao.getAllByParentKey(101);
		int size = propertyCells.size() + eventCells.size();
		List<Cell> cells = new ArrayList<Cell>(size);
		for (int i = 0; i < size; i++) {
			cells.add(null);
		}

		for (PropertyCell cell : propertyCells) {
			cells.set(cell.getPosition(), cell);
			// System.out.println(cell.getPosition());
		}

		for (EventCell cell : eventCells) {
			cells.set(cell.getPosition(), cell);
			// System.out.println(cell.getName());
			// System.out.println(cell.getDescription());
			// System.out.println(cell.getPosition());
			// System.out.println(cell.getCellType());
			// System.out.println(cell.getEvent().getDescription());
		}

		// System.out.println(proertyCells);
		// System.out.println(eventCells);
		// System.out.println(cells);

		for (Player player : players) {
			player.addMoney(startMoney);
		}

		return new Board(players, cells);
	}

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("test_files\\beans.xml");
		DbService dbs = context.getBean(DbService.class);
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("p1"));
		players.add(new Player("p2"));

		Board board = dbs.newBoard(players, 5000);
		System.out.println(board);
	}
}
