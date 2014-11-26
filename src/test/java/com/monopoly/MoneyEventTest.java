package com.monopoly;

import org.junit.Before;
import org.junit.Test;

import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Wallet;

//Create By Kulikovsky Anton


public class MoneyEventTest {
	private Wallet wallet = new Wallet();
	private Player player = new Player("Karabas", wallet);
	
	
	@Before
	public void before() {
		player.getWallet().addMoney(500);
	}
	
	@Test
	public void moneEvent() {
		
	}
}
