package com.rovo98.ds.sorting.exercises;

/**
 * Using merge sort from up to bottom to sort the "E A S Y Q U E S T I O N"
 *
 * @author rovo98
 * Date: 3/3/2018
 *
 * Exercise from algs4.
 */
public class UBMergeSortDemo {
    private UBMergeSortDemo() { }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <=hi; i++) {
            if (less(a[i], a[i-1]))
                return false;
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    public static void sort(Comparable[] a) {
        int n = a.length;
        // Allocate a auxiliary array for copying array.
        Comparable[] aux = new Comparable[n];
        upSort(a, aux, 0, n-1);
        assert isSorted(a);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subArrays.
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
        // post condition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }

    /**
     * MergeSort a[lo .. hi] using auxiliary array aux[lo .. hi]
     * @param a the unsorted array.
     * @param aux the auxiliary array.
     * @param lo the starting index.
     * @param hi the ending index.
     */
    private static void upSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        upSort(a, aux, lo, mid);
        upSort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    // Driver the program to solve the problem above.
    public static void main(String[] args) {
        Character[] characters = {'E', 'A', 'S', 'Y', 'Q', 'U', 'E', 'S', 'T', 'I', 'O', 'N'};
        System.out.println("The input characters is the following:");
        for (char c : characters)
            System.out.print(c + " ");
        System.out.println();
        sort(characters);
        System.out.println("After calling the function, it becomes: ");
        for (char c : characters)
            System.out.print(c + " ");
        System.out.println();
    }
}
