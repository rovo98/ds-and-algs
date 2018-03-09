package com.rovo98.ds.sorting.exercises;

import java.util.Random;

/**
 * Exercise: QuickSort improvement:
 *          2. 三取样切分，并把基准点(pivot)作为哨兵(sentinel).
 *
 *
 * @author rovo98
 * Date: 6/3/2018
 */
public class QuickSortImprovedTwo {
    // This class will not be instanced.
    private QuickSortImprovedTwo() { }

    /**********************************************************
     *  Helper for sorting.
     **********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**************************************************
     *  Check if the array is sorted.
     **************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
    // quickSort the array.
    public static double quickSort(Comparable[] a) {
        Stopwatch sw = new Stopwatch();
        sort(a, 0, a.length-1);
        assert isSorted(a);
        return sw.elapsedTime();
    }
    // sort the array recursively.
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        dealPivot(a, lo, hi);
        Comparable v = a[hi - 1]; // the sentinel for this range.
        int i = lo;
        int j = hi - 1;

        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) break;      // redundant since a[hi - 1] is sentinel.
            }
            while (j > lo && less(v, a[--j])) {

            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        if (i < hi-1) {
            exch(a, i, hi-1);
        }
        sort(a, lo, i-1);
        sort(a, i+1, hi);
    }

    /**
     * Pick the first, middle and last element, then let the medium be the pivot
     * and set to the {@code hi - 1} as a sentinel.
     * @param a the array to be sorted.
     * @param lo the starting index of range to be sorted.
     * @param hi the ending index of range to be sorted.
     */
    private static void dealPivot(Comparable[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (less(a[mid], a[lo])) {
            exch(a, mid, lo);
        }
        if (less(a[hi], a[lo])) {
            exch(a, lo, hi);
        }
        if (less(a[hi], a[mid])) {
            exch(a, mid, hi);
        }
        // set the middle item to the hi - 1 as a sentinel.
        exch(a, mid, hi-1);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
     * Unit tests the {@code QuickImprovedTwo}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int SIZE = 10000000;
        Integer[] testArr = new Integer[SIZE];
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input array is the following:");
        show(testArr);
        double time = quickSort(testArr);
        show(testArr);
        System.out.println("After calling function.(Finished in " + time + "seconds)");
    }
}
