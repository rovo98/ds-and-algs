package com.rovo98.ds.searching;

/**
 * ExponentialSearch implement.
 * <p>
 * Exponential Search involves two steps:<br />
 * 1. Find the range where element is present.<br />
 * 2. Do Binary Search in above found range.<br />
 * <p>
 * <p>
 * How to find the range where element may be present?<br>
 * The idea is to start with subArray size 1 compare its last element with x, then try
 * size 2, then 4 and so on until last element of a subArray is not greater.
 * <p>
 * Once we find an index i (after repeated doubling of i), we know that the element must
 * be present between i/2 and i.
 *
 * @author rovo98
 * Date: 2/3/2018
 */
/*
Applications of Exponential Search:
     1. Exponential Binary search is particularly useful for unbounded searched, where
     size of array is infinite.
     2. It works better than Binary Search for bounded arrays also when the element
     to be searched is closer to the first element.
 */
public class ExponentialSearch {

    /**
     * private empty constructor
     * <p>
     * this class can not be instanced.
     */
    private ExponentialSearch() {
    }

    /**
     * Returns the index of element to be searched.
     *
     * @param arr array where need to be searched.
     * @param key the element to be searched.
     * @return the index of element to be searched if element present;
     * {@code -1} otherwise.
     */
    /*
    Complexity Analysis:
        Time complexity: O(log n).
        Space complexity: O(1).
     */
    public static int search(int[] arr, int key) {
        int n = arr.length;
        // If x is present at first location itself
        if (arr[0] == key) {
            return 0;
        }
        // Find range for binary search by repeated doubling.
        int i = 1;
        while (i < n && arr[i] < key) {
            i = i * 2;
        }
        // Call binary search for the found range.
        return binarySearch(arr, i / 2, Math.min(i, n), key);
    }

    // Binary search.
    private static int binarySearch(int[] arr, int lo, int hi, int key) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == key)
                return mid;
            else if (arr[mid] > key)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return -1;
    }

    /**
     * Unit tests the {@code ExponentialSearch}.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        System.out.println("The input test array is the following:");
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
        int x = 40;
        int index = search(arr, x);
        if (index != -1) {
            System.out.println("Element found at index " + index + ".");
        } else {
            System.out.println("Element not found.");
        }
    }
}
