package com.monopoly.board.events;

import java.util.List;
import java.util.Random;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.Board;
import com.monopoly.board.cells.Cell;
import com.monopoly.board.cells.Property;
import com.monopoly.game.session.GameSession;

/**
 * Created by Roma on 06.11.2014.
 */

/**
 * ЧПСобытие
 */
public class EmergencyEvent extends Event {
	private int cellCount;
	private Random random;

	public EmergencyEvent(String description) {		
		this.description = description;
		this.cellCount = 1;
		random = new Random(cellCount);
	}

	@Override
	public void performEvent() {
		Board board = GameSession.getInstance().getBoard();
		List<Cell> cells = board.getPropertyCell();
		for (int i = 0; i < cellCount; i++) {
			int randomIndex = random.nextInt(cells.size());
			Property property = (Property) cells.get(randomIndex);
			if (property.hasBuilding()) {
				property.getBuilding().levelDown();
				String message = null;
				if (property.hasBuilding()) {
					message = description + " и у здания на ячейке " + property.getName() + " понижен уровень!";
				} else {
					message = description + " и на ячейке " + property.getName() + " больше нет здания!";
				}
				ActionUtils.sendMessageToAll(message);
			}
		}
	}

	public synchronized int getCellCount() {
		return cellCount;
	}

	public synchronized void setCellCount(int cellCount) {
		this.cellCount = cellCount;
	}
}
