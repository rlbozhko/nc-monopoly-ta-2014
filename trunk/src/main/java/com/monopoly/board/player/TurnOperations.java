package com.monopoly.board.player;

/**
 * Created by mdolina on 07.11.2014.
 */
/**
 * Операции хода
 */
public interface TurnOperations {
    public void startTurn();
    public void finishTurn();
    public void surrender();
}