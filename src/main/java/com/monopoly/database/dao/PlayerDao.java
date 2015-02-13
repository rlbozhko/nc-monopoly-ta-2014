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

import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.MonopolyType;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.cells.PropertyStatus;
import com.monopoly.board.player.Player;
import com.monopoly.entity.PlayerEntity;
import com.monopoly.entity.PropertyCellEntity;

@Repository
public class PlayerDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BuildingDao buildingDao; 

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Player insert(Player object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Player object) {
		// TODO Auto-generated method stub

	}

	public void delete(Player object) {
		// TODO Auto-generated method stub

	}

	public List<Player> getAllByParentKey(long key) {
		String sql = "select property_cells.object_id object_id, property_cells.name p_name, basePrice.value basePrice,"
				+ "  baseRent.value baseRent, property_cells.description description, maxLevel.value maxLevel, "
				+ "  monopoly.value monopoly, payBackMoney.value payBackMoney, "
				+ "  pledgePercent.value pledgePercent, position.value position, status.value status, "
				+ "  turnsToPayBack.value turnsToPayBack "
				+ "from objects property_cells, attributes basePrice, attributes baseRent, "
				+ "  attributes maxLevel,attributes monopoly, "
				+ "  attributes payBackMoney, attributes pledgePercent, "
				+ "  attributes position, attributes status, attributes turnsToPayBack " 
				+ "where "
				+ "	 property_cells.object_type_id = 3 and" + "	property_cells.parent_id = " + key + " and"
				+ "	 basePrice.attr_id = 13 and" + "	basePrice.object_id = property_cells.object_id and"
				+ "	 baseRent.attr_id = 14 and" + "	baseRent.object_id = property_cells.object_id and"
				+ "	 maxLevel.attr_id = 16 and" + "	maxLevel.object_id = property_cells.object_id and"
				+ "	 monopoly.attr_id = 17 and" + "	monopoly.object_id = property_cells.object_id and"
				+ "	 payBackMoney.attr_id = 19 and" + "	payBackMoney.object_id = property_cells.object_id and"
				+ "	 pledgePercent.attr_id = 20 and" + "	pledgePercent.object_id = property_cells.object_id and"
				+ "	 position.attr_id = 21 and" + "	position.object_id = property_cells.object_id and"
				+ "	 status.attr_id = 22 and" + "	status.object_id = property_cells.object_id and"
				+ "	 turnsToPayBack.attr_id = 23 and" + "	turnsToPayBack.object_id = property_cells.object_id";
		List<Player> players = this.jdbcTemplate.query(sql,
				new RowMapper<Player>() {
					public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
						PlayerEntity entity = new PlayerEntity();
						entity.setName(rs.getString("p_name"));
//						entity.setBasePrice(Integer.parseInt(rs.getString("basePrice")));
//						entity.setBaseRent(Integer.parseInt(rs.getString("baseRent")));
//						entity.setDescription(rs.getString("description"));
//						entity.setMaxLevel(Integer.parseInt(rs.getString("maxLevel")));
//						entity.setMonopoly(Monopoly.getMonopoly(MonopolyType.valueOf(rs.getString("monopoly"))));
//						entity.setPayBackMoney(Integer.parseInt(rs.getString("payBackMoney")));
//						entity.setPledgePercent(Double.parseDouble(rs.getString("pledgePercent")));
//						entity.setPosition(Integer.parseInt(rs.getString("position")));
//						entity.setStatus(PropertyStatus.valueOf(rs.getString("status")));
//						entity.setTurnsToPayBack(Integer.parseInt(rs.getString("turnsToPayBack")));
//						int cell_id = Integer.parseInt(rs.getString("object_id"));
//						List<Building> buildings = buildingDao.getAllByParentKey(cell_id);
//						if (!buildings.isEmpty() && buildings.get(0) != null) {
//							entity.setBuilding(buildings.get(0));
//						}						
//						
						return new Player(entity);
					}
				});
		return players;		
	}
	
	public static void main(String[] args) {
//		ApplicationContext context =
//				new FileSystemXmlApplicationContext("test_files\\beans.xml");	            		
//		PlayerDao dao = context.getBean(PlayerDao.class);		
//		List<PropertyCell> cells = dao.getAllByParentKey(2);
//		System.out.println(cells);		
	}
}
