package com.monopoly.board.events;
/**
 * Create By Kulikovsky Anton
 * */
import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

public class SkipTurnEvent extends BaseEvent{
	
	public SkipTurnEvent(String eventName, String description) {
		this.name = eventName;
		this.description = description;
	}

	@Override
	public void performEvent() {
		Player player = GameSession.getInstance().getBoard().getCurrentPlayer();
        IO playerIO = ActionUtils.getPlayerIO(player);

        player.setStatus(Status.SKIP_TURN);
        playerIO.showMessage("Жизнь хорошо, когда ешь не спеша. Вы пропускаете 1 ход");
        ActionUtils.sendMessageToAll(player.getName() + " пропускает 1 ход");
	}
}
