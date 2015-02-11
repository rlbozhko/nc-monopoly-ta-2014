package com.monopoly.services;

import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monopoly.bean.User;
import com.monopoly.database.dao.UserDbDao;

@Service
public class UserDbService {
	@Autowired
	private UserDbDao userDao;

	@Transactional
	public User getUser(String hash) {
		if (hash != null) {
			if (hash.length() != 36) {
				return null;
			}
			User user = userDao.getByHash(hash);
			return user;
		}

		return null;
	}

	@Transactional
	public void signOut(String hash) {
		User user = userDao.getByHash(hash);
		if (user != null) {
			userDao.updateHash(user, null);
		}
	}

	@Transactional
	public Cookie signIn(String email, String password) {
		User user = userDao.getByEmailPassword(email, password);

		if (user != null) {
			String uuid = UUID.randomUUID().toString();
			userDao.updateHash(user, uuid);
			Cookie cookie = new Cookie("bb_data", uuid);
			cookie.setMaxAge(60 * 60 * 24);
			return cookie;
		}

		return null;
	}
	
	@Transactional
	public boolean createUser(User user) {
		try {
			userDao.insert(user);
		} catch (SQLException e) {
			return false; //if already exists
		}
		return true; // if successfully created
	}
	
	@Transactional
	public void deleteUser(User user) {
		userDao.delete(user);
	}

}
