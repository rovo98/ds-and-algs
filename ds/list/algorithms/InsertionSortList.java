package com.rovo98.ds.list.algorithms;

import com.rovo98.ds.list.datastructures.LinkedList;

/**
 * Use insertion sort to sort a single-linked-list with ascending order.
 *
 * @author rovo98
 * Date: 2/2/2018
 */
public class InsertionSortList<Item> {
    // This algorithm's time complexity is O(n^2) and its
    // space complexity is O(1).
    public  LinkedList<Item> listInsertionSort(LinkedList<Item> head) {
        // Divide the list into two parts, one represents the unsorted list
        // and one represents the sorted list.
        LinkedList<Item> unSorted = head.next.next;
        LinkedList<Item> sorted = head;
        LinkedList<Item> remain;
        LinkedList<Item> pNode;
        // Initial the sorted list.
        sorted.next.next = null;

        while (unSorted != null) {
            pNode = sorted;
            remain = unSorted.next;
            // To find the right position to insert into the sorted list.
            while (pNode.next != null && pNode.next.compareTo(unSorted) < 0) {
                pNode = pNode.next;
            }
            // insert the new node.
            unSorted.next = pNode.next;
            pNode.next = unSorted;
            // refresh the unsorted list.
            unSorted = remain;
        }
        // return the sorted list.
        return sorted;
    }

    /**
     * Unit tests the {@code Insertion} method.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        InsertionSortList<Integer> isld = new InsertionSortList<>();
        // test list : 2->1->5->1->9->3->NULL.
        LinkedList<Integer> testList = new LinkedList<>();
        testList.addTail(2);
        testList.addTail(1);
        testList.addTail(5);
        testList.addTail(1);
        testList.addTail(9);
        testList.addTail(3);
        System.out.println("The test list is the following:");
        System.out.println(testList.toString());
        testList = isld.listInsertionSort(testList);
        System.out.println("After calling the insertSort function, it becomes:");
        System.out.println(testList.toString());
    }
}
