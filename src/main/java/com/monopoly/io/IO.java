package com.monopoly.io;

import com.monopoly.action.Action;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;

/**
 * Created by Roma on 19.11.2014.
 */
public interface IO {
    void outputBoardState();

    void outputAvailableActions();

    void performAction(Action action);

    Player getUser();

    Player selectPlayer();

    Property selectProperty(Player player);

    Building selectBuilding(Property property);

    com.monopoly.action.deal.Deal dealDialog(Player otherPlayer);

    boolean yesNoDialog(String message);

    void showMessage(String message);
    
    void showDice();
}
