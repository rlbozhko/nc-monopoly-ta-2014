package com.monopoly.tools;
/**
 * Create By Kulikovsky Anton
 * */
public class XORShiftRandom {
    private final int MAGIC_NUMBER1 = 21;
    private final int MAGIC_NUMBER2 = 35;
    private final int MAGIC_NUMBER3 = 4;

    private long last;

    public XORShiftRandom() {
        this.last = System.nanoTime();
    }

    public int nextInt(int max) {
        last ^= (last << MAGIC_NUMBER1);
        last ^= (last >>> MAGIC_NUMBER2);
        last ^= (last << MAGIC_NUMBER3);
        int value = (int) last % max;
        return (value < 0) ? -value + 1 : value + 1;
    }

}
