package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.TestSession;

import java.util.List;

/**
 * Created by Roma on 19.11.2014.
 */
public class StartTurnAction implements Action {
    Session session;
    Board board;

    public StartTurnAction() {
        this.session = TestSession.getInstance();
        this.board = session.getBoard();
    }

    @Override
    public void performAction(Player player) {
        Dice dice = board.getDice();
        dice.generateNewDiceValue();
        player.goToPosition(player.getPosition() + dice.getFaceDie1() + dice.getFaceDie2());
        //System.out.println(dice.getFaceDie1() + " " + dice.getFaceDie2());
        player.setStatus(Status.ACTIVE);
    }

    @Override
    public String getName() {
        return "Start Turn";
    }
}
