package com.monopoly;

public class DiceRunnable implements Runnable {
    private Dice die1;
    private Dice die2;
    private int min;
    private int max;
    private boolean isStop;

    public DiceRunnable(Dice die1, Dice die2, boolean isStop) {
        this.die1 = die1;
        this.die2 = die2;
        this.min = 1;
        this.max = 6;
        this.isStop = isStop;
    }

    @Override
    public void run() {
        while (true) {
            die1.setFace(min + (int) (Math.random() * ((max - min) + 1)));
            die2.setFace(min + (int) (Math.random() * ((max - min) + 1)));
            if (isStop) {
                break;
            }
        }
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }
   
}
