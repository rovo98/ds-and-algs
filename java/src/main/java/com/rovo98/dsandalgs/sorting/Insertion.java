package com.rovo98.dsandalgs.sorting;

import com.rovo98.dsandalgs.sorting.exercises.SortCompare;
//import edu.princeton.cs.algs4.StdDraw;

import java.util.Random;

/**
 * InsertionSort Implement.
 * @author rovo98
 * Date: 17/2/2018
 */
public class Insertion {
    /**
     * Exchange two elements in array at index {@code i} and {@code j}.
     * @param a the array where need to be modified.
     * @param i the integer representing index one.
     * @param j the integer representing another index.
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**********************************************************
     * Helper for sorting.
     **********************************************************/
    @SuppressWarnings("unchecked")
    private  static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * InsertionSort the unsorted array.
     * @param a the array where need to be sorted.
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0&&less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a);
    }
    /*
    Moving the elements that less than key to the right side rather than swap each two of them.
     */
    public static void sortImproved(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            Comparable key = a[i];
            int j;
            for (j = i-1; j >= 0 && less(key, a[j]); j--) {
                a[j+1] = a[j];
            }
            a[j+1] = key;
        }
        assert  isSorted(a);
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
     * Unit tests the {@code Insertion} sort.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int testSize = 100;
        Integer[] testArr = new Integer[testSize];
        // Initializes the testArr with the integer in range of [0,99] randomly.
        Random r = new Random();
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        Insertion.show(testArr);
        double time = SortCompare.time("InsertionImproved", testArr);
        Insertion.show(testArr);
        System.out.println("After calling the Insertion sort.(Finished in " + time + " seconds)");
    }
}
