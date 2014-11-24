package com.monopoly.board.events;

/**
 * Created by Roma on 31.10.2014.
 */

import com.monopoly.board.Board;

/**
 * Событие
 */
public interface Event {
    public void performEvent(Board board);

    public String getDescription();

    public String getName();
}
