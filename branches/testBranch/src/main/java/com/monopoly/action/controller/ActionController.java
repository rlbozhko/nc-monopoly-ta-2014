package com.monopoly.action.controller;

import java.util.List;

import com.monopoly.action.ActionType;
import com.monopoly.board.player.Player;

/**
 * Created by Roma on 19.11.2014.
 */
public interface ActionController {
    List<ActionType> getAvailableActions(Player player);
}
