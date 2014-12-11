package com.monopoly.action;

import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.PropertyCell;
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

    @Override
    public List<Action> getAvailableActions(Player player) {
        session = TestSession.getInstance();
        List<Action> result = new ArrayList<>();
        if (Status.FINISH.equals(player.getStatus())) {
            return result;
        }
        if (player.isPayRent()) {
            result.add(new PayRentAction());
        }
        result.add(new DealAction());
        result.add(new PledgePropertyAction());
        result.add(new GiveUpAction());
        result.add(new WaitAction());

        Cell cell = session.getBoard().getCells().get(player.getPosition());
        if (CellType.PROPERTY_CELL.equals(cell.getCellType())) {
            PropertyCell propertyCell = (PropertyCell) cell;
            if (propertyCell.getOwner() == null) {
                result.add(new BuyPropertyAction());
            }
        }

        if (Status.START_TURN.equals(player.getStatus())) {
            result.add(new StartTurnAction());
        } else if (Status.ACTIVE.equals(player.getStatus())) {
            result.add(new EndTurnAction());
        }

        return result;
    }
}
