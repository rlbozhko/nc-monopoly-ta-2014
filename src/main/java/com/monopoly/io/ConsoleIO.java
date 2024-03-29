package com.monopoly.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.monopoly.action.ActionType;
import com.monopoly.action.ActionUtils;
import com.monopoly.action.controller.ActionController;
import com.monopoly.action.deal.Deal;
import com.monopoly.action.deal.DealContainer;
import com.monopoly.board.Board;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.CellType;
import com.monopoly.board.cells.Property;
import com.monopoly.board.dice.Dice;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;
import com.monopoly.io.WebIO.SelectPropertyHelper;
import com.monopoly.io.WebIO.YesNoDialog;

/**
 * Created by Roma on 20.11.2014.
 */
public class ConsoleIO implements IO, Runnable {
	private Player player;
	private List<ActionType> actions;
	private PropertyManager propertyManager;

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
		} while (Status.FINISH != player.getStatus());
	}

	@Override
	public Player getOwner() {
		return player;
	}
	
	public void outputBoardState() {
		Session session = GameSession.getInstance();
		System.out.println();
		System.out.println("Информация об игре");
		List<Player> players = session.getBoard().getPlayers();
		int position = player.getPosition();
		System.out.println("Вы на позиции: " + position + " " + session.getBoard().getCells().get(position).getName());
		System.out.println("У Вас на счету $" + player.getMoney() + ". У Вас в собственности: "
				+ propertyManager.getPlayerProperties(player));
		if (player.isTimerStarted()) {
			System.out.println("ОСТАЛОСЬ ВРЕМЕНИ НА ХОД: " + Math.round(player.getRemainingTime() / 100.) / 10.);
		}
		for (Player other : players) {
			if (!player.equals(other)) {
				System.out.println(other.getName() + " -- на позиции: " + other.getPosition());
			}
		}
		System.out.println();
	}

	public void outputAvailableActions() {
		ActionController actionController = GameSession.getInstance().getActionController();
		actions = actionController.getAvailableActions(player);
		System.out.println("Доступные действия:");
		for (int i = 0; i < actions.size(); i++) {
			System.out.println(i + " " + actions.get(i));
		}
		System.out.println();
	}
	
	@Override
	public void performAction(ActionType actionType) {
		actionType.create().performAction(player);
	}

	@Override
	public Player selectPlayer() {
		List<Player> players = GameSession.getInstance().getBoard().getPlayers();
		System.out.println("Выберите игрока:");
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (Status.FINISH != player.getStatus() && !this.player.equals(player)) {
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
			System.out.print((i + 1) + " " + ((Cell) property).getName() + (property.isPledged() ? " Заложена" : ""));
			Building building = property.getBuilding();
			if (building != null) {
				System.out.print(" "
						+ (" " + building.getBuildingName() + " ур." + building.getCurrentLevel()));
			}
			System.out.println("");
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
	public Deal dealDialog(Player otherPlayer) {
		Deal deal = null;
		DealContainer dealContainer = new DealContainer(player, otherPlayer);
		System.out.println("Установите условия сделки");
		boolean continueSelect = true;
		while (continueSelect) {
			System.out.println("1 Предложить денег " + dealContainer.getGiveMoney() + "\n" + "2 Требовать денег "
					+ dealContainer.getAskMoney() + "\n" + "3 Предложить собственность "
					+ dealContainer.getGiveProperties() + "\n" + "4 Требовать собственность "
					+ dealContainer.getAskProperties() + "\n" + "7 Отправить предложение\n" + "0 Отменить сделку\n");
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
		System.out.println("Выберите ответ\n" + "1 - Да\n" + "0 - Нет");
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
				cellLine.append(cell.getPosition()).append(". ").append(cell.getName()).append(". Описание: ")
						.append(cell.getDescription());
				if (CellType.PROPERTY_CELL == cell.getCellType()) {
					Property property = (Property) cell;
					cellLine.append(". Монополия: ").append(property.getMonopoly().getMonopolyType())
							.append(". Базовая стоимость ").append(property.getPrice()).append(". Базовая аренда ")
							.append(property.getRent()).append(". Максимальный уровень зданий ")
							.append(property.getMaxLevel());
				}
				cellLine.append("\n");
				writer.write(cellLine.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showDice() {
		Dice dice = Dice.getInstance();
		ActionUtils.sendMessageToAll(player.getName() + " бросил кости: " + dice.getFaceDie1() + " "
				+ dice.getFaceDie2());
	}
	
	@Override
	public boolean hasSelectPlayerRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSelectedPlayer(Player selectedPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SelectPropertyHelper getSelectPropertyHelper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatedDeal(Deal deal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasCreateDealRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasYesNoDialog() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public YesNoDialog getYesNoDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Queue<String> getAllMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showWarning(String message) {
		System.out.println();
		System.out.println("Warning: " + message);
		
	}

	@Override
	public String getWarning() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasWarning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSelectPropertyRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player getDealTarget() {
		// TODO Auto-generated method stub
		return null;
	}
}
