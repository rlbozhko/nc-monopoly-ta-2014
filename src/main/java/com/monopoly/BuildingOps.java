package com.monopoly;

/**
 * Created by Roma on 06.11.2014.
 */
/**
 * ОперацииЗдания
 */
public interface BuildingOps {
    public int getPrice();
    public boolean levelUp();
    public boolean levelDown();
    Event getEvent();
}