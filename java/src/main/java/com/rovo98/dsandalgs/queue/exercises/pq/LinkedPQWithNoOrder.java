package com.rovo98.dsandalgs.queue.exercises.pq;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT LinkedPQWithNoOrder, an implement of MaxPQ using linked-list.
 *
 * Data:
 *      Node    first
 *      int n                           the number of keys in maxPQ.
 *      LinkedPQWithNoOrder()           Initializes an empty maxPQ.
 *      LinkedPQWithNoOrder(Key[] a)    Initializes a maxPQ containing keys from array {@code a}.
 *      boolean isEmpty()               Returns true if maxPQ is empty, otherwise false.
 *      int size()                      Returns the number of keys in maxPQ.
 *      void insert(Key v)              Add a key into maxPQ.
 *      Key max()                       Get the max key from the maxPQ.
 *      Key delMax()                    Delete and return the max key from the maxPQ.
 *
 * endADT
 *
 * @author rovo98
 * Date: 11/3/2018
 * learing from algs4.
 */

public class LinkedPQWithNoOrder<Key extends Comparable<Key>> implements MaxPQ<Key>, Iterable<Key> {
    private Node<Key> first;
    private int n;

    private class Node<Item> {
        Item val;
        Node<Item> next;
    }

    /**
     * Initializes an empty maxPQ.
     */
    public LinkedPQWithNoOrder() {
        first = null;
        n = 0;
    }

    /**
     * Initializes an empty maxPQ containing keys from array {@code a}.
     * @param a the array where its keys are added to maxPQ.
     */
    public LinkedPQWithNoOrder(Key[] a) {
        for (Key key : a) {
            insert(key);
        }
    }

    /**
     * Insert a key into maxPQ.
     * @param v the key to add to maxPQ.
     */
    // Time complexity: O(1).
    @Override
    public void insert(Key v) {
        Node<Key> oldFirst = first;
        first = new Node<>();
        first.val = v;
        first.next = oldFirst;
        n++;
    }

    /**
     * Get the max key from the maxPQ.
     * @return the max key in the maxPQ.
     * @throws NoSuchElementException if maxPQ  is empty.
     */
    /*
    Complexity Analysis:
        Time complexity: O(n).
        Space complexity: O(1).
     */
    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        Key max = first.val;
        Node<Key> p = first.next;

        // do a loop to find the maximum key.
        while (p != null) {
            if (p.val.compareTo(max) > 0) {
                max = p.val;
            }
        }
        return max;
    }

    /**
     * Delete and return the max key in maxPQ.
     * @return the max key in maxPQ.
     * @throws NoSuchElementException if maxPQ is empty.
     */
    /*
    Complexity Analysis:
        Time complexity: O(n).
        Space complexity: O(1).
     */
    @Override
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        Key max = first.val;
        Node<Key> p = first;
        int index = 0;
        int count = 0;
        // do a loop to find the index of the maximum key.
        while (p != null) {
            if (p.val.compareTo(max) >= 0) {
                index = count;
                max = p.val;
            }
            p = p.next;
            count++;
        }

        Key del;
        if (index == 0) {       // if the first key is the maximum.
            del = first.val;
            first = first.next;
        } else {                // delete and return the maximum key.
            int c = 1;
            p = first;
            while (c < index) {
                p = p.next;
                c++;
            }
            del = p.next.val;
            p.next = p.next.next;
        }
        n--;
        return del;
    }
    /**
     * Returns the number of keys in maxPQ.
     * @return the number of keys in maxPQ.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns true if maxPQ is empty.
     * @return  {@code true} if maxPQ is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns an iterator that iterates all keys in maxPQ.
     * @return an iterator that iterates all keys in maxPQ.
     */
    @Override
    public Iterator<Key> iterator() {
        return new LinkedPQIterator<>(first);
    }
    private class LinkedPQIterator<Key> implements Iterator<Key> {
        private Node<Key> current;
        private LinkedPQIterator(Node<Key> first) {
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Key del = current.val;
            current = current.next;
            return del;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests the {@code LinkedPQWithNoOrder} data structure.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new LinkedPQWithNoOrder<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(r.nextInt(100));
        }
        System.out.println("The keys in the maxPQ are the following:");
        for (int key : maxPQ) {
            System.out.print(key + " ");
        }
        System.out.println();
        System.out.println("The priorities from the test PQ are the following:");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.delMax() + " ");
        }
        System.out.println();
    }
}
