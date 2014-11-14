package com.monopoly.board;

import com.monopoly.board.player.Player;

import java.util.List;

/**
 * Created by Roma on 06.11.2014.
 */
/**
* ОперацииСИгроками
*/
public interface BoardPlayerOperations {
    public List<Player> getPlayers();
    public Player nextPlayer();
    public Player currentPlayer();
}
