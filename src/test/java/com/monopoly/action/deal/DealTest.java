package com.monopoly.action.deal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;

/**
 * Created by Roma on 19.12.2014.
 */
public class DealTest {
	public static void main(String[] args) {
		Player source = new Player("SourcePlayer");
		Player target = new Player("TargetPlayer");
		List<Player> players = new LinkedList<>();
		players.add(source);
		players.add(target);

		Monopoly monopoly1 = new Monopoly("Monopoly1");
		Property property1 = new PropertyCell("c2m1", "c2m1 desc", 1, null, 1000, 200, monopoly1);
		Property property2 = new PropertyCell("c3m1", "c3m1 desc", 2, null, 1000, 200, monopoly1);

		PropertyManager propertyManager = new PropertyManager(players);

		propertyManager.setPropertyOwner(target, property1);
		propertyManager.setPropertyOwner(source, property2);

		List<Property> askProperties = new ArrayList<>();
		askProperties.add(property1);
		askProperties.add(property2);
		List<Property> giveProperties = new ArrayList<>();
		giveProperties.add(property2);
		giveProperties.add(property1);

		Deal deal = new DealMock(source, target);
		deal = new AskMoneyDeal(deal, 100);
		deal = new GiveMoneyDeal(deal, 200);
		deal = new AskPropertyDeal(deal, askProperties);
		deal = new GivePropertyDeal(deal, giveProperties);

		System.out.println(deal.message());
	}
}
