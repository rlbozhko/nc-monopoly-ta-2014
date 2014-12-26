package com.monopoly.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.monopoly.bean.User;

@Repository
public class UserDao {
	private List<User> usersList = TestUesrs.getUsersList();

	public User getUser(String hash) {
		// TODO вернуть юзера из базы по хэшу
		for (User each : usersList) {
			if (hash.equals(each.getHash())) {
				return each;
			}
		}

		return null;
	}

	public void signOut(String hash) {
		// TODO должен удалять значение hash у юзера из базы
		for (User each : usersList) {
			if (hash.equals(each.getHash())) {
				each.setHash(null);
				break;
			}
		}
	}

	public boolean isUserExists(String email, String password) {
		for (User each : usersList) {
			if (email.equals(each.getEmail()) && password.equals(each.getPassword())) {
				return true;
			}
		}
		return false;
	}

	public void saveHash(String uuid, String email, String password) {
		for (User each : usersList) {
			if (email.equals(each.getEmail()) && password.equals(each.getPassword())) {
				each.setHash(uuid);
				break;
			}
		}
	}
}
