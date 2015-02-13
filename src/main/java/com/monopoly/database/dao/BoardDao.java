package com.monopoly.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monopoly.board.Board;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.MonopolyType;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.cells.PropertyStatus;
import com.monopoly.board.player.Player;
import com.monopoly.entity.BoardEntity;
import com.monopoly.entity.PropertyCellEntity;
import com.monopoly.entity.SessionEntity;

@Repository
public class BoardDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PropertyCellDao propertyDao;
	
	@Autowired
	private EventCellDao eventCellDao;

	@Autowired
	private PlayerDao playerDao;

	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Board insert(Board object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Board object) {
		// TODO Auto-generated method stub

	}

	public void delete(Board object) {
		// TODO Auto-generated method stub

	}

	public Board getByParentKey(long key) {
		String sql = "select board.object_id id from objects board where board.parent_id = ? and board.object_type_id = 2";
		Board board = this.jdbcTemplate.queryForObject(sql, new Object[] {key}, new RowMapper<Board>() {
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardEntity entity = new BoardEntity();
				long boardId = rs.getLong("id"); 
				System.out.println("boardId = " + boardId);
				
				List<PropertyCell> propertyCells = propertyDao.getAllByParentKey(boardId);
				List<EventCell> eventCells = eventCellDao.getAllByParentKey(boardId);
				
				System.out.println("propertyCells = " + propertyCells);
				System.out.println("eventCells = " + eventCells);
				
				
				int size = propertyCells.size() + eventCells.size();
				List<Cell> cells = new ArrayList<Cell>(size);
				for (int i = 0; i < size; i++) {
					cells.add(null);
				}

				for (PropertyCell cell : propertyCells) {
					cells.set(cell.getPosition(), cell);					
				}

				for (EventCell cell : eventCells) {
					cells.set(cell.getPosition(), cell);
				}
				
				System.out.println("cells = " + cells);
				
				List<Player> players = playerDao.getAllByParentKey(boardId);
				
				System.out.println("players = " + players);
				
				entity.setCells(cells);
				entity.setPlayers(players);				
				
				return new Board(entity);
			}
		});
		return board;
	}

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("test_files\\beans.xml");
		BoardDao dao = context.getBean(BoardDao.class);
		Board board = dao.getByParentKey(100);
		System.out.println(board);		
		
	}
}
