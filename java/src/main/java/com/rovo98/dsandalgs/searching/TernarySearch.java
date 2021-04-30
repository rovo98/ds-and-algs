package com.rovo98.dsandalgs.searching;

/**
 * TernarySearch implement.
 *
 * @author rovo98
 *
 * Date: 2/3/2018
 */
public class TernarySearch {
    /**
     * Returns the index of element to be searched.
     * @param arr array where need to be searched.
     * @param left the starting index of array.
     * @param right the ending index of array.
     * @param key the element to be searched.
     * @return the index of element to be searched if key is presented in array;
     *           {@code -1} otherwise.
     */
    public static int search(int arr[], int left, int right, int key) {
        if (left <= right) {
            int mid1 = left + (right - left) / 3;
            int mid2 = mid1 + (right - left) / 3;

            // If key is present at the mid1.
            if (arr[mid1] == key)
                return mid1;
            // If key is present at the mid2
            if (arr[mid2] == key)
                return mid2;

            // If key is present in left one-third
            if (arr[mid1] > key)
                return search(arr, left, mid1-1, key);
            // if key is present in right one-third.
            if (arr[mid2] < key)
                return search(arr, mid2+1, right, key);
            // If key is present in middle one-third.
            return search(arr, mid1+1, mid2-1, key);
        }
        // We reach here when element is not present in array.
        return -1;
    }

    /**
     * Unit tests the {@code TernarySearch}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 10, 40, 90};
        System.out.println("The input test array is the following:");
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
        int x = 40;
        int index = search(arr, 0, arr.length-1, x);
        if (index != -1) {
            System.out.println("Element found at index " + index + ".");
        } else {
            System.out.println("Element not found.");
        }
    }
}
