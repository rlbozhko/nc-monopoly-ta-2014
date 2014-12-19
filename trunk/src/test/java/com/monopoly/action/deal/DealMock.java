package com.monopoly.action.deal;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.io.DummyIO;
import com.monopoly.io.IO;

/**
 * Created by Roma on 19.12.2014.
 */
public class DealMock implements Deal {
    Player source;
    Player target;
    IO sourceIO;
    IO targetIO;

    public DealMock(Player source, Player target) {
        this.source = source;
        this.target = target;
        sourceIO = new DummyIO(source);
        targetIO = new DummyIO(target);
    }

    @Override
    public Player getSource() {
        return source;
    }

    @Override
    public Player getTarget() {
        return target;
    }

    @Override
    public IO getSourceIO() {
        return sourceIO;
    }

    @Override
    public IO getTargetIO() {
        return targetIO;
    }

    @Override
    public String message() {
        return "Игрок " + source.getName() + "\n";
    }

    @Override
    public void performDeal() {
    }

    @Override
    public boolean isValid() {
        return true;
    }
}


