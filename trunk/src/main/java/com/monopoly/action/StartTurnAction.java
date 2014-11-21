package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.DiceOperations;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.dice.DiceGenerator;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;

import java.util.List;

/**
 * Created by Roma on 19.11.2014.
 */
public class StartTurnAction implements Action {
    Session session;
    Board board;

    public StartTurnAction(Session session) {
        this.session = session;
        this.board = session.getBoard();
    }

    @Override
    public void performAction(Player player) {
        List<Dice> dice = ((DiceOperations)board).getDice();
        Thread diceGenerator = new Thread(new DiceGenerator(dice.get(0), dice.get(1)));
        player.goToPosition(player.getPosition() + dice.get(0).getFace() + dice.get(1).getFace());
        player.setStatus(Status.ACTIVE);
        diceGenerator.interrupt();
    }
}
