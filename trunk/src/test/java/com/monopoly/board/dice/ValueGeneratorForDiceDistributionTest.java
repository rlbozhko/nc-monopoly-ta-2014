package com.monopoly.board.dice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roma on 30.11.2014.
 */
public class ValueGeneratorForDiceDistributionTest {
    public static void main(String[] args) {
        ValueGeneratorForDice generator = new ValueGeneratorForDice();
        Thread genThread = new Thread(generator);
        genThread.start();
        int number_of_repeat = 100;
        int[] sumOfValues1 = new int[6];
        int[] sumOfValues2 = new int[6];
        int doubles = 0;
        List<Integer> sumOfValues = new ArrayList<>();
        for (int i = 0; i < number_of_repeat; i++) {
            try {
                Thread.currentThread().sleep(110);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int val1 = generator.getValue1();
            int val2 = generator.getValue2();
            sumOfValues1[val1 - 1]++;
            sumOfValues2[val2 - 1]++;
            sumOfValues.add(val1 + val2);
            if (val1 == val2) {
                doubles++;
            }
            System.out.println(val1 + " " + val2 + "  = " + (val1 + val2));
        }
        System.out.println();
        System.out.println("Распеределение чисел 1 и 2 значений");
        printSequence(6);
        printValues(sumOfValues1);
        printValues(sumOfValues2);
        System.out.println("Количество дублей " + doubles);
        System.out.println();
        System.out.println("Распределение сумм");
        printNumberOfSum(sumOfValues);

        generator.finish();
    }

    private static void printSequence(int maxNumber) {
        for (int i = 1; i <= maxNumber; i++) {
            System.out.printf("%3d ", i);
        }
        System.out.println();
    }

    private static void printNumberOfSum(List<Integer> sumOfValues) {
        int[] numberOfSums = new int[12];
        for (Integer value : sumOfValues) {
            numberOfSums[value - 1]++;
        }
        printSequence(numberOfSums.length);
        printValues(numberOfSums);
    }

    private static void printValues(int[] sumOfValues1) {
        for (int i = 0; i < sumOfValues1.length; i++) {
            System.out.printf("%3d ", sumOfValues1[i]);
        }
        System.out.println();
    }

}
