package com.rovo98.dsandalgs.searching;

/**
 * InterpolationSearch implement.
 *
 *
 * Given a sorted array of n uniformly distributed values arr[]
 *
 * The Interpolation Search is an improvement over Binary Search for instances,
 * where the values in a sorted array are uniformly distributed. Binary Search
 * always goes to middle element to check.
 * On the other hand interpolation search may go to different locations according
 * the value of key being searched.
 *
 * For example if the value of key is closer to the last element, interpolation
 * search is likely to start search toward the end side.
 *
 * @author rovo98
 *
 * Date: 2/3/2018
 */
// To find the position to be searched, it uses following formula:
/*
The idea of formula is to return higher value of pos
when element to be searched is closer to arr[hi]. And
smaller value when closer to arr[lo]

pos = lo + [(x-arr[lo])*(hi-lo) / (arr[hi]-arr[lo])

arr[] ==> Array where elements need to be searched
x     ==> Element to be searched
lo    ==> Starting index in arr[]
hi    ==> Ending index in arr[]
 */
public class InterpolationSearch {
    /**
     * Returns the index of the target element to be searched.
     * @param arr array where elements need to be searched.
     * @param key element to be searched.
     * @return   the index of the element to be searched;
     *          {@code -1} otherwise.
     */
    /*
    Complexity Analysis:
        Time complexity: O(log log n) if elements are uniformly distributed. O(n) worst case.
        Space complexity: O(1).
     */
    public static int search(int[] arr, int key) {
        // Find indexes of two corners
        int lo = 0;
        int hi = arr.length - 1;
        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (lo <= hi && key >= arr[lo] && key <= arr[hi]) {
            // Probing the position with keeping uniform distribution in mind.
            int pos = lo + ((key - arr[lo]) * (hi - lo) / (arr[hi] - arr[lo]));

            // Condition of target found
            if (arr[pos] == key) {
                return pos;
            } else if (arr[pos] > key) {
                hi = pos - 1;
            } else {
                lo = pos + 1;
            }
        }
        return -1;
    }

    /**
     * Unit tests the {@code InterpolationSearch}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        int arr[]  = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24,
                        33, 35, 42, 47};
        System.out.println("The input test array is the following:");
        for (int elem : arr) {
            System.out.print(elem + " ");
        }
        System.out.println();
        int key = 18;
        int index = search(arr, key);
        if (index != -1) {
            System.out.println("Element found at index " + index + ".");
        } else {
            System.out.println("Element not found!");
        }
    }
}
