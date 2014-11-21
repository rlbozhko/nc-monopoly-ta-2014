package com.monopoly.io;

import com.monopoly.action.Action;
import com.monopoly.action.ActionController;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.Session;
import com.monopoly.game.session.TestSession;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Roma on 20.11.2014.
 */
public class ConsoleIO implements IO, Runnable {
    //Session session;
    Player player;
    //ActionController actionController;
    List<Action> actions;

    public ConsoleIO(Player player) {
        //this.session = TestSession.getInstance();
        this.player = player;
        //this.actionController = session.getActionController();
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
                System.out.println("Другой игрок " + i + " -- на позиции: " + other.getPosition());
            }
            i++;
        }
    }

    @Override
    public void outputAvailableActions(Player player) {
        ActionController actionController = TestSession.getInstance().getActionController();
        actions = actionController.getAvailableActions(player);
        System.out.println("Доступные действия:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + " " + actions.get(i).getName());
        }
    }

    @Override
    public void performAction(Action action) {
        action.performAction(player);
        //outputBoardState();
        //outputAvailableActions(player);
    }

    @Override
    public void run() {
        do {
            Scanner in = new Scanner(System.in);
            outputBoardState();
            outputAvailableActions(player);
            System.out.print("Выберите действие: ");
            if (in.hasNextInt()) {
                performAction(actions.get(in.nextInt()));
            }

        } while (!Status.FINISH.equals(player.getStatus()));
    }
}
