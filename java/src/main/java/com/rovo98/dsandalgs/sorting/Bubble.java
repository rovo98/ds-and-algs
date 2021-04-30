package com.rovo98.dsandalgs.sorting;

import com.rovo98.dsandalgs.sorting.exercises.SortCompare;
import java.util.Random;


/**
 * BubbleSort Implement.
 * @author rovo98
 * Date: 17/2/2018
 */
public class Bubble {
    private Bubble() {}

    /**
     * Exchange two elements in array at index {@code i} and {@code j}.
     * @param a the array where need to be modify.
     * @param i the integer representing the index one.
     * @param j the integer representing another index.
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /*************************************************************
     *  Helper for sorting.
     *************************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Bubble sort the unsorted array.
     * @param a the array where need to be sorted.
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        boolean flag = true;
        for (int i = 1; i < n&&flag; i++) {
            flag = false;
            for (int j = 0; j < n-i; j++) {
                if (!less(a[j], a[j+1])) {   // sort the array in ascending order.
                    exch(a, j, j+1);
                    flag = true;
                }
            }
        }

        assert isSorted(a);
    }

    /**************************************************
     *  Check if the array is sorted.
     **************************************************/
     private static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static void show(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
            if ((i+1) % 50 == 0)
                System.out.println();
        }
        System.out.println();
    }
    /**
     * Unit tests {@code Bubble} sort.
     * @param args command-line arguments
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
        Bubble.show(testArr);
        double time = SortCompare.time("Bubble", testArr);
        Bubble.show(testArr);
        System.out.println("After calling the Bubble sort function.(Finished in " + time + " seconds)");
    }
}
