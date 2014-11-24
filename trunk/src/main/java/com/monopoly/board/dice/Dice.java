package com.monopoly.board.dice;

public class Dice {
    private int face;

    public Dice() {
        this.face = 0;
    }

    public synchronized int getFace() {
        return face;
    }

    public synchronized void setFace(int face) {
        this.face = face;
    }


}
