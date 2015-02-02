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
        for (IO io : ioList) {
            Player user = io.getOwner();
            if (player.equals(user)) {
                return io;
            }
        }
        return null;
    }

    public static void sendMessageToAll(String message) {
        for (IO io : GameSession.getInstance().getIO()) {
            io.showMessage(message);
        }
    }
    
    public static Player getPlayerByName(String name) {
    	Player result = null;
    	List<Player> players = GameSession.getInstance().getBoard().getPlayers();
    	for (Player player : players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
    	
    	return result;
    }
}
