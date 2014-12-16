package com.monopoly.database;

import com.monopoly.board.player.Player;
import com.monopoly.database.dao.DaoConnect;
import com.monopoly.database.dao.GenericDao;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlayerDaoImplTest extends TestCase {

    public void testGetAll() throws SQLException, PersistException {
        DaoConnect factory = new DbConnection();
        Connection connection = (Connection) factory.getConnection();
        List<Player> players = null;
        GenericDao playerDao =  factory.getDao(connection, Player.class);
        assertNotNull(playerDao);
        players = playerDao.getAll();

        for (Player player: players){
            assertNotNull("Player is not null",player.getName());
            assertNotNull("Wallent is not null",player.getWallet().getMoney());
//            System.out.println(player.getName()+ " " + player.getWallet().getMoney()+" "+ player.getPosition());
        }
    }
}