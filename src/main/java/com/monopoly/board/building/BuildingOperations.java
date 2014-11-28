package com.monopoly.board.building;

/**
 * Created by Roma on 06.11.2014.
 */

/**
 * ОперацииЗдания
 */
public interface BuildingOperations {

    int currentPrice();
    boolean levelUp();
    boolean levelDown();
    int currentLevel();
    int getMaxLevel();
    
}
