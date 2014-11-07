package com.monopoly;

/**
 * Created by Roma on 06.11.2014.
 */
/**
 * ОперацииЗдания
 */
public interface BuildingOps {
    
	int currentPrice();
    boolean levelUp();
    boolean levelDown();
    Event getEvent();
    int currentLevel ();
}
