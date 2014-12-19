package com.monopoly.action.deal;

import com.monopoly.board.player.Player;
import com.monopoly.io.IO;

/**
 * Created by Roma on 18.12.2014.
 */
public abstract class WrapperDeal implements Deal {
    private Deal deal;

    protected WrapperDeal(Deal deal) {
        this.deal = deal;
    }
    @Override
    public Player getSource() {
        return deal.getSource();
    }

    @Override
    public Player getTarget() {
        return deal.getTarget();
    }

    @Override
    public IO getSourceIO() {
        return deal.getSourceIO();
    }

    @Override
    public IO getTargetIO() {
        return deal.getTargetIO();
    }

    @Override
    public String message() {
        return deal.message();
    }

    @Override
    public void performDeal() {
        deal.performDeal();
    }
}
