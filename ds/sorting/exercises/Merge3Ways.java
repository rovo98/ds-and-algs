package com.rovo98.ds.sorting.exercises;

/**
 * Implement the mergeSort with merging three parts.
 *
 *
 * @author rovo98
 * Date: 3/3/2018
 *
 * Exercise from algs4.
 */
/*
Complexity Analysis:
    Time complexity: O(log3 n).
    Space complexity: O(n).

 Notice: In case of 2-way Merge sort we get the equation: T(n) = 2T(n/2) + O(n)
 Similarly, in case of 3-way Merge sort we get the equation: T(n) = 3T(n/3) + O(n).
 By solving it using Master method, we get its complexity as O(n log3 n).
 Although time complexity looks less compared to 2-way merge sort, the time taken
 actually may become higher because number of comparisons in merge function go higher.
 */
public class Merge3Ways {
    private Merge3Ways() { }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid1, int mid2, int hi) {
        assert isSorted(a, lo, mid1);
        assert isSorted(a, mid1+1, mid2);
        assert isSorted(a, mid2+1, hi);

        // copy a[lo .. hi] to aux[lo .. hi].
        for (int c = lo; c <= hi; c++) {
            aux[c] = a[c];
        }

        int i = lo;
        int j = mid1+1;
        int k = mid2+1;
        int index = lo;
        // Find the smallest element to be moved into a.
        while (i <= mid1 && j <= mid2 && k <= hi) {
            if (less(aux[i], aux[j])) {
                if (less(aux[i], aux[k])) {
                    a[index++] = aux[i++];
                } else {
                    a[index++] = aux[k++];
                }
            } else {
                if (less(aux[j], aux[k])) {
                    a[index++] = aux[j++];
                } else {
                    a[index++] = aux[k++];
                }
            }
        }
        // case where first and second ranges have remaining values.
        while (i <= mid1 && j <= mid2) {
            if (less(aux[i], aux[j])) {
                a[index++] = aux[i++];
            } else {
                a[index++] = aux[j++];
            }
        }
        // case where second and third ranges have remaining values.
        while (j <= mid2 && k <= hi) {
            if (less(aux[j], aux[k])) {
                a[index++] = aux[j++];
            } else {
                a[index++] = aux[k++];
            }
        }
        // case where first and third ranges have remaining values.
        while (i <= mid1 && k <= hi) {
            if (less(aux[i], aux[k])) {
                a[index++] = aux[i++];
            } else {
                a[index++] = aux[k++];
            }
        }
        // copying the remaining values from first ranges.
        while (i <= mid1) {
            a[index++] = aux[i++];
        }
        // copying the remaining values from second ranges.
        while (j <= mid2) {
            a[index++] = aux[j++];
        }
        // copying the remaining values from third ranges.
        while (k <= hi) {
            a[index++] = aux[k++];
        }

        assert isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // If array size is 1 then do nothing.
        if (hi - lo < 1) {
            return;
        }
        // Splitting array into 2 parts.
        int mid1 = lo + (hi - lo) / 3;
        int mid2 = mid1 + (hi - lo) / 3;

        // Sorting 3 arrays recursively.
        sort(a, aux, lo, mid1);
        sort(a, aux, mid1+1, mid2);
        sort(a, aux, mid2+1, hi);
        merge(a, aux, lo, mid1, mid2, hi);
    }
    /**********************************************************
     *  Helper for sorting.
     **********************************************************/
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /***********************************************************
     * Check if array is sorted.
     ***********************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++) {
            if (less(a[i], a[i-1]))
                return false;
        }
        return true;
    }

    /**
     * Unit tests the {@code Merge3Ways}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Integer[] testArr = new Integer[] {45, -2, -45, 78,
                                            30, -42, 10, 19, 73, 93};
        System.out.println("The input test array is the following:");
        for (int num : testArr) {
            System.out.print(num + " ");
        }
        System.out.println();
        sort(testArr);
        System.out.println("After 3 way merge sort:");
        for (int num : testArr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
