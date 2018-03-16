package com.rovo98.ds.sorting.exercises;

import com.rovo98.ds.queue.datastructures.LinkedQueue;

/**
 * Merge two sorted queues.
 *
 * Returns the merged queue.
 *
 * @author rovo98
 *
 * Date: 3/3/2018
 * Exercise from algs4.
 */
public class MergeSortedQueue {
    /**
     * Merge two sorted queues.
     * @param q1 the first queue.
     * @param q2 the second queue.
     * @return  the merged queue.
     */
    public static LinkedQueue<Object> merge(LinkedQueue<Object> q1, LinkedQueue<Object> q2) {
        if (q1.size() == 0) {
            return q2;
        } else if (q2.size() == 0) {
            return q1;
        }

        LinkedQueue<Object> mergeQueue = new LinkedQueue<>();
        int i = 0;
        int j = 0;
        int size1 = q1.size();
        int size2 = q2.size();

        while (i < size1 && j < size2) {
            int flag = q1.peek().toString().compareTo(q2.peek().toString());
            if (flag < 0) {
                mergeQueue.enqueue(q1.dequeue());
                i++;
            } else {
                mergeQueue.enqueue(q2.dequeue());
                j++;
            }
        }

        while (i < size1) {
            mergeQueue.enqueue(q1.dequeue());
            i++;
        }
        while (j < size2) {
            mergeQueue.enqueue(q2.dequeue());
            j++;
        }

        return mergeQueue;
    }


    /**
     * Unit tests the method {@code merge}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        LinkedQueue<Object> q1 = new LinkedQueue<>();
        LinkedQueue<Object> q2 = new LinkedQueue<>();
        for (int i = 1; i < 6; i++) {
            q1.enqueue(i);
            q2.enqueue(i);
        }
        System.out.println("The two test sorted queues are " + q1.toString() + " and " + q2.toString() + ".");
        LinkedQueue<Object> result = merge(q1, q2);
        System.out.println("And the result is " + result.toString() + ".");
    }
}
