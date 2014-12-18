package com.monopoly.action;

import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;

/**
 * Created by Roma on 19.11.2014.
 */
public class StartTurnAction implements Action {
    Session session;

    public StartTurnAction() {
        this.session = GameSession.getInstance();
    }

    @Override
    public void performAction(Player player) {
        Dice dice = Dice.getInstance();
        dice.generateNewDiceValue();
        player.goToPosition(player.getPosition() + dice.getFaceDie1() + dice.getFaceDie2());
        ActionUtils.sendMessageToAll(player.getName() + " бросил кости: " + dice.getFaceDie1() + " " + dice.getFaceDie2());
        player.setStatus(Status.ACTIVE);
    }

    @Override
    public String getName() {
        return "Start Turn";
    }
}
