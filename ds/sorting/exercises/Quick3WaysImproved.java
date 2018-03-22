package com.rovo98.ds.sorting.exercises;

import java.util.Random;

/**
 * An improvement of quick3Ways
 * @author rovo98
 * Date: 2018.3.22
 */
public class Quick3WaysImproved {
    private static final int BOUND = 7;
    // This class will not be instanced.
    private Quick3WaysImproved() {}

    // quick3ways Sort improved.
    private static double sort(Comparable[] a) {
        Stopwatch sw = new Stopwatch();
        quickSort(a, 0, a.length - 1);
        assert isSorted(a);
        return sw.elapsedTime();
    }
    @SuppressWarnings("unchecked")
    private static void quickSort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + BOUND) {
            insertionSort(a, lo, hi);
            return;
        }
        // dealWithPivot will increase the running time wile deal with big input data
        // it need more comparison.
//        dealWithPivot(a, lo, hi);    // since the pivot is a[lo+1].
//        int lt = lo + 1;
//        int gt = hi;
//        int i = lt + 1;
        int lt = lo;
        int gt = hi;
        int i = lt + 1;
        Comparable pivot = a[lo];

        while (i <= gt) {
            int cmp = a[i].compareTo(pivot);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, gt--, i);
            else              i++;
        }
        quickSort(a, lo, lt-1);
        quickSort(a, gt+1, hi);
    }

    /*********************************************
     *Sorting helper functions.
     *********************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
    // insertion sort.
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            Comparable key = a[i];
            int j = i - 1;
            for (; j >= lo && less(key, a[j]); j--) {
                a[j+1] = a[j];
            }
            a[j+1] = key;
        }
    }
    // deal with pivots.
    private static void dealWithPivot(Comparable[] a, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (less(a[mid], a[lo])) {
            exch(a, lo, mid);
        }
        if (less(a[hi], a[mid])) {
            exch(a, mid, hi);
        }
        if (less(a[hi], a[lo])) {
            exch(a, lo, hi);
        }
        exch(a, mid, lo+1);  // Set the pivot to lo+1 as a sentinel.
    }

    /**********************************************
     * Array helper.
     **********************************************/
    private static void print(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            if ((i + 1) % 50 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
    /**
     * Unit tests the {@code Quick3Ways} data type.
     * @param args command arguments.
     */
    public static void main(String[] args) {
        final int SIZE = 10000000;
        Integer[] testArr = new Integer[SIZE];
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        print(testArr);
        double time = sort(testArr);
        print(testArr);
        System.out.println("After calling the function. (Finished in "+time+"seconds)");
    }
}
