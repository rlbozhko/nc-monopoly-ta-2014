package com.monopoly.database;

import com.monopoly.board.player.Player;
import com.monopoly.board.player.Wallet;
import com.monopoly.database.dao.GenericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoImpl implements GenericDao<Player> {
    private Connection connection;

    public PlayerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Player insert(Player object) {
        return null;
    }

    @Override
    public Player getByKey(int key) {
        return null;
    }

    @Override
    public void update(Player object) {

    }

    @Override
    public void delete(Player object) {

    }

     @Override
    public List<Player> getAll() {
        List<Player> list = null;
         List<Wallet> list1 = null;
        String query = "select obj_player.NAME name,obj_player.object_id id, \n" +
                "ref_player.REFERENCE position, \n" +
                "attr_player.value money \n" +
                "from OBJECTS obj_player join OBJREFERENCE ref_player on (obj_player.object_id = ref_player.OBJECT_ID)\n" +
                "and ref_player.ATTR_ID = 2 \n" +
                "join ATTRIBUTES attr_player on (attr_player.OBJECT_ID = obj_player.OBJECT_ID)\n" +
                "and attr_player.ATTR_ID = 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            list = new ArrayList<Player>();
            while (rs.next()) {
                Player player = new Player(rs.getString("name"),new Wallet());
                player.getWallet().addMoney(rs.getInt("money"));
                //player.setStatus(Status.valueOf(rs.getString("")));//статус
                player.goToPosition(rs.getInt("position"));
                list.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
