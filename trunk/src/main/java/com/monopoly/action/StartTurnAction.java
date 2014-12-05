package com.monopoly.action;

import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

import java.util.List;

/**
 * Created by Roma on 19.11.2014.
 */
public class StartTurnAction implements Action {
    Session session;

    public StartTurnAction() {
        this.session = TestSession.getInstance();
    }

    @Override
    public void performAction(Player player) {
        Dice dice = Dice.getInstance();
        dice.generateNewDiceValue();
        player.goToPosition(player.getPosition() + dice.getFaceDie1() + dice.getFaceDie2());
        //System.out.println(player.getName() + " бросил кости: " + dice.getFaceDie1() + " " + dice.getFaceDie2());
        for (IO io : TestSession.getInstance().getIO()) {
            io.showMessage(player.getName() + " бросил кости: " + dice.getFaceDie1() + " " + dice.getFaceDie2());
        }
        player.setStatus(Status.ACTIVE);
    }

    @Override
    public String getName() {
        return "Start Turn";
    }
}
