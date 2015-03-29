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

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.entity.PlayerEntity;

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
		String sql = "select player.object_id, player.name name, doublesCount.value doublesCount, "
				+ "  jailStatus.value jailStatus, jailTerm.value jailTerm, "
				+ "  extraTurn.value extraTurn, offerADeal.value offerADeal, "
				+ "  payRent.value payRent, position.value position, status.value status, "
				+ "  wallet.value wallet, player.description playerColor "
				+ "from objects player, attributes doublesCount, attributes jailStatus, "
				+ "  attributes jailTerm,attributes extraTurn,attributes offerADeal, "
				+ "  attributes payRent,attributes position,attributes status, " + "  attributes wallet " + "where "
				+ "  player.object_type_id = 4 and " + "  player.parent_id = ? and "
				+ "  doublesCount.attr_id = 3 and	doublesCount.object_id = player.object_id and "
				+ "  jailStatus.attr_id = 4 and	jailStatus.object_id = player.object_id and "
				+ "  jailTerm.attr_id = 5 and	jailTerm.object_id = player.object_id and "
				+ "  extraTurn.attr_id = 6 and	extraTurn.object_id = player.object_id and "
				+ "  offerADeal.attr_id = 7 and	offerADeal.object_id = player.object_id and "
				+ "  payRent.attr_id = 8 and	payRent.object_id = player.object_id and "
				+ "  position.attr_id = 9 and	position.object_id = player.object_id and "
				+ "  status.attr_id = 11 and	status.object_id = player.object_id and "
				+ "  wallet.attr_id = 12 and	wallet.object_id = player.object_id";
		List<Player> players = this.jdbcTemplate.query(sql, new Object[] {key}, new RowMapper<Player>() {
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
				PlayerEntity entity = new PlayerEntity();
				entity.setName(rs.getString("name"));
				entity.setDoublesCount(Integer.parseInt(rs.getString("doublesCount")));
				entity.setJailStatus(Status.valueOf(rs.getString("jailStatus")));
				entity.setJailTerm(Integer.parseInt(rs.getString("jailTerm")));
				entity.setExtraTurn(Boolean.parseBoolean(rs.getString("extraTurn")));
				entity.setOfferADeal(Boolean.parseBoolean(rs.getString("offerADeal")));
				entity.setPayRent(Boolean.parseBoolean(rs.getString("payRent")));
				entity.setPosition(Integer.parseInt(rs.getString("position")));
				entity.setStatus(Status.valueOf(rs.getString("status")));
				entity.setWallet(Integer.parseInt(rs.getString("wallet")));
				entity.setPlayerColor(rs.getString("playerColor"));				
				
				return new Player(entity);
			}
		});
		return players;
	}

	public static void main(String[] args) {
		 ApplicationContext context =
				 new FileSystemXmlApplicationContext("test_files\\beans.xml");
		 PlayerDao dao = context.getBean(PlayerDao.class);
		 List<Player> players = dao.getAllByParentKey(2);
		 for (Player player : players) {
			 printPlayer(player);
		 }
			 
		 
	}
	
	private static void printPlayer(Player player) {
		System.out.println(player.getName());
		System.out.println(player.getDoublesCount());
		System.out.println(player.getJailTerm());
		System.out.println(player.getLastPosition());
		System.out.println(player.getMoney());
		System.out.println(player.getPlayerColor());
		System.out.println(player.getPosition());
		System.out.println(player.getRemainingTime());
		System.out.println(player.getJailStatus());
		System.out.println(player.getStatus());
	}
}
