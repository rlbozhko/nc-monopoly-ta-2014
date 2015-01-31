package com.monopoly.action.controller;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.action.ActionType;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;

public class JailActionController implements ActionController {
	@Override
	public List<ActionType> getAvailableActions(Player player) {
		List<ActionType> result = new ArrayList<>();
		if (player.isJailed() && player.getStatus() == Status.START_TURN) {
			result.add(ActionType.SERVE_JAIL_TERM);
			result.add(ActionType.PAY_BAIL);
			result.add(ActionType.ESCAPE);
		}

		return result;
	}
}
