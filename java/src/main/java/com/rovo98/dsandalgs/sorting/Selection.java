package com.rovo98.dsandalgs.sorting;

import com.rovo98.dsandalgs.sorting.exercises.SortCompare;
import java.util.Random;

/**
 * SelectionSort Implement.
 * @author rovo98
 * Date: 17/2/2018
 */
public class Selection {
    /**
     * Exchange two elements in arrays at index {@code i} and {@code j}.
     * @param a the array where need to be modified.
     * @param i the integer representing index one.
     * @param j the integer representing another index.
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /*********************************************************
     *  Helper for sorting.
     *********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * SelectionSort the unsorted array.
     * @param a the array where need to be sorted.
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }

        assert isSorted(a);
    }

    /************************************************
     *  Check if the array is sorted.
     ************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            if ((i+1) % 50 == 0)
                System.out.println();
        }
        System.out.println();
    }

    /**
     * Unit tests the {@code Selection} sort algorithm.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int testSize = 1000000;
        Integer[] testArr = new Integer[testSize];
        // Initializes the test array with integer in range of [0, 99] randomly.
        Random r = new Random();
        for (int i = 0; i < testSize; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        Selection.show(testArr);
        double time = SortCompare.time("Selection", testArr);
        Selection.show(testArr);
        System.out.println("After calling the Selection sort function.(Finished in " + time + " seconds)");
    }
}
