package com.rovo98.ds.searching;

/**
 * BinarySearch implement.
 *
 *
 * Binary Search : Search a sorted array by repeatedly dividing the search interval in half.
 * Begin with an interval covering the whole array. If the value of the search key is less than
 * the item in the middle of the interval, narrow the interval to the lower half. Otherwise narrow
 * it to the upper half.
 * Repeatedly check until the value is found or the interval is empty.
 *
 * @author rovo98
 * Date: 2/3/2018
 */
public class BinarySearch {
    /**
     * Returns the index of the element to be searched.
     * @param arr array where need to be searched.
     * @param key the element to be searched.
     * @return the index of the element to be searched if key present in array;
     *          {@code -1} otherwise.
     */
    public static int search(int arr[], int key) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) { // the element only can be present in left subarray.
                hi = mid - 1;
            } else {                    // the element can only be present in right subarray.
                lo = mid + 1;
            }
        }
        // We reach here when element is not present in array.
        return -1;
    }

    /**
     * Unit tests the {@code BinarySearch}.
     * @param args arguments.
     */
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        System.out.println("The input test array is the following:");
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
        int x = 10;
        int index = search(arr, x);
        if (index != -1) {
            System.out.println("Element found at index " + index + ".");
        } else {
            System.out.println("Element not present.");
        }
    }
}
