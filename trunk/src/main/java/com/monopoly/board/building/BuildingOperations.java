package com.monopoly.board.building;

/**
 * Created by Roma on 06.11.2014.
 */

/**
 * ОперацииЗдания
 */
public interface BuildingOperations {

    boolean levelUp();
    boolean levelDown();
    
    int currentPrice();
    int currentLevel();
    int getMaxLevel();
    int getPrimaryCost();
    
    String getBuildingName();
    String getBuildingDescription();
    
}
