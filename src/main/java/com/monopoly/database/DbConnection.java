package com.monopoly.database;

import com.monopoly.board.player.Player;
import com.monopoly.database.dao.DaoConnect;
import com.monopoly.database.dao.GenericDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class DbConnection implements DaoConnect<Connection> {

    private Map<Class, DaoCreator> creators;

    public DbConnection(){
        creators = new HashMap<Class, DaoCreator>();

        creators.put(Player.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new PlayerDaoImpl(connection);
            }
        });
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        Properties properties = new Properties();
        InputStream input;
        try {
            Locale.setDefault(Locale.ENGLISH);
            input = new FileInputStream("db.properties");
            properties.load(input);
            Class.forName(properties.getProperty("DRIVER_CLASS"));
            connection = DriverManager.getConnection(properties.getProperty("URL"),
                    properties.getProperty("USER"),
                    properties.getProperty("PASSWORD"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection Failed!");
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);

    }
}
