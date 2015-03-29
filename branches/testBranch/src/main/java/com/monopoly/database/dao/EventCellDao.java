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

import com.monopoly.board.cells.EventCell;
import com.monopoly.entity.EventCellEntity;

@Repository
public class EventCellDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EventDao eventDao;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public EventCell insert(EventCell object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(EventCell object) {
		// TODO Auto-generated method stub

	}

	public void delete(EventCell object) {
		// TODO Auto-generated method stub

	}

	public List<EventCell> getAllByParentKey(long key) {
		String sql = 
				"select event_cells.object_id, event_cells.name name, event_cells.description description, "
				+ "  position.value position " + "from objects event_cells, attributes position " 
				+ "where "
				+ "  event_cells.object_type_id = 6 and " + "  event_cells.parent_id = " + key + " and "
				+ "  position.attr_id = 2 and " + "  position.object_id = event_cells.object_id";
		List<EventCell> eventCells = this.jdbcTemplate.query(sql, new RowMapper<EventCell>() {
			public EventCell mapRow(ResultSet rs, int rowNum) throws SQLException {
				EventCellEntity entity = new EventCellEntity();
				entity.setName(rs.getString("name"));
				entity.setDescription(rs.getString("description"));
				entity.setPosition(Integer.parseInt(rs.getString("position")));
				int cell_id = Integer.parseInt(rs.getString("object_id"));				
				entity.setEvents(eventDao.getAllByParentKey(cell_id));
				
				return entity.createEventCell();
			}
		});
		return eventCells;
	}

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("test_files\\beans.xml");
		EventCellDao dao = context.getBean(EventCellDao.class);
		List<EventCell> cells = dao.getAllByParentKey(101);
		System.out.println(cells);
	}
}
