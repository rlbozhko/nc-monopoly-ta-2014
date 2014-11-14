package com.monopoly.board.player;

/**
 * Created by mdolina on 07.11.2014.
 */
/**
 * Передвижения по карте
 */
public interface MoveOperations {
    public int getPosition();
    public void goToPosition(int position);
}