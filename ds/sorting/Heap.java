package com.rovo98.ds.sorting;

import com.rovo98.ds.sorting.exercises.Stopwatch;

import java.util.Random;

/**
 * Heap sort with java implementation.
 * @author rovo98
 * Date: 14/3/2018
 * learing from algs4.
 */
public class Heap {
    /*********************************************************
     *  Helper for sorting.
     *********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    // heap sort.
    public static double sort(Comparable[] a) {
        Stopwatch sw = new Stopwatch();
        int n = a.length;
        // build up the heap.
        for (int k = n/2; k>=0; k--) {
            sink(a, k, n);
        }
        while (n > 0) {
            exch(a, 0, --n);
            sink(a, 0, n);
        }
        assert isSorted(a);
        return sw.elapsedTime();
    }
    // Adjusting the heap by sinking the elements.
    private static void sink(Comparable[] a, int k, int n) {
        while (2 * k < n) {
            int j = 2 * k;
            if (j < n-1 && less(a, j, j+1))
                j++;
            if (!less(a, k, j))
                break;
            exch(a, k, j);
            k = j;
        }
    }

    /*************************************************
     *  Check if the array is sorted
     *************************************************/
    @SuppressWarnings("unchecked")
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i-1]) < 0) {
                return false;
            }
        }
        return true;
    }
    // print the array.
    private static void print(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            if ((i+1) % 50 == 0)
                System.out.println();
        }
        System.out.println();
    }
    /**
     * Unit tests the {@code Heap} sort.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int SIZE = 100000;
        Integer[] testArr = new Integer[SIZE];
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The test array is the following:");
        Heap.print(testArr);
        double time = Heap.sort(testArr);
        Heap.print(testArr);
        System.out.println("After calling the function.(Finished in " + time +"seconds)");
    }
}
