package com.monopoly.action;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.board.Board;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;

/**
 * Created by Roma on 20.11.2014.
 */
public class FinishGameAction implements Action {
	private final static Object statusLock = new Object();
	public final static ActionType type = ActionType.FINISH_GAME;

	private Session session;
	private Board board;

	public FinishGameAction() {
		this.session = GameSession.getInstance();
		this.board = session.getBoard();
	}

	@Override
	public void performAction(Player player) {
		PropertyManager propertyManager = GameSession.getInstance().getPropertyManager();
		synchronized (statusLock) {
			if (Status.ACTIVE == player.getStatus() || Status.START_TURN == player.getStatus()) {
				player.setStatus(Status.FINISH);
				board.getNextPlayer().setStatus(Status.START_TURN);
			} else {
				player.setStatus(Status.FINISH);
			}

			List<Property> properties = new ArrayList<>(propertyManager.getPlayerProperties(player));
			for (Property property : properties) {
				propertyManager.resetPropertyOwner(property);
			}
			
			ActionUtils.sendMessageToAll(player.getName() + " сдался");
			List<Player> activePlayers = board.getActivePlayers();
			if (activePlayers.size() == 1) {
				Player lastPlayer = activePlayers.get(0);
				lastPlayer.setStatus(Status.FINISH);
				lastPlayer.resetTimer();
				ActionUtils.getPlayerIO(lastPlayer).showMessage("Поздравляю!!! Вы стали абсолютным монополистом!!!");
				ActionUtils.sendMessageToAll("Игрок " + lastPlayer.getName() + " победил!!!");
			}
		}
	}

	@Override
	public String getName() {
		return "Finish Game";
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return type.equals(obj);
	}

	@Override
	public ActionType getType() {
		return type;
	}
}
