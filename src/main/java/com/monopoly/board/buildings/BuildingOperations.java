package com.monopoly.board.buildings;

/**
 * Created by Roma on 06.11.2014.
 */

import com.monopoly.board.events.Event;

/**
 * ОперацииЗдания
 */
public interface BuildingOperations {
    
	int currentPrice();
    boolean levelUp();
    boolean levelDown();
    Event getEvent();
    int currentLevel ();
    int getMaxLevel();
}
