package com.rovo98.ds.sorting.exercises;

import java.util.Arrays;
import java.util.Random;

/**
 * @author rovo98
 * Date: 17/2/2018
 * Learning from algs4.
 */
public class Stopwatch {
    private long start;

    /**
     * Initializes a stopwatch.
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    /**
     * Returns the CPU elapsed time (in seconds) since the stopwatch was created.
     * @return the CPU elapsed time (in seconds) since the stopwatch was created.
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    /**
     * Unit tests the {@code stopWatch}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        // sort a array.
        final int size = 1000000;
        int[] testArr = new int[size];
        // Initializes the test array with integer in range of [0, 99] randomly.
        for (int i = 0; i < size; i++) {
            testArr[i] = new Random().nextInt(100);
        }
        Stopwatch sw = new Stopwatch();
        Arrays.sort(testArr);
        double time = sw.elapsedTime();
        System.out.println("After sorting the test array with a size of " + size + ", finished in " + time + " seconds.");
    }
}
