package com.monopoly.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monopoly.daotest.Testdao;

@Service
public class TestService {
	@Autowired
	private Testdao testDao;
	
	@Transactional
	public ArrayList<String> getTest() {
		ArrayList<String> test = testDao.getTest();
		return test;
	}
}
