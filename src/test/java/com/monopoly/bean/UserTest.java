package com.monopoly.bean;

/**
 * Created by Dmitriy on 08.01.2015.
 */

import org.junit.Test;
import org.junit.Assert;

public class UserTest {
    User user= new User(" "," "," ");

    @Test
    public void UserCreationTest() {
        user.setPassword("password");
        user.setEmail("email");
        user.setHash("hash");

        Assert.assertEquals("password", user.getPassword());
        Assert.assertEquals("email", user.getEmail());
        Assert.assertEquals("hash", user.getHash());

    }
}
