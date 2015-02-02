package com.monopoly.board.player;

/**
 * Created by Dmitriy on 20.01.2015.
 */
import com.monopoly.action.controller.PlayerActionController;
import com.monopoly.bean.User;
import com.monopoly.board.cells.PropertyCell;
import com.monopoly.game.session.*;
import com.monopoly.io.ConsoleIO;
import com.monopoly.io.DummyIO;
import com.monopoly.io.IO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerTest{
    Player tester = new Player("tester");
    public static final int START_MONEY=5000;

        List<Player> players = new ArrayList<>();
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        Player p3 = new Player("Player 3");
        Map<User, IO> usersIO = new HashMap<User, IO>();
        Session gameSession;

    @Before
    public void Before(){
        p1.setStatus(Status.START_TURN);
        players.add(p1);
        players.add(p2);
        players.add(p3);

        usersIO.put(new User("p1@test.ua", "123", "hash1"), new ConsoleIO(p1));
        usersIO.put(new User("p2@test.ua", "123", "hash2"), new DummyIO(p2));
        usersIO.put(new User("p3@test.ua", "123", "hash3"), new DummyIO(p3));

        GameSession.GameSessionBuilder.setBoard( GameSession.newBoard(players, START_MONEY));
        GameSession.GameSessionBuilder.setActionController(new PlayerActionController());
        GameSession.GameSessionBuilder.setPropertyManager(new PropertyManager(players));
        GameSession.GameSessionBuilder.setUsersIO(usersIO);
        gameSession = GameSession.getInstance();
    }

    @After
    public void CloseAll(){
    gameSession.close();
    }

    @Test
    public void setJailStatusTest() {
        p1.setJailStatus(Status.JAILED);
        Assert.assertEquals(Status.JAILED, p1.getJailStatus());
    }

    @Test
    public void setStatusTest() {
        p1.setStatus(Status.ACTIVE);
        Assert.assertEquals(Status.ACTIVE, p1.getStatus());
    }

    @Test
    public void setJailTermTest() {
        p1.setJailTerm(10);
        Assert.assertEquals(10, p1.getJailTerm());
    }

    @Test
    public void subtractJailTermTest() {
        p1.setJailTerm(5);
        p1.subtractJailTerm();
        Assert.assertEquals(4, p1.getJailTerm());
    }

    @Test
    public void setPositionTest() {
        p1.setPosition(5);
        Assert.assertEquals(5, p1.getPosition());
    }

    @Test
    public void isJailedTest() {
        p1.setJailStatus(Status.JAILED);
        Assert.assertEquals(true, p1.isJailed());
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("Player 1", p1.getName());
    }

    @Test
    public void addMoneyTest() {
        p1.addMoney(20);
        Assert.assertEquals(5020, p1.getMoney());
    }

    @Test
    public void subtractMoneyTest() {
        p1.addMoney(20);
        p1.subtractMoney(10);
        Assert.assertEquals(5010, p1.getMoney());
    }

    @Test
    public void setPayRentTest() {
        p1.setPayRent(true);
        Assert.assertEquals(true, p1.isPayRent());
    }

    @Test
    public void gotoPositionTest() {
        p1.goToPosition(7);
        Assert.assertEquals(7, p1.getPosition());

    }

    @Test
    public void getLastPositionTest() {
        p1.goToPosition(2);
        p1.goToPosition(3);
        Assert.assertEquals(2, p1.getLastPosition());
    }

    @Test
    public void getCurrentCellTest() {
        p1.goToPosition(4);
        Assert.assertEquals(GameSession.getInstance().getBoard().getCells().get(4), p1.getCurrentCell());
    }

    @Test
    public void buyPropertyTest() {
        Assert.assertEquals(false, p1.hasProperty());
        p1.goToPosition(2);
        p1.buyProperty((PropertyCell) GameSession.getInstance().getBoard().getCells().get(3));
        Assert.assertEquals(true, p1.hasProperty());
    }
   }
