package com.rovo98.ds.list.algorithms;

import com.rovo98.ds.list.datastructures.LinkedList;

/**
 * Reversing linked list.
 *
 * @author rovo98
 * Date: 3/2/2018
 */
public class ReverseList<Item> {
    /**
     * Reverses linked list.
     * @param head the head node of the list.
     * @return the reversed list.
     */
    /*
    Time complexity: O(n).
    Space complexity: O(1).
     */
    public LinkedList<Item> reverseList(LinkedList<Item> head) {
        LinkedList<Item> rNode = head.next;
        while (rNode.next != null) {
            LinkedList<Item> node = rNode.next;
            rNode.next = node.next;
            node.next = head.next; // Notice: here is not : node.next = rNode;
            head.next = node;
        }
        return head;
    }

    // Approach two: Iteration
    /*
    Time complexity: O(n).
    Space complexity: O(1).
     */
    public LinkedList<Item> reverseListIteration(LinkedList<Item> head) {
        LinkedList<Item> first = head.next;
        LinkedList<Item> reverse = null;
        while (first != null) {
            LinkedList<Item> second = first.next;
            // insert the first node into the head of the list.
            first.next = reverse;
            reverse = first;
            // refresh the first node.
            first = second;
        }
        return reverse;
    }
    // Approach three: recursion
    /*
      Time complexity: O(log n).
      Space complexity: O(1).
     */
    public LinkedList<Item> reverseListRecursion(LinkedList<Item> first) {
        if (first == null) {
            return null;
        }
        if (first.next == null) {
            return first;
        }
        LinkedList<Item> second = first.next;
        LinkedList<Item> rest = reverseListRecursion(second);
        second.next = first;
        first.next = null;
        return rest;
    }

    // Derive the program to test the method above.
    public static void main(String[] args) {
        ReverseList<Integer> rl = new ReverseList<>();
        // test list 1 : 1->2->3->4->5->NULL.
        LinkedList<Integer> testList = new LinkedList<>();
        testList.addTail(1);
        testList.addTail(2);
        testList.addTail(3);
        testList.addTail(4);
        testList.addTail(5);
        // test list 2 : 0->1->2->3->4->NULL.
        LinkedList<Integer> testList2 = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            testList2.addTail(i);
        }
        // test list 3 : 0->1->2->3->4->5->NULL.
        LinkedList<Integer> testList3 = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            testList3.addTail(i);
        }
        System.out.println("The input test list  is :" + testList.toString());
        testList = rl.reverseList(testList);
        System.out.println("After calling the function, it becomes: " + testList.toString());
        System.out.println("The input test list 2 is : " + testList2.toString());
        testList2 = rl.reverseList(testList2);
        System.out.println("After calling the function, it becomes: " + testList2.toString());
        System.out.println("The input test list 3 is : " + testList3.toString());
        testList3 = rl.reverseListRecursion(testList3.next);
        System.out.println("After calling the function, it becomes: " + testList3.toString());
    }
}
