package com.rovo98.ds.sorting.exercises;

import java.util.Random;

/**
 * QuickSort improvement:
 *      1. 对于小数组，快速排序比插入排序慢；
 *      因为递归，快速排序的sort()方法在小数组中也会调用自己。
 *      改进： 使用插入排序，同归并排序的优化。
 *
 *
 * @author rovo98
 * Date: 6/3/2018
 */
public class QuickSortImproved {
    private static final int M = 7;
    // This class will not be instanced.
    private QuickSortImproved() { }
    /*********************************************************
     *  Helper for sorting.
     *********************************************************/
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

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * QuickSort the unsorted array.
     * @param a the array to be sorted.
     * @return the running time of this function.
     */
    public static double quickSort(Comparable[] a) {
        // Rearranges the array randomly.
        shuffle(a);
        Stopwatch sw = new Stopwatch();
        sort(a, 0, a.length-1);
        assert isSorted(a);
        return sw.elapsedTime();
    }
    // sort array recursively.
    private static void sort(Comparable[] a, int lo, int hi) {
        // insertion sort the small arrays.
        if (hi <= lo + M) {
            insertionSort(a, lo, hi);
            return;
        }
        Comparable v = a[lo];
        int i = lo;
        int j = hi+1;

        while (true) {
            // Find the item on lo to swap.
            while (less(a[++i], v)) {
                if (i == hi) break;
            }
            // Find the item on hi to swap.
            while (less(v, a[--j])) {
                if (j == lo) break; // redundant since a[lo] acts as sentinel -- when j = lo [while loop] breaks.
            }
            // Check if the pointers cross.
            if (j <= i) {
                break;
            }
            exch(a, i, j);
        }
        // swap the pivot item with the a[j].
        exch(a, lo, j);

        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    /**
     * shuffle the array.
     * @param a the array where need to be shuffle.
     */
    private static void shuffle(Comparable[] a) {
        int n = a.length;
        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            int random = r.nextInt(n-1);
            Comparable temp = a[i];
            a[i] = a[random];
            a[random] = temp;
        }
    }
    // insertion sort.
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
            if ((i+1) % 50 == 0)
                System.out.println();
        }
        System.out.println();
    }
    /**
     * Unit tests the {@code QuickSortImproved}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        final int SIZE = 1000;
        Integer[] testArr = new Integer[SIZE];
        Random r = new Random();
        for (int i = 0; i < SIZE; i++) {
            testArr[i] = r.nextInt(100);
        }
        System.out.println("The input test array is the following:");
        show(testArr);
        double time = quickSort(testArr);
        show(testArr);
        System.out.println("After calling function. (Finished in " + time + "seconds)");
    }
}
