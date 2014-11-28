package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.DiceOperations;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.EventCell;
import com.monopoly.board.cells.Property;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.dice.ValueGeneratorForDice;
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
    private final int MAX_MOVE = 12;

    public StartTurnAction() {
        this.session = TestSession.getInstance();
        this.board = session.getBoard();
    }

    @Override
    public void performAction(Player player) {
        Dice dice = ((DiceOperations) board).getDice();
        player.goToPosition(player.getPosition() + dice.getNewFaceDie1() + dice.getNewFaceDie2());
        player.setStatus(Status.ACTIVE);
        List<Cell> cells = board.getCells();

        if (hasPassCircle(player)) {
            ((EventCell) cells.get(0)).getEvent().performEvent();
        }

        Cell currentCell = cells.get(player.getPosition());
        if (CellType.EVENT_CELL.equals(currentCell.getCellType())) {
            ((EventCell) currentCell).getEvent().performEvent();
        } else if (CellType.PROPERTY_CELL.equals(currentCell.getCellType())) {
            Property property = (Property) currentCell;
            if (null != property.getOwner() && !player.equals(property.getOwner())) {
                player.setMustPayRent(true);
            }
        }
    }

    private boolean hasPassCircle(Player player) {
        return (player.getLastPosition() > (board.getCells().size() - MAX_MOVE))
            && (player.getPosition() < MAX_MOVE)
                && (player.getPosition() != 0);
    }

    @Override
    public String getName() {
        return "Start Turn";
    }
}
