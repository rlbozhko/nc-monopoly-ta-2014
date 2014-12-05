package com.monopoly.board.events;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.TestSession;
import com.monopoly.io.IO;

/**
 * Created by kos on 24.11.2014.
 */


/**
 * дополнительный ход
 */

public class ExtraTurnEvent extends BaseEvent{

    public ExtraTurnEvent(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public void performEvent() {
        Player player = TestSession.getInstance().getBoard().getCurrentPlayer();
        IO playerIO = ActionUtils.getPlayerIO(player);

        player.setStatus(Status.START_TURN);
        playerIO.showMessage("Сегодня ваш день! Вы получили возможность походить еще раз!!!");
    }
}