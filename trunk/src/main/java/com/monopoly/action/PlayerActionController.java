package com.monopoly.action;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.TestSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class PlayerActionController implements ActionController {
    Session session;

    public PlayerActionController() {

    }

    @Override
    public List<Action> getAvailableActions(Player player) {
        session = TestSession.getInstance();
        List<Action> result = new ArrayList<>();
        if (Status.FINISH.equals(player.getStatus())) {
            //result.clear();
            return result;
        }
        result.add(new DealAction());
        result.add(new GiveUpAction());
        result.add(new WaitAction());
        if (Status.WAIT.equals(player.getStatus())) {
            //
        } else if (Status.START_TURN.equals(player.getStatus())) {
            result.add(new StartTurnAction());
        } else if (Status.ACTIVE.equals(player.getStatus())) {
            result.add(new EndTurnAction());
        }

        return result;
    }
}
