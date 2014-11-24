package com.monopoly.board.player;

import com.monopoly.board.cells.PropertyCell;

/**
 * Created by mdolina on 11/21/14.
 */
public interface PropertyOperations {

    public void buyProperty(PropertyCell propertyCell);

    public void sellProperty(PropertyCell propertyCell);

    public void putUpProperty(PropertyCell propertyCell);

    public void buyBackProperty(PropertyCell propertyCell);
}
