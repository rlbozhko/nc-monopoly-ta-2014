package com.monopoly.io;

import com.monopoly.action.Action;
import com.monopoly.action.deal.Deal;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;

public class WebIO implements IO {
	private Player player;
	
	public WebIO(Player player) {
		this.player = player;
	}
	
	@Override
	public void outputBoardState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void outputAvailableActions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void performAction(Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	public Player getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player selectPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property selectProperty(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deal dealDialog(Player otherPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean yesNoDialog(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showDice() {
		// TODO Auto-generated method stub

	}

}
