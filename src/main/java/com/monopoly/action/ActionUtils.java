package com.monopoly.action;

import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;
import com.monopoly.io.IO;

import java.util.List;

/**
 * Created by Roma on 24.11.2014.
 */
public class ActionUtils {
    public static IO getPlayerIO(Player player) {
        List<IO> ioList = GameSession.getInstance().getIO();
        for (int i = 0; i < ioList.size(); i++) {
            Player user = ioList.get(i).getUser();
            if (player.equals(user)) {
                return ioList.get(i);
            }
        }
        return null;
    }

    public static void sendMessageToAll(String message) {
        for (IO io : GameSession.getInstance().getIO()) {
            io.showMessage(message);
        }
    }

}
