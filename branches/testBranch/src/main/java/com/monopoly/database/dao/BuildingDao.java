package com.monopoly.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monopoly.board.building.Building;
import com.monopoly.board.building.BuildingType;

@Repository
public class BuildingDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	public Building insert(Building object) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void update(Building object) {	

	}	
	
	public void delete(Building object) {	

	}
	
	public List<Building> getAllByParentKey(int key) {
		String sql = "select property_cells.object_id property_cell_id, building.name building_type, "
				+ "  currentLevel.value currentLevel, maxLevel.value maxLevel, "
				+ "  primaryCost.value primaryCost "
				+ "from objects property_cells, objects building, attributes currentLevel, "
				+ "  attributes maxLevel, attributes primaryCost " 
				+ "where "
				+ "  property_cells.object_id = " + key + " and" + "  building.object_type_id = 5 and"
				+ "  building.parent_id = property_cells.object_id and" 
				+ "  currentLevel.attr_id = 24 and"
				+ "  currentLevel.object_id = building.object_id and"  
				+ "  maxLevel.attr_id = 27 and"	
				+ "  maxLevel.object_id = building.object_id and" 
				+ "  primaryCost.attr_id = 29 and" 
				+ "  primaryCost.object_id = building.object_id";
		List<Building> buildings = this.jdbcTemplate.query(sql, new RowMapper<Building>() {
			public Building mapRow(ResultSet rs, int rowNum) throws SQLException {
				BuildingType type = BuildingType.valueOf(rs.getString("BUILDING_TYPE"));
				int cost = Integer.parseInt(rs.getString("PRIMARYCOST"));
				Building building = new Building(type, cost);
				building.setCurrentLevel(Integer.parseInt(rs.getString("CURRENTLEVEL")));				
				building.setPrimaryCost(Integer.parseInt(rs.getString("PRIMARYCOST")));

				return building;
			}
		});
		return buildings;
	}
}
