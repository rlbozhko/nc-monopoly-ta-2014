package com.monopoly.tools;
/**
 * Create By Kulikovsky Anton
 * */
public class XORShiftStrategy implements RandomStrategy {
    private final int XOR_PARAMETER_A = 21;
    private final int XOR_PARAMETER_B = 35;
    private final int XOR_PARAMETER_C = 4;

    private long last;

    public XORShiftStrategy() {
        this.last = System.nanoTime();
    }

	@Override
	public int nextInt(int max) {
		last ^= (last << XOR_PARAMETER_A);
        last ^= (last >>> XOR_PARAMETER_B);
        last ^= (last << XOR_PARAMETER_C);
        int value = (int) last % max;
        return (value < 0) ? -value + 1 : value + 1;
	}
}
