package com.monopoly.board.player;

/**
 * Created by Dmitriy on 21.01.2015.
 */
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WalletTest {
    Wallet wallet = new Wallet();

    @Test
    public void addmoneyTest() {
        wallet.addMoney(100);
        Assert.assertEquals(100, wallet.getMoney());
    }

    @Test
    public void subtractmoneyTest() {
        wallet.addMoney(100);
        wallet.subtractMoney(50);
        Assert.assertEquals(50, wallet.getMoney());
        wallet.subtractMoney(200);
        Assert.assertEquals(50, wallet.getMoney());
    }
}
