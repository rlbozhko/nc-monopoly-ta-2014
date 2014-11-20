package com.monopoly.io;

import com.monopoly.action.Action;
import com.monopoly.action.ActionController;
import com.monopoly.action.EndTurnAction;
import com.monopoly.action.StartTurnAction;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.Session;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Roma on 20.11.2014.
 */
public class DummyIO implements IO, Runnable {
    Session session;
    Player player;
    ActionController actionController;
    List<Action> actions;

    public DummyIO(Session session, Player player) {
        this.session = session;
        this.player = player;
        this.actionController = session.getActionController();
    }

    @Override
    public void outputBoardState() {
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
    public void outputAvailableActions(Player player) {
        actions = actionController.getAvailableActions(player);
    }

    @Override
    public void performAction(Action action) {
        action.performAction(player);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int choice;

            for (int i = 0; i < actions.size(); i++) {
                Action action = actions.get(i);
                if (action instanceof StartTurnAction) {
                    performAction(action);
                    return;
                } else if (action instanceof EndTurnAction) {
                    performAction(action);
                    return;
                }
            }

        } while (true);
    }
}
