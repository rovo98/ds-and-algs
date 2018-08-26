package com.rovo98.ds.sorting.exercises;

import java.util.Random;

/**
 * Exercise from algorithms4: 将merge() 方法中复制到 aux[]的过程，
 * 改为：aux[] 一半存放arr[]的正序，一半存放arr[]的逆序。
 *
 * @author rovo98
 *
 * Date: 5/3/2018
 */
public class QuickMerge {
    private static final int BOUND = 7;
    /*********************************************************
     *  Helper for sorting.
     *********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    public static double quickMergeSortUB(Comparable[] a) {
        Stopwatch sw = new Stopwatch();
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        sort(a, aux, 0, a.length-1);

        assert isSorted(a);
        return sw.elapsedTime();
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // insertion sort the small arrays.
        if (hi <= hi - lo + BOUND) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        quickMerge(a, aux, lo, mid, hi);
    }
    private static void quickMerge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Check if the subArray is already sorted.
        if (less(a[mid], a[mid + 1])) {
            return;
        }

        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);
        // copy the a[lo .. mid] to aux[lo .. mid]
        for (int k = lo; k <= mid; k++) {
            aux[k] = a[k];
        }
        // copy the a[hi .. mid+1] to aux[mid+1 .. hi]
        for (int k = mid+1; k <= hi; k++) {
            aux[k] = a[hi - k + mid + 1];
        }

        int i = lo;
        int j = hi;

        for (int k = lo; k <= hi; k++) {
            if (less(aux[j], aux[i])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j--];
            }
        }

        assert isSorted(a, lo, hi);
    }

    /********************************************************
     *  Check if the array is sorted.
     ********************************************************/
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
    // insertion Sort.
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            Comparable key = a[i];
            int j;
            for (j = i-1; j >= lo && less(key, a[j]); j--) {
                a[j+1] = a[j];
            }
            a[j+1] = key;
        }
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            if ((i + 1) % 50 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
    /**
     * Unit tests the {@code QuickMerge}.
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
        double time = quickMergeSortUB(testArr);
        show(testArr);
        System.out.println("After calling function. (Finished in " + time + "seconds)");
    }
}
