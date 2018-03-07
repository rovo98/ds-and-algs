package com.rovo98.ds.searching;

/**
 * Jump search implement.
 *
 * Jump Search is a searching algorithm for sorted arrays. The basic idea
 * is to check fewer elements (than linear search) by jumping ahead by
 * fixed steps or skipping some elements in place of searching all elements.
 *
 *
 * For example, suppose we have an array arr[] of size n and block (to be jumped) size m.
 * Then we search at the indexes arr[0], arr[m], arr[2m], ..., arr[km] and so on.
 * Once we find the interval (arr[km] < x < arr[(k+1)m]), we perform a linear search operation
 * from the index km to find the element x.
 *
 *
 * @author rovo98
 * Date: 2/3/2018
 *
 *
 */
/*
Binary search is better than jump search, but jump search has an advantage
that we traverse back only once (Binary Search may require up to O(log n)) jumps,
consider a situation where the element to be search is the smallest element or
smaller than the smallest). So in a systems where jumping back is costly, we use
Jump Search.
 */
public class JumpSearch {
    /*
    Complexity Analysis:
        Time complexity: O(sqrt(n)).
        Space complexity: O(1).
     */
    // The time complexity of Jump search is between linear search and binary search.
    public static int search(int[] arr, int key) {
        int n = arr.length;
        // Finding the block size to be jumped.
        int block_size = (int) Math.floor(Math.sqrt(n));
        int step = block_size;
        // Finding the block where element is present (if it is present).
        int prev = 0;
        while (arr[Math.min(step, n) - 1] < key) {
            prev = step;
            step += block_size;
            if (prev >= n) {
                return -1;
            }
        }

        // Doing a linear search for x in block.
        // beginning with prev.
        while (arr[prev] < key) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }
        if (arr[prev] == key) {
            return prev;
        }

        return -1;
    }

    /**
     * Unit tests the {@code JumpSearch}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        int arr[] = {0, 1, 1, 2, 3, 5, 8, 13, 21,
                    34, 55, 89, 144, 233, 377, 610};
        int key = 55;
        System.out.println("The input test array is the following:");
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("Number " + key + " is at index " + search(arr, key) + ".");
    }
}
