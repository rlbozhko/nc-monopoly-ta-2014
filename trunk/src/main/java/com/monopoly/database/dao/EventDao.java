package com.monopoly.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monopoly.board.events.Event;
import com.monopoly.board.events.EventType;

@Repository
public class EventDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Event insert(Event object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Event object) {
		// TODO Auto-generated method stub

	}

	public void delete(Event object) {
		// TODO Auto-generated method stub

	}
	
	public List<Event> getAllByParentKey(int key) {
		String sql = "select event.name event_type, event.description event_description "
				+ "from objects event_cells, objects event " 
				+ "where " 
				+ "  event_cells.object_id = " + key + " and "
				+ "  event.object_type_id = 10 and event.parent_id = event_cells.object_id";
		List<Event> events = this.jdbcTemplate.query(sql, new RowMapper<Event>() {
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				String description = rs.getString("event_description");
				if (description != null) {
					return EventType.valueOf(rs.getString("event_type")).create(description);
				} else {
					return EventType.valueOf(rs.getString("event_type")).create();
				}				
			}
		});
		return events;
	}

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("test_files\\beans.xml");
		EventDao dao = context.getBean(EventDao.class);
		List<Event> cells = dao.getAllByParentKey(104);
		System.out.println(cells);
	}
}
