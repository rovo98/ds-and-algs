package com.rovo98.ds.sorting;

import com.rovo98.ds.sorting.exercises.SortCompare;
import java.util.Random;

/**
 * ShellSort Implement.
 * @author rovo98
 * Date: 17/2/2018
 */
public class Shell {
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

    /*********************************************************
     *  Helper for sorting.
     *********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * ShellSor the unsorted array.
     * @param a the array where need to be sorted.
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n/3) {
            h = 3 * h + 1;          // 1, 4, 13, 40, 121, 364, 1093, ...
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h&&less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }

        assert isSorted(a);
    }
    /*
    moving the elements that less than key to the right side rather than swap each two of them.
     */
    public static void sortImproved(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                Comparable key = a[i];
                int j = i - h;
                for (; j >= 0 && less(key, a[j]); j -= h) {
                    a[j+h] = a[j];
                }
                a[j+h] = key;
            }
            h /= 3;
        }
        assert isSorted(a);
    }

    /*************************************************
     *  Check if the array is sorted.
     *************************************************/
    private static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i =1; i < n; i++) {
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
     * Unit tests the {@code Shell} sort algorithm.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int testSize = 10000000;
        Integer[] testArr = new Integer[testSize];
        // Initializes the test array with integer in range of [0, 99] randomly.
        Random r = new Random();
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        Shell.show(testArr);
        double time = SortCompare.time("ShellImproved", testArr);
        Shell.show(testArr);
        System.out.println("After calling the Shell sort function.(Finished in " + time + "seconds)");

    }
}
