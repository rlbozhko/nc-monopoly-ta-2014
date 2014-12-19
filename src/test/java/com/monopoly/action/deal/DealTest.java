package com.monopoly.action.deal;

import com.monopoly.board.Board;
import com.monopoly.board.building.Building;
import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.Property;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.board.player.Player;
import com.monopoly.game.session.GameSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 19.12.2014.
 */
public class DealTest {
    public static void main(String[] args) {
        Player source = new Player("SourcePlayer");
        Player target = new Player("TargetPlayer");

        Monopoly monopoly1 = new Monopoly("Monopoly1");
        Property property1 = new PropertyCell("c2m1", "c2m1 desc", 1, null,
                new ArrayList<Building>(), 1000, 200, monopoly1);
        Property property2 = new PropertyCell("c3m1", "c3m1 desc", 2, null,
                new ArrayList<Building>(), 1000, 200, monopoly1);

        property1.setAndAddToOwner(target);
        property2.setAndAddToOwner(source);

        List<Property> askProperties = new ArrayList<>();
        askProperties.add(property1);
        List<Property> giveProperties = new ArrayList<>();
        giveProperties.add(property2);

        Deal deal = new DealMock(source, target);
        deal = new AskMoneyDeal(deal, 100);
        deal = new GiveMoneyDeal(deal, 200);
        deal = new AskPropertyDeal(deal, askProperties);
        deal = new GivePropertyDeal(deal, giveProperties);

        System.out.println(deal.message());

    }
}
