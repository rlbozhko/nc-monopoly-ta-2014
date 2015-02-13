package com.monopoly.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monopoly.bean.User;
import com.monopoly.entity.SessionEntity;
import com.monopoly.game.session.GameSession.GameSessionBuilder;
import com.monopoly.game.session.Session;

@Repository
public class SessionDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Session insert(User session) throws SQLException {
		synchronized (SessionDao.class) {
			// if (getByEmail(user.getEmail()) != null ||
			// getByName(user.getNickName()) != null) {
			// throw new SQLException("User " + user.getEmail() +
			// " has already exists");
			// }
			// Long id = System.currentTimeMillis();
			// this.jdbcTemplate.update("INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) "
			// + "VALUES (? ,NULL,7, ? ,?)", id, user.getNickName(),
			// user.getHash());
			// this.jdbcTemplate.update("INSERT INTO attributes (attr_id, object_id, value, date_value) "
			// + "VALUES (35, ?, ?, NULL)", id, user.getEmail());
			// this.jdbcTemplate.update("INSERT INTO attributes (attr_id, object_id, value, date_value) "
			// + "VALUES (38, ?, ?, NULL)", id, user.getPassword());
			return null;
		}
	}

	public void delete(Session session) {
		this.jdbcTemplate.update("delete from objects where objects.description = ?", session.getId());
	}

	public Long getId(Session session) {
		String sql = "select ses.object_id, ses.name, ses.description id from objects ses "
				+ "where ses.object_type_id = 1 and ses.description = ?";
		Long id;
		try {
			id = this.jdbcTemplate.queryForObject(sql, new Object[] { session.getId() }, new RowMapper<Long>() {
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return Long.parseLong(rs.getString("object_id"));
				}
			});
		} catch (DataAccessException e) {
			return null;
		}
		return id;
	}

	public void update(Session session) {
//		this.jdbcTemplate.update("update objects set description = ? where object_id = ?", hash, getId(user));
//		user.setHash(hash);
	}
	
	public SessionEntity getById(long id) {
		String sql = "select ses.object_id, ses.name, ses.description id from objects ses "
				+ "where ses.object_type_id = 1 and ses.object_id = ?";
		SessionEntity sessionEntity;
		try {
			sessionEntity = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<SessionEntity>() {
				public SessionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
					SessionEntity entity = new SessionEntity();
					entity.setId(rs.getLong("id"));
					
					return entity;
				}
			});
		} catch (DataAccessException e) {
			return null;
		}
		return sessionEntity;
	}

	public User getByName(String name) {
		String sql = "select users.name name, u_email.value email, "
				+ "  u_password.value password, users.description hash "
				+ "  from objects users, attributes u_email,attributes u_password "
				+ "  where users.object_type_id = 7 and " + "    u_email.attr_id = 35 and "
				+ "    u_password.attr_id = 38 and " + "    u_email.object_id = users.object_id and "
				+ "    u_password.object_id = users.object_id and " + "    users.name = ?";
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(sql, new Object[] { name }, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getString("email"), rs.getString("password"), rs.getString("hash"), rs
							.getString("name"));
				}
			});
		} catch (DataAccessException e) {
			return null;
		}
		return user;
	}

	public User getByHash(String hash) {
		String sql = "select users.name name, u_email.value email, "
				+ "  u_password.value password, users.description hash "
				+ "  from objects users, attributes u_email,attributes u_password "
				+ "  where users.object_type_id = 7 and " + "    u_email.attr_id = 35 and "
				+ "    u_password.attr_id = 38 and " + "    u_email.object_id = users.object_id and "
				+ "    u_password.object_id = users.object_id and " + "    users.description = ?";
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(sql, new Object[] { hash }, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getString("email"), rs.getString("password"), rs.getString("hash"), rs
							.getString("name"));
				}
			});
		} catch (DataAccessException e) {
			return null;
		}
		return user;
	}

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("test_files\\beans.xml");
		SessionDao dao = context.getBean(SessionDao.class);
//		User user = dao.getByEmailPassword("petya@test.ua", "123");
//		printUser(user);
//
		// Long id = dao.getId(user);
		// System.out.println(id);
		// User newUser = new User("", "", "", "");
		// id = dao.getId(newUser);
		// System.out.println(id);
		// printUser(newUser);
		//
		// String hash = "648421354";
		// dao.updateHash(user, hash);
		// User u2 = dao.getByEmailPassword("petya@test.ua", "123");
		// printUser(u2);
		//
		// dao.updateHash(newUser, "123");
		//
		// User hashUser = dao.getByHash(hash);
		// printUser(hashUser);
		//
		// User insertTest = new User("insert@mail.com", "123", "","insert");
		//
		// dao.delete(insertTest);
		// try {
		// dao.insert(insertTest);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// printUser(dao.getByEmail(insertTest.getEmail()));
		//
		// try {
		// dao.insert(insertTest);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	private static void printUser(User user) {
		System.out.println("Name = " + user.getNickName());
		System.out.println("Email = " + user.getEmail());
		System.out.println("Password = " + user.getPassword());
		System.out.println("Hash = " + user.getHash());
	}
}
