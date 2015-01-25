package com.monopoly.action.controller;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.action.Action;
import com.monopoly.action.jail.EscapeAction;
import com.monopoly.action.jail.PayBailAction;
import com.monopoly.action.jail.ServeJailTermAction;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;

public class JailActionController implements ActionController {
	@Override
	public List<Action> getAvailableActions(Player player) {
		List<Action> result = new ArrayList<>();
		if (player.isJailed() && player.getStatus() == Status.START_TURN) {
			result.add(new ServeJailTermAction());
			result.add(new PayBailAction());
			result.add(new EscapeAction());
		}

		return result;
	}
}
