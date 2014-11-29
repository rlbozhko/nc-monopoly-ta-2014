package com.monopoly.board.events;
/**
 * Create By Kulikovsky Anton
 * */
import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

public class SkipTurnEvent implements Event{
	private String name;
	private String description;
	
	public SkipTurnEvent(String eventName, String description) {
		this.name = eventName;
		this.description = description;
	}

	@Override
	public void performEvent() {
		Player player = TestSession.getInstance().getBoard().getCurrentPlayer();
        IO playerIO = ActionUtils.getPlayerIO(player);

        player.setStatus(Status.SKIP_TURN);
        playerIO.showMessage("Жизнь хорошо, когда ешь не спеша. Вы пропускаете 1 ход");
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

}
