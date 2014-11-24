package com.monopoly.io;

import com.monopoly.action.Action;
import com.monopoly.action.ActionController;
import com.monopoly.action.Deal;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;
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
    public void run() {
        do {
            outputBoardState();
            outputAvailableActions(player);
            System.out.print("Выберите действие: ");
            int input = positiveIntInput();
            if (input < actions.size() && input >= 0) {
                performAction(actions.get(input));
            } else {
                System.out.println("Неверный Ввод!!!");
            }

        } while (!Status.FINISH.equals(player.getStatus()));
    }

    @Override
    public Player getUser() {
        return player;
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
    }

    @Override
    public Player selectPlayer() {
        List<Player> players = TestSession.getInstance().getBoard().getPlayers();
        System.out.println("Выберите игрока:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (!Status.FINISH.equals(player.getStatus())) {
                System.out.println(i + " " + player.getName());
            }
        }

        int input = positiveIntInput();
        if (input < players.size() && input >= 0) {
            return players.get(input);
        } else {
            return null;
        }
    }

    @Override
    public Property selectProperty(Player player) {
        System.out.println("Выберите Собственность игрока " + player.getName());
        List<Property> properties = player.getProperty();
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            System.out.println(i + " " + ((Cell) property).getName());
        }

        int input = positiveIntInput();
        if (input < properties.size() && input >= 0) {
            return properties.get(input);
        } else {
            return null;
        }
    }

    @Override
    public Building selectBuilding(Property property) {
        System.out.println("Выберите Здание");
        List<Building> buildings = property.getBuildings();
        for (int i = 0; i < buildings.size(); i++) {
            Building building = buildings.get(i);
            System.out.println(i + " " + (building.getName()));
        }

        int input = positiveIntInput();
        if (input < buildings.size() && input >= 0) {
            return buildings.get(input);
        } else {
            return null;
        }
    }

    @Override
    public Deal dealDialog(Player otherPlayer) {
        Deal deal = new Deal(player);
        System.out.println("Установите условия сделки");
        boolean continueSelect = true;
        while (continueSelect) {
        System.out.println(
                "1 Отдать денег " + deal.getGiveMoney() + "\n" +
                "2 Требовать денег " + deal.getAskMoney() + "\n" +
                "3 Отдать собственность " + deal.getGiveProperty() + "\n" +
                "4 Требовать собственность " + deal.getAskProperty() + "\n" +
                "7 Отправить сделку\n" +
                "0 Отменить сделку\n");
            int choice = positiveIntInput();
            int input;
            switch (choice) {
                case 1:
                    input = positiveIntInput();
                    deal.setGiveMoney( input < 0 ? input : 0);
                    break;
                case 2:
                    input = positiveIntInput();
                    deal.setAskMoney(input < 0 ? input : 0);
                    break;
                case 3:
                    deal.setGiveProperty(selectProperty(player));
                    break;
                case 4:
                    deal.setAskProperty(selectProperty(otherPlayer));
                    break;
                case 7:
                    continueSelect = false;
                    break;
                default:
                    deal = null;
                    continueSelect = false;
                    break;
            }
        }
        return deal;
    }

    @Override
    public boolean yesNoDialog(String message) {
        System.out.println(message);
        System.out.println("Выберите ответ\n" +
                "1 - Да\n" +
                "0 - Нет");
        int choice = positiveIntInput();
        if (choice == 1) {
            return true;
        }
        return false;
    }

    private int positiveIntInput() {
        Scanner in = new Scanner(System.in);
        int input = -1;
        if (in.hasNextInt()) {
            input = in.nextInt();
        }
        return input;
    }
}
