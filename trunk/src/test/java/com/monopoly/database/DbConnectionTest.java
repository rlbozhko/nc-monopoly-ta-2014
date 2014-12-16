package com.monopoly.database;

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionTest extends TestCase {
Connection con;
    public void testGetConnection() throws SQLException {
        con = new DbConnection().getConnection();
        assertNotNull(con);
        con.close();
        assertTrue(con.isClosed());

    }
}