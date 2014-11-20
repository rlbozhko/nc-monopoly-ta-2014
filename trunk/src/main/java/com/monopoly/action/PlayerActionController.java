package com.monopoly.action;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class PlayerActionController implements ActionController {
    Session session;
    public PlayerActionController(Session session) {
        this.session = session;
    }

    @Override
    public List<Action> getAvailableActions(Player player) {
        List<Action> result = new ArrayList<>();
        if (Status.FINISH.equals(player.getStatus())) {
            //result.clear();
            return result;
        }
        result.add(new SurrenderAction(session));
        if (Status.WAIT.equals(player.getStatus())) {
            //
        } else if (Status.START_TURN.equals(player.getStatus())) {
            result.add(new StartTurnAction(session));
        } else if (Status.ACTIVE.equals(player.getStatus())) {
            result.add(new EndTurnAction(session));
        }

        return result;
    }
}
