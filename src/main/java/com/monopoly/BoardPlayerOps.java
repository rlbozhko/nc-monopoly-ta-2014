package com.monopoly;

import java.util.List;

/**
 * Created by Roma on 06.11.2014.
 */
/**
* ОперацииСИгроками
*/
public interface BoardPlayerOps {
    public List<Player> getPlayers();
    public Player nextPlayer();
    public Player currentPlayer();
}
