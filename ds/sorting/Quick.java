package com.rovo98.ds.sorting;

import com.rovo98.ds.sorting.exercises.Stopwatch;

import java.util.Random;

public class Quick {
    // This class should not be instanced.
    private Quick() { }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Rearranges the arrays in ascending order, using the natural order.
     * @param a the array to be sorted.
     * @return the running time of this function.
     */
    public static double sort(Comparable[] a) {
        Stopwatch sw = new Stopwatch();
        sort(a, 0, a.length-1);
        assert isSorted(a);
        return sw.elapsedTime();
    }
    // quick sort the subArray from a[lo] to a[hi]
    private static void  sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }
    // partition the subArray a[lo .. hi] so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    // and return the index j.
    private static int partition(Comparable[] a,int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            // Find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi)
                    break;
            }

            // Find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo)    // redundant since a[lo] acts as sentinel
                    break;
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            if (less(a[i], a[i - 1])) {
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
     * Unit tests the {@code Quick} sort.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int SIZE = 1000000;
        Integer[] testArr = new Integer[SIZE];
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        show(testArr);
        double time = sort(testArr);
        show(testArr);
        System.out.println("After calling function. (Finished in " + time + "seconds)");
    }
}
