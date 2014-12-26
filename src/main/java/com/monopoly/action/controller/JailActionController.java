package com.monopoly.action.controller;

import com.monopoly.action.Action;
import com.monopoly.action.EscapeAction;
import com.monopoly.action.PayBailAction;
import com.monopoly.action.ServeJailTermAction;
import com.monopoly.action.controller.ActionController;
import com.monopoly.board.player.Player;

import java.util.ArrayList;
import java.util.List;

public class JailActionController implements ActionController {
    @Override
    public List<Action> getAvailableActions(Player player) {
        List<Action> result = new ArrayList<>();
        if (player.isJailed()){
            result.add(new ServeJailTermAction());
            result.add(new PayBailAction());
            result.add(new EscapeAction());
        }

        return result;
    }
}
