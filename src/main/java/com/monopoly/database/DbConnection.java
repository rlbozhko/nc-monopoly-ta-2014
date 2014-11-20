package com.monopoly.database;

import java.sql.*;
import java.util.Properties;

public class DbConnection {
    private String host;
    private String user;
    private String password;
    private String port;
    private String sid;
    private String url;
    private String driverName = "oracle.jdbc.driver.OracleDriver";

    Connection connection = null;
    private Properties properties = new Properties();

    public DbConnection(String host, String port, String sid, String user, String password) {
        this.host = host;
        this.port = port;
        this.sid = sid;
        this.user = user;
        this.password = password;
    }

    public void initProperties() {
        url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid;
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("useUnicode", "true");
        System.out.println();
    }

    public void init() {
        if (connection == null) {
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException e) {
                System.out.println("Oracle JDBC Driver not found");
                e.printStackTrace();
                return;
            }
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.err.println("Connection Failed!");
                e.printStackTrace();
                return;
            }
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet result = null;
        try {
            Statement stm = connection.createStatement();
            stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateQuery(String query) {
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
