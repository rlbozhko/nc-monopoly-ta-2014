package com.monopoly.board.events;

/**
 * Created by Roma on 31.10.2014.
 */


/**
 * Событие
 */
public interface Event {
    public void performEvent();

    public String getDescription();

    public String getName();
}
