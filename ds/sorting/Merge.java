package com.rovo98.ds.sorting;

import com.rovo98.ds.sorting.exercises.SortCompare;

import java.util.Random;

/**
 * MergeSort Implement.
 * @author rovo98
 * Date: 23/2/2018
 * Learning from algs4.
 */
public class Merge {
    // This class should not be instantiated.
    private Merge() { }

    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subArrays.
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);
        int i = lo;
        int j = mid + 1;

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // move back to a[]
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi) {
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
        // post condition: a[lo .. hi] is sorted.
        assert isSorted(a, lo, hi);
    }
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        ubSort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }

    /**
     * Merge sort the unsorted array from up to bottom.
     * @param a  the unsorted array.
     * @param aux the auxiliary array.
     * @param lo  the starting index.
     * @param hi  the ending index.
     */
    private static void ubSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        ubSort(a, aux, lo, mid);
        ubSort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Merge sort the unsorted array from bottom to up.
     * @param a the unsorted array.
     */
    private static void buSort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n-sz; lo += sz + sz) {
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));
            }
        }
        isSorted(a);
    }

    /****************************************************
     * Check if array is sorted -- useful for debugging.
     ****************************************************/
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i < hi; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /****************************************************
     * Helper sorting function.
     ****************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
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
     * Unit tests the {@code Merge} sorting algorithm.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int testSize = 10000000;
        Integer[] testArr = new Integer[testSize];
        Random r = new Random();
        for (int i = 0; i < testSize; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        Merge.show(testArr);
        double time = SortCompare.time("Merge", testArr);
        Merge.show(testArr);
        System.out.println("After calling the mergeSort function. (Finished in " + time + "seconds).");
    }
}
