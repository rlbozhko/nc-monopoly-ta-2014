package com.monopoly.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.monopoly.bean.User;

@Repository
public class UserDbDao implements Dao<User> {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User getByKey(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User insert(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void merge(User object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User object) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAllByParentKey(int key) {
		
		return null;
	}
	
	public Integer getId(User user) {
		String sql = 
				"select users.object_id id, users.name name, u_email.value email, " +
				"  u_password.value password, users.description hash " +
				"  from objects users, attributes u_email,attributes u_password " +  
				"  where users.object_type_id = 7 and " +
				"    u_email.attr_id = 35 and " +
				"    u_password.attr_id = 38 and " +
				"    u_email.object_id = users.object_id and " +
				"    u_password.object_id = users.object_id and " +
				"    u_email.value = ? and " +
				"    u_password.value = ?";
		Integer id;
		try {
			id = this.jdbcTemplate.queryForObject(sql, new Object[]{user.getEmail(), user.getPassword()},
					new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return Integer.parseInt(rs.getString("id"));					
				}
			});
		} catch (DataAccessException e) {
			return null;
		}
		return id;
	}
	
	public void updateHash(User user, String hash) {		
		this.jdbcTemplate.update(
		        "update objects set description = ? where object_id = ?",
		        hash, getId(user));
		user.setHash(hash);
	}
	
	public User getByEmailPassword(String email, String password) {
		String sql = 
				"select users.name name, u_email.value email, " +
				"  u_password.value password, users.description hash " +
				"  from objects users, attributes u_email,attributes u_password " +  
				"  where users.object_type_id = 7 and " +
				"    u_email.attr_id = 35 and " +
				"    u_password.attr_id = 38 and " +
				"    u_email.object_id = users.object_id and " +
				"    u_password.object_id = users.object_id and " +
				"    u_email.value = ? and " +
				"    u_password.value = ?";
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(sql, new Object[]{email, password},
					new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getString("email"), rs.getString("password"),
							rs.getString("hash"), rs.getString("name"));					
				}
			});
		} catch (DataAccessException e) {
			return null;
		}
		return user;
	}
	
	public User getByHash(String hash) {
		String sql = 
				"select users.name name, u_email.value email, " +
				"  u_password.value password, users.description hash " +
				"  from objects users, attributes u_email,attributes u_password " +  
				"  where users.object_type_id = 7 and " +
				"    u_email.attr_id = 35 and " +
				"    u_password.attr_id = 38 and " +
				"    u_email.object_id = users.object_id and " +
				"    u_password.object_id = users.object_id and " +
				"    users.description = ?";
		User user;
		try {
			user = this.jdbcTemplate.queryForObject(sql, new Object[]{hash},
					new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new User(rs.getString("email"), rs.getString("password"),
							rs.getString("hash"), rs.getString("name"));					
				}
			});
		} catch (DataAccessException e) {
			return null;
		}
		return user;
	}
	

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("test_files\\beans.xml");
		UserDbDao dao = context.getBean(UserDbDao.class);
		User user = dao.getByEmailPassword("petya@test.ua", "123");
		printUser(user);
		
		Integer id = dao.getId(user);
		System.out.println(id);
		User newUser = new User("","","","");
		id = dao.getId(newUser);
		System.out.println(id);
		printUser(newUser);
		
		String hash = "648421354";
		dao.updateHash(user, hash);
		User u2 = dao.getByEmailPassword("petya@test.ua", "123");		
		printUser(u2);
		
		dao.updateHash(newUser, "123");
	
		User hashUser = dao.getByHash(hash);
		printUser(hashUser);
		
	}
	
	private static void printUser(User user) {
		System.out.println("Name = " + user.getNickName());
		System.out.println("Email = " + user.getEmail());
		System.out.println("Password = " + user.getPassword());
		System.out.println("Hash = " + user.getHash());
	}
}

