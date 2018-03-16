package com.rovo98.ds.searching;

/**
 * linear search implement.
 *
 * @author rovo98
 * Date: 2/3/2018
 */
public class LinearSearch {

    public static int search(int[] arr, int key) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Unit tests the {@code LinearSearch}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        int[] testArr = {1, 10, 56, 45, 37, 90};
        System.out.println("The input test array is the following:");
        for (int item : testArr) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println("Number 90 is at index " + search(testArr, 90) + ".");
    }
}
