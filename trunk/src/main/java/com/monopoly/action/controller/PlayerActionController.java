package com.monopoly.action.controller;

import com.monopoly.action.*;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class PlayerActionController implements ActionController {
    private Session session;
    private PropertyManager propertyManager;

    @Override
    public List<Action> getAvailableActions(Player player) {
        session = GameSession.getInstance();
        propertyManager = session.getPropertyManager();

        List<Action> result = new ArrayList<>();
        if (Status.FINISH == player.getStatus()) {
            return result;
        }

        result.add(new DealAction());
        result.add(new GiveUpAction());
        result.add(new WaitAction());
        result.add(new PledgePropertyAction());
        if (player.hasPledgedProperty()) {
            result.add(new PayBackAction());
        }

        if (player.isJailed()) {
            result.addAll(getJailActions(player));
            return result;
        }

        if (player.isPayRent()) {
            result.add(new PayRentAction());
        }

        Cell cell = session.getBoard().getCells().get(player.getPosition());
        if (CellType.PROPERTY_CELL == cell.getCellType()) {
            PropertyCell propertyCell = (PropertyCell) cell;
            if (propertyManager.getPropertyOwner(propertyCell) == null) {
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

    List<Action> getJailActions(Player player) {
        return new JailActionController().getAvailableActions(player);
    }
}
