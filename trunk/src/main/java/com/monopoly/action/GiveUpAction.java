package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;
import com.monopoly.io.IO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class GiveUpAction implements Action {

    Session session;
    Board board;

    public GiveUpAction() {
        this.session = GameSession.getInstance();
        this.board = session.getBoard();
    }

    @Override
    public void performAction(Player player) {
        PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
        Player nextPlayer = board.getNextPlayer();
        if (Status.ACTIVE.equals(player.getStatus()) || Status.START_TURN.equals(player.getStatus())) {
            if (!player.equals(nextPlayer)) {
                nextPlayer.setStatus(Status.START_TURN);
            }
        }
        player.setStatus(Status.FINISH);

        for (IO io : session.getIO()) {
            io.showMessage(player.getName() + " сдался");
        }
        List<Property> properties = new LinkedList<>(propertyManager.getPlayerProperties(player));
        for (Property property : properties) {
            propertyManager.resetPropertyOwner(property);
        }
    }

    @Override
    public String getName() {
        return "Give up";
    }


}
