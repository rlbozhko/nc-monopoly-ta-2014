package com.monopoly.io;

import com.monopoly.action.*;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.TestSession;

import java.util.List;

/**
 * Created by Roma on 20.11.2014.
 */
public class DummyIO implements IO, Runnable {

    private Player player;
    private List<Action> actions;
    private boolean answer = true;

    public DummyIO(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outputAvailableActions();
            for (int i = 0; i < actions.size(); i++) {
                Action action = actions.get(i);
                if (player.getWallet().getMoney() == 0) {
                    performAction(new GiveUpAction());
                    break;
                }
                if (action instanceof PayRentAction) {
                    performPayRent(action);
                    break;
                } else if (action instanceof StartTurnAction || action instanceof EndTurnAction) {
                    performAction(action);
                    break;
                }
            }
        } while (!Status.FINISH.equals(player.getStatus()));
    }

    private void performPayRent(Action action) {
        Property property = (Property) TestSession.getInstance().getBoard().getCells().get(player.getPosition());
        if (player.getWallet().getMoney() >= property.getRent()) {
            performAction(action);
        } else {
            performAction(new GiveUpAction());
        }
    }

    @Override
    public void outputBoardState() {
        Session session = TestSession.getInstance();
        System.out.println("Информация об игре");
        List<Player> players = session.getBoard().getPlayers();
        System.out.println("Вы на позиции: " + player.getPosition());
        int i = 0;
        for (Player other : players) {
            if (!player.equals(other)) {
                System.out.println("Другой игрок " + i + "на позиции: " + other.getPosition());
            }
            i++;
        }
    }

    @Override
    public void outputAvailableActions() {
        ActionController actionController = TestSession.getInstance().getActionController();
        actions = actionController.getAvailableActions(player);
    }

    @Override
    public void performAction(Action action) {
        action.performAction(player);
    }

    @Override
    public Player getUser() {
        return player;
    }

    @Override
    public Player selectPlayer() {
        return null;
    }

    @Override
    public Property selectProperty(Player player) {
        return null;
    }

    @Override
    public Building selectBuilding(Property property) {
        return null;
    }

    @Override
    public Deal dealDialog(Player otherPlayer) {
        return null;
    }

    @Override
    public boolean yesNoDialog(String message) {
        if (answer) {
            answer = false;
            return true;
        } else {
            answer = true;
            return false;
        }
    }

    @Override
    public void showMessage(String message) {
        //System.out.println(player.getName() + ": " + message);
    }
}
