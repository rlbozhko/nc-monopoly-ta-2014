package com.monopoly.action;

import com.monopoly.board.player.Player;

import java.util.List;

/**
 * Created by Roma on 19.11.2014.
 */
public interface ActionController {
    List<Action> getAvailableActions(Player player);
}