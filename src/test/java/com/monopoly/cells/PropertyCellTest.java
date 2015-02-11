package com.monopoly.cells;

/**
 * Created by Dmitriy on 22.01.2015.
 */
import com.monopoly.action.controller.PlayerActionController;
import com.monopoly.bean.User;
import com.monopoly.board.building.Building;
import com.monopoly.board.building.BuildingType;
import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.MonopolyType;
import com.monopoly.board.cells.PropertyCell;

import java.util.*;

import com.monopoly.board.cells.PropertyStatus;
import com.monopoly.board.player.Player;
import com.monopoly.board.player.PropertyManager;
import com.monopoly.board.player.Status;
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.Session;
import com.monopoly.io.ConsoleIO;
import com.monopoly.io.DummyIO;
import com.monopoly.io.IO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class PropertyCellTest {
    Monopoly abc = new Monopoly(MonopolyType.MONOPOLY1);
    Building building = new Building(BuildingType.getTypeByText("CASTLE"), 100);
    PropertyCell prop = new PropertyCell("Name","Some property Cell",5,null,500,10,abc);
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

        usersIO.put(new User("p1@test.ua", "123", "hash1", "p1"), new ConsoleIO(p1));
        usersIO.put(new User("p2@test.ua", "123", "hash2", "p2"), new DummyIO(p2));
        usersIO.put(new User("p3@test.ua", "123", "hash3", "p3"), new DummyIO(p3));

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
    public void BuildBuildingTest()
    {   p1.buyProperty(prop);
        Assert.assertEquals(false, prop.hasBuilding());
        prop.buildBuilding(building);
        Assert.assertEquals(true, prop.hasBuilding());
    }

    @Test
    public void SellBuildingTest()
    {   p1.buyProperty(prop);
        Assert.assertEquals(false, prop.hasBuilding());
        prop.buildBuilding(building);
        Assert.assertEquals(true, prop.hasBuilding());
        prop.sellBuilding();
        Assert.assertEquals(false, prop.hasBuilding());
    }

    @Test
    public void UpgradeBuildingTest()
    {   p1.buyProperty(prop);
        prop.buildBuilding(building);
        Assert.assertEquals(1, building.getCurrentLevel());
        prop.upgradeBuilding();
       Assert.assertEquals(2, building.getCurrentLevel());
        prop.upgradeBuilding();
        prop.upgradeBuilding();
        prop.upgradeBuilding();
        prop.upgradeBuilding();
        Assert.assertEquals(5, building.getCurrentLevel());
    }

    @Test
    public void IsPledgedTest()
    {   p1.buyProperty(prop);
        Assert.assertEquals(PropertyStatus.UNPLEDGED, prop.getStatus());
        prop.buildBuilding(building);
        prop.setStatus(PropertyStatus.PLEDGED);
        Assert.assertEquals(true, prop.isPledged());

    }

    @Test
    public void IsdPledgedTest()
    {   p1.setStatus(Status.START_TURN);
        p1.buyProperty(prop);
        Assert.assertEquals(PropertyStatus.UNPLEDGED, prop.getStatus());
        prop.buildBuilding(building);
        prop.setPayBackMoney(15);
        prop.setPledgePercent(2.5);
        prop.setTurnsToPayBack(2);
        prop.getPayBackMoney();
        Assert.assertEquals(15, prop.getPayBackMoney());
        Assert.assertEquals(2, prop.getTurnsToPayBack());
        prop.risePayBackMoney();
        p1.goToPosition(2);
        p1.setStatus(Status.FINISH);

        p1.setStatus(Status.START_TURN);
        p1.goToPosition(3);
        prop.getPayBackMoney();
        Assert.assertEquals(16, prop.getPayBackMoney());
        Assert.assertEquals(2, prop.getTurnsToPayBack());
        p1.setStatus(Status.FINISH);
        //prop.setStatus(PropertyStatus.PLEDGED);


    }
    @Test
    public void GetRentTest()
    {
        Assert.assertEquals(12, prop.getRent());
    }

    @Test
    public void GetStatusTest()
    {
        Assert.assertEquals(PropertyStatus.UNPLEDGED, prop.getStatus());
    }

    @Test
    public void GetMonopolyTest()
    {
        Assert.assertEquals( abc, prop.getMonopoly());
    }

    @Test
    public void GetMaxLevelTest()
    {
        Assert.assertEquals( 5, prop.getMaxLevel());
    }

    @Test
    public void GetNameTest()
    {
        Assert.assertEquals("Name", prop.getName());
    }

}
