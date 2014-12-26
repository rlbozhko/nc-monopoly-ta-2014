package com.monopoly.io;

import com.monopoly.action.Action;
import com.monopoly.action.controller.ActionController;
import com.monopoly.action.deal.*;
import com.monopoly.board.Board;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * Created by Roma on 20.11.2014.
 */
public class ConsoleIO implements IO, Runnable {
    private Player player;
    private List<Action> actions;
    PropertyManager propertyManager;

    public ConsoleIO(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        propertyManager = GameSession.getInstance().getPropertyManager();
        printBoardToFile();
        do {
            outputBoardState();
            outputAvailableActions();
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
        Session session = GameSession.getInstance();
        System.out.println();
        System.out.println("Информация об игре");
        List<Player> players = session.getBoard().getPlayers();
        int position = player.getPosition();
        System.out.println("Вы на позиции: " + position + " " + session.getBoard().getCells().get(position).getName());
        System.out.println("У Вас на счету $" + player.getMoney() + ". У Вас в собственности: "
                + propertyManager.getPlayerProperties(player));
        for (Player other : players) {
            if (!player.equals(other)) {
                System.out.println(other.getName() + " -- на позиции: " + other.getPosition());
            }
        }
        System.out.println();
    }

    @Override
    public void outputAvailableActions() {
        ActionController actionController = GameSession.getInstance().getActionController();
        actions = actionController.getAvailableActions(player);
        System.out.println("Доступные действия:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + " " + actions.get(i).getName());
        }
        System.out.println();
    }

    @Override
    public void performAction(Action action) {
        action.performAction(player);
    }

    @Override
    public Player selectPlayer() {
        List<Player> players = GameSession.getInstance().getBoard().getPlayers();
        System.out.println("Выберите игрока:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (!Status.FINISH.equals(player.getStatus()) && !this.player.equals(player)) {
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
        List<Property> properties = propertyManager.getPlayerProperties(player);
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            System.out.println((i + 1) + " " + ((Cell) property).getName() + (property.isPledged() ? " Заложена" : ""));
        }

        System.out.println("0 - Для отмены");
        int input = positiveIntInput() - 1;
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
            System.out.println(i + " " + (building.getBuildingName()));
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
        Deal deal = null;
        DealContainer dealContainer = new DealContainer(player, otherPlayer);
        System.out.println("Установите условия сделки");
        boolean continueSelect = true;
        while (continueSelect) {
            System.out.println(
                    "1 Предложить денег " + dealContainer.getGiveMoney() + "\n" +
                            "2 Требовать денег " + dealContainer.getAskMoney() + "\n" +
                            "3 Предложить собственность " + dealContainer.getGiveProperties() + "\n" +
                            "4 Требовать собственность " + dealContainer.getAskProperties() + "\n" +
                            "7 Отправить предложение\n" +
                            "0 Отменить сделку\n");
            int choice = positiveIntInput();
            int input;
            switch (choice) {
                case 1:
                    System.out.println("Введите количество денег:");
                    input = positiveIntInput();
                    if (input >= 0 && input <= player.getMoney()) {
                        dealContainer.setGiveMoney(input > 0 ? input : 0);
                    } else {
                        System.out.println("Число должно быть в диапозоне от 0 до " + player.getMoney());
                    }
                    break;
                case 2:
                    System.out.println("Введите количество денег:");
                    input = positiveIntInput();
                    dealContainer.setAskMoney(input > 0 ? input : 0);
                    if (input < 0) {
                        System.out.println("Введите положительное число");
                    }
                    break;
                case 3:
                    dealContainer.addGiveProperty(selectProperty(player));
                    break;
                case 4:
                    dealContainer.addAskProperty(selectProperty(otherPlayer));
                    break;
                case 7:
                    continueSelect = false;
                    deal = dealContainer.createDeal();
                    break;
                default:
                    continueSelect = false;
                    showMessage("Сделка отменена");
                    break;
            }
        }
        return deal;
    }

    @Override
    public boolean yesNoDialog(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("Выберите ответ\n" +
                "1 - Да\n" +
                "0 - Нет");
        int choice = positiveIntInput();
        return choice == 1;
    }

    @Override
    public void showMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    private int positiveIntInput() {
        Scanner in = new Scanner(System.in);
        int input = -1;
        if (in.hasNextInt()) {
            input = in.nextInt();
        }
        return input;
    }

    private void printBoardToFile() {
        Board board = GameSession.getInstance().getBoard();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("board.txt"))) {
            for (Cell cell : board.getCells()) {
                StringBuilder cellLine = new StringBuilder();
                cellLine.append(cell.getPosition()).append(". ").append(cell.getName()).append(". Описание: ").append(cell.getDescription());
                if (CellType.PROPERTY_CELL.equals(cell.getCellType())) {
                    Property property = (Property) cell;
                    cellLine.append(". Монополия: ").append(property.getMonopoly().getMonopolyType())
                            .append(". Базовая стоимость ").append(property.getPrice())
                            .append(". Базовая аренда ").append(property.getRent())
                            .append(". Максимальное количество зданий ").append(property.getMaxBuildings())
                            .append(". Максимальный уровень зданий ").append(property.getMaxLevel());
                }
                cellLine.append("\n");
                writer.write(cellLine.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
