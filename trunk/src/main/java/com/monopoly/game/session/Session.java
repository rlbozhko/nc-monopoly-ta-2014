package com.monopoly.game.session;

import com.monopoly.action.controller.ActionController;
import com.monopoly.bean.User;
import com.monopoly.board.Board;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.io.IO;

import java.util.List;
import java.util.Set;

/**
 * Created by Roma on 19.11.2014.
 */
public interface Session {
    Board getBoard();
    PropertyManager getPropertyManager();
    ActionController getActionController();
    List<IO> getIO();
    void close();
    IO getUserIO(User user);
	Set<User> getUsers();
}
