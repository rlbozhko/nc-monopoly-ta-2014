package com.monopoly.services;

import java.util.UUID;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monopoly.bean.User;
import com.monopoly.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Transactional
	public User getUser(String hash) {
		if (hash != null) {
			if (hash.length() != 36) {
				return null;
			}
			User user = userDao.getUser(hash);
			return user;
		}

		return null;
	}

	@Transactional
	public void signOut(String hash) {
		userDao.signOut(hash);
	}
	
	@Transactional
	public Cookie signIn(String email, String password) {
		boolean isUserExists = userDao.isUserExists(email, password);

        if (isUserExists) {
            String uuid = UUID.randomUUID().toString();
            userDao.saveHash(uuid, email, password);
            Cookie cookie = new Cookie("bb_data", uuid);
            cookie.setMaxAge(60 * 60 * 24);
            return cookie;
        }

        return null;
	}

}
