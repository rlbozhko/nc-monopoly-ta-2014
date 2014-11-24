package com.monopoly.board.events;

import com.monopoly.board.Board;
import com.monopoly.board.player.Player;
import com.monopoly.tools.XORShiftRandom;

import java.util.Random;

public class MoveEvent {
    private final int MAX_VALUE_MOVE_PLAYER = 7;
    private String eventName;
    private String descrition;

    public MoveEvent(String eventName, String descrition) {
        this.eventName = eventName;
        this.descrition = descrition;
    }

    Random random = new Random();
    XORShiftRandom xorShiftRandom = new XORShiftRandom();

    // TODO if (position + MAX_VALUE_MOVE_PLAYER) > board lenght
    public void doAction(Board board) {
        Player player = board.getCurrentPlayer();
        boolean isAdvance = random.nextBoolean();
        if (isAdvance) {
            player.goToPosition(player.getPosition()
                    + xorShiftRandom.nextInt(MAX_VALUE_MOVE_PLAYER));
        } else {
            player.goToPosition(player.getPosition()
                    - xorShiftRandom.nextInt(MAX_VALUE_MOVE_PLAYER));

        }

    }

}
