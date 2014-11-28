package com.monopoly.action;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class GiveUpAction implements Action {

    Session session;
    Board board;

    public GiveUpAction() {
        this.session = TestSession.getInstance();
        this.board = session.getBoard();
    }

    @Override
    public void performAction(Player player) {
        Player nextPlayer = board.getNextPlayer();
        if (Status.ACTIVE.equals(player.getStatus()) || Status.START_TURN.equals(player.getStatus())) {
            if (!player.equals(nextPlayer)) {
                nextPlayer.setStatus(Status.START_TURN);
            }
        }
        for (IO io : session.getIO()) {
            io.showMessage(player.getName() + " сдался");
        }
        //Место для аукциона
        List<Property> property = player.getProperty();
        while (property.size() != 0) {
            property.get(0).setAndAddToOwner(null);
        }
        //
        player.setStatus(Status.FINISH);
    }

    @Override
    public String getName() {
        return "Give up";
    }


}
