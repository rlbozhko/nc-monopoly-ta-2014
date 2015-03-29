package com.monopoly.dao;

import java.util.ArrayList;
import java.util.List;
import com.monopoly.bean.User;


public class TestUsers {

	// For Test
	private static User userPetya = new User("petya@test.ua","123","", "Petrucho");
	private static User userVasya = new User("vasya@test.ua","123","", "Vasya_32");
	private static User userAnna = new User("anna@test.ua","123","", "Anka iz tanka");

	private static List<User> usersList = new ArrayList<>();

	public static List<User> getUsersList() {
		usersList.add(userPetya);
		usersList.add(userVasya);
		usersList.add(userAnna);
		return usersList;
		
	}
	
}
