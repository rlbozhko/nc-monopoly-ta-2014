package com.monopoly.board.player;

/**
 * Created by Dmitriy on 20.01.2015.
 */
import com.monopoly.game.session.GameSession;
import com.monopoly.game.session.SessionTest;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    Player tester = new Player("tester");

    @Test
    public void setJailStatusTest() {
        tester.setJailStatus(Status.JAILED);
        Assert.assertEquals(Status.JAILED, tester.getJailStatus());
    }

    @Test
    public void setStatusTest() {
        tester.setStatus(Status.ACTIVE);
        Assert.assertEquals(Status.ACTIVE, tester.getStatus());
    }
    @Test
    public void setJailTermTest() {
        tester.setJailTerm(10);
        Assert.assertEquals(10, tester.getJailTerm());
    }
    @Test
    public void subtractJailTermTest() {
        tester.setJailTerm(5);
        tester.subtractJailTerm();
        Assert.assertEquals(4, tester.getJailTerm());
    }

    @Test
    public void setPositionTest() {
        tester.setPosition(5);
        Assert.assertEquals(5, tester.getPosition());
    }

    @Test
    public void isJailedTest() {
        tester.setJailStatus(Status.JAILED);
        Assert.assertEquals(true, tester.isJailed());
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("tester", tester.getName());
    }

    @Test
    public void addMoneyTest(){
        tester.addMoney(20);
        Assert.assertEquals(20, tester.getMoney());
    }

    @Test
    public void subtractMoneyTest(){
        tester.addMoney(20);
        tester.subtractMoney(10);
        Assert.assertEquals(10, tester.getMoney());
    }

    @Test
    public void setPayRentTest(){
        tester.setPayRent(true);
        Assert.assertEquals(true, tester.isPayRent());
    }



   // @Test
    //public void gotoPositionTest() {
        //SessionTest.main();
      //  tester.goToPosition(6);
      //  Assert.assertEquals(6, tester.getPosition());
   // }

}
