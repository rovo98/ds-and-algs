package com.rovo98.dsandalgs.sorting.exercises;

import java.util.Random;

/**
 * Exercises: 对原始归并排序的优化 ：
 *      1. 对小规模的子数组进行插入排序；
 *      2. 测试数组是否已经有序；
 *      3. 不将元素复制到辅助数组中。   没实现。。。
 *
 * @author rovo98
 * Date: 5/3/2018
 *
 */
public class MergeSortImproved {
    private static final int BOUND = 7;

    private MergeSortImproved() { }

    /**
     * MergeSort the unsorted array from up to bottom.
     * @param arr the array where need to be sorted.
     * @return      {@code the running time of this function}.
     */
    public static double mergeSortUB(Comparable[] arr) {
        Stopwatch sw = new Stopwatch();
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        sort(arr, aux, 0, n-1);

        assert isSorted(arr);
        return sw.elapsedTime();
    }

    /**
     * MergeSort recursively.
     * @param arr the array where to be sorted.
     * @param aux the auxiliary array.
     * @param lo  the starting index.
     * @param hi  the ending index.
     */
    @SuppressWarnings("unchecked")
    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        // Using insertionSort for small arrays.
        if (hi <= lo + BOUND) {
            insertionSort(arr, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid+1, hi);
        // Check if the array is already sorted.
        if (less(arr[mid], arr[mid+1]) || arr[mid].compareTo(arr[mid+1]) == 0) {
            System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
            return;
        }

        merge(arr, aux, lo, mid, hi);
    }

    /**
     * Merge two halves of the array where need to be sorted.
     * @param arr the array where need to be sorted.
     * @param aux the auxiliary array.
     * @param lo  the starting index
     * @param mid the middle point to divide array into two halves.
     * @param hi  the ending index.
     */
    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        // percondition: arr[lo .. mid] and arr[mid+1 .. hi] is sorted.
        assert isSorted(arr, lo, mid);
        assert isSorted(arr, mid+1, hi);

        int i = lo;
        int j = mid+1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               arr[k] = aux[j++];
            else if (j > hi)                arr[k] = aux[i++];
            else if (less(aux[j], aux[i]))  arr[k] = aux[j++];
            else                            arr[k] = aux[i++];
        }
        // post condition: arr[lo .. hi] is sorted.
        assert isSorted(arr, lo, hi);
    }
    /***********************************************************
     *  Helper for sorting.
     ***********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    private static boolean isSorted(Comparable[] arr) {
        return isSorted(arr, 0, arr.length-1);
    }
    private static boolean isSorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            if (less(arr[i], arr[i-1])) {
                return false;
            }
        }
        return true;
    }
    /**
     * Insertion sort the unsorted array arr[lo .. hi]
     * @param lo  the starting index.
     * @param hi  the ending index.
     * @param arr the array where need to be sorted.
     */
    private static void insertionSort(Comparable[] arr, int lo, int hi) {

        for (int i = lo+1; i <= hi; i++) {
            Comparable key = arr[i];
            int j = i - 1;
            while (j >= lo && less(key, arr[j])) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
    private static void show(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if ((i + 1) % 50 == 0)
                System.out.println();
        }
        System.out.println();
    }
    /**
     * Unit tests the {@code MergeSortImproved}.
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
        double time = mergeSortUB(testArr);
        show(testArr);
        System.out.println("After calling the function. (Finished in " + time + "seconds)");
    }
}
