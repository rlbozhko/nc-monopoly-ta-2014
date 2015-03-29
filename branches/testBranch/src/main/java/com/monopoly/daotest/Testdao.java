package com.monopoly.daotest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public class Testdao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ArrayList<String> getTest() {
		final ArrayList<String> testList = new ArrayList<>();
		String sql = "select obj_player.NAME name,obj_player.object_id id, \n"
				+ "ref_player.REFERENCE position, \n"
				+ "attr_player.value money \n"
				+ "from OBJECTS obj_player join OBJREFERENCE ref_player on (obj_player.object_id = ref_player.OBJECT_ID)\n"
				+ "and ref_player.ATTR_ID = 2 \n"
				+ "join ATTRIBUTES attr_player on (attr_player.OBJECT_ID = obj_player.OBJECT_ID)\n"
				+ "and attr_player.ATTR_ID = 1";

		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String test = rs.getString("name");
				testList.add(test);
			}
		});
		return testList;
	}
}
