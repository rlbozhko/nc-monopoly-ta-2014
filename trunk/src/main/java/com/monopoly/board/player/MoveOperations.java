package com.monopoly.board.player;

/**
 * Created by mdolina on 07.11.2014.
 */

import com.monopoly.board.cells.Cell;

/**
 * Передвижения по карте
 */
public interface MoveOperations {
    int getPosition();

    int getLastPosition();

    void goToPosition(int position);

    Cell getCurrentCell();

    boolean isPayRent();

    void setPayRent(boolean payRent);
    
    boolean isOfferADeal();
    
    void setOfferADeal(boolean askedDeal);
}