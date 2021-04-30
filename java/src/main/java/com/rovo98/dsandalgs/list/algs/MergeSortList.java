package com.rovo98.dsandalgs.list.algs;

import com.rovo98.dsandalgs.list.ds.LinkedList;

import java.util.Random;

/**
 * Use divide and conquer thoughts to sort the linked list.
 *
 * @author rovo98
 * Date: 2/2/2018
 */
public class MergeSortList<Item> {
    /**
     * Merge two lists.
     *
     * @param l1 list 1.
     * @param l2 list 2.
     * @return the merged linked list.
     */
    // This method's time complexity is O(n).
    public LinkedList<Item> merge(LinkedList<Item> l1, LinkedList<Item> l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        LinkedList<Item> mergedList = new LinkedList<>();
        LinkedList<Item> mNode = mergedList;
        while (l1 != null && l2 != null) {
            if (l1.compareTo(l2) > 0) {
                mNode.next = l2;
                l2 = l2.next;
            } else {
                mNode.next = l1;
                l1 = l1.next;
            }
            mNode = mNode.next;
        }
        if (l1 != null) {
            mNode.next = l1;
        }
        if (l2 != null) {
            mNode.next = l2;
        }
        return mergedList.next;
    }

    // This method's time complexity is O(n log n).
    public LinkedList<Item> sortList(LinkedList<Item> head) {
        if (head == null || head.next == null) {
            return head;
        }
        // using fast-slow pointers to divide the list into two parts. (two pointers)
        LinkedList<Item> sNode = head;
        LinkedList<Item> fNode = head;
        while (fNode.next != null && fNode.next.next != null) {
            fNode = fNode.next.next;
            sNode = sNode.next;
        }
        fNode = sNode.next;
        sNode.next = null;

        LinkedList<Item> l1 = sortList(head);
        LinkedList<Item> l2 = sortList(fNode);

        return merge(l1, l2);
    }

    /**
     * Unit tests the {@code sortList} method.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        MergeSortList<Integer> msl = new MergeSortList<>();
        // test list : random test list having nodes which value in range of [0, 99].
        LinkedList<Integer> testList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            testList.addTail(new Random().nextInt(100));
        }
        System.out.println("The input test list is the following:");
        System.out.println(testList.toString());
        testList.next = msl.sortList(testList.next);
        System.out.println("\nAfter calling the function, it becomes:");
        System.out.println(testList.toString());
    }
}
