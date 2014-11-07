package com.monopoly;

/**
 * Created by Roma on 31.10.2014.
 */

import java.util.List;

/**
 * Событие
 */
public interface Event {
    public void performEvent(Board board);
    public String getDescription();
    public String getName();
}
