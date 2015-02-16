package com.monopoly.action.deal;

import com.monopoly.action.ActionUtils;
import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 18.12.2014.
 */
public class EmptyDeal implements Deal {
    private Player source;
    private Player target;
    private IO sourceIO;
    private IO targetIO;

    public EmptyDeal(Player source, Player target) {
        this.source = source;
        this.target = target;
        sourceIO = ActionUtils.getPlayerIO(source);
        targetIO = ActionUtils.getPlayerIO(target);
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
        return "Игрок " + source.getName() + "<br>";
    }

    @Override
    public void performDeal() {
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
