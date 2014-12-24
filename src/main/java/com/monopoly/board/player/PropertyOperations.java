package com.monopoly.board.player;

import com.monopoly.board.cells.PropertyCell;

/**
 * Created by mdolina on 11/21/14.
 */
public interface PropertyOperations {

    public boolean buyProperty(PropertyCell propertyCell);

    boolean hasPledgedProperty();
}
