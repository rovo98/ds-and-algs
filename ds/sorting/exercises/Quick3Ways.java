package com.rovo98.ds.sorting.exercises;

import java.util.Random;

/**
 * Quick3Ways implement
 *
 * suppose v is the pivot of the unsorted array.
 *
 * we divide the array into three parts:
 *      1. let a[lo .. lt-1] less than v
 *      2. let a[lt .. i-1] equal to v
 *      3. let a[gt+1 .. hi] greater than v.
 *
 * And the elements in the range of a[i .. gt] are not be processed.
 *
 * @author rovo98
 * Date: 6/3/2018
 */
public class Quick3Ways {
    // This class will not be instanced.
    private Quick3Ways() { }

    /**********************************************************
     *  Helper for sorting.
     **********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /******************************************************
     *  Check if the array is sorted.
     ******************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * quick sort the array by dividing it into three parts.
     * @param a the array to be sorted.
     * @return  running time of this function.
     */
    public static double quick3Sort(Comparable[] a) {
        Stopwatch sw = new Stopwatch();
        sort(a, 0, a.length-1);
        assert isSorted(a);
        return sw.elapsedTime();
    }
    // sort the array recursively.
    @SuppressWarnings("unchecked")
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        Comparable v = a[lo];
        int i = lo + 1;
        int gt = hi;
        int lt = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);   // ascending order.  -- v.compareTo(a[i]) for descending order.
            if      (cmp < 0)    exch(a, lt++, i++);
            else if (cmp > 0)    exch(a, i, gt--);
            else                 i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    /******************************************************
     *  Helper for sorting.
     *****************************************************/
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
     * Unit tests the {@code Quick3Ways}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int SIZE = 10000000;
        Integer[] testArr = new Integer[SIZE];
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        show(testArr);
        double time = quick3Sort(testArr);
        show(testArr);
        System.out.println("After calling the function.(Finished is " + time + "seconds)");
    }
}
