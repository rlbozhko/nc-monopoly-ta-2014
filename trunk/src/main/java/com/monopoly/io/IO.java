package com.monopoly.io;

import java.util.Queue;

import com.monopoly.action.deal.Deal;
import com.monopoly.board.cells.Property;
import com.monopoly.board.player.Player;
import com.monopoly.io.WebIO.SelectPropertyHelper;
import com.monopoly.io.WebIO.YesNoDialog;

/**
 * Created by Roma on 19.11.2014.
 */
public interface IO {	

	Player getOwner();

	Player selectPlayer();

	Property selectProperty(Player player);

	boolean hasSelectPlayerRequest();

	void setSelectedPlayer(Player selectedPlayer);

	SelectPropertyHelper getSelectPropertyHelper();
	
	Deal dealDialog(Player otherPlayer);

	void setCreatedDeal(Deal deal);

	boolean hasCreateDealRequest();

	boolean yesNoDialog(String message);

	boolean hasYesNoDialog();

	YesNoDialog getYesNoDialog();

	void showMessage(String message);

	String getLastMessage();

	Queue<String> getAllMessages();

	boolean hasMessages();

	void showWarning(String message);

	String getWarning();

	boolean hasWarning();

	void showDice();
}
