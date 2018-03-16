package com.rovo98.ds.queue.exercises.priorityqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT LinkedPQWithOrder, an implement of MaxPQ using linked-list.
 *
 * Data
 *      Node first
 *      int n                       the number of keys in maxPQ.
 * Operations
 *      LinkedPQWithOrder()         Initializes an empty maxPQ.
 *      LinkedPQWithOrder(Key[] v)  Initializes a maxPQ containing all keys from array {@code a}.
 *      boolean isEmpty()           Returns true if maxPQ is empty, otherwise false.
 *      int size()                  Returns the number of keys in maxPQ.
 *      void insert(Key v)          Add a key into maxPQ.
 *      Key max()                   Get the max key from the maxPQ.
 *      Key delMax()                Delete and return the max key from the maxPQ.
 *
 * endADT
 *
 * @author rovo98
 * Date: 11/3/2018
 *
 * learing from algs4.
 */
public class LinkedPQWithOrder<Key extends Comparable<Key>> implements MaxPQ<Key>, Iterable<Key> {
    private Node<Key> first;
    private int n;

    private class Node<Key> {
        Key val;
        Node<Key> next;
    }

    /**
     * Initializes an empty maxPQ.
     */
    public LinkedPQWithOrder() {
        first = null;
        n = 0;
    }

    /**
     * Initializes a maxPQ containing all keys from array {@code a}.
     * @param a the array where its keys are added to maxPQ.
     */
    public LinkedPQWithOrder(Key[] a) {
        for (Key key : a) {
            insert(key);
        }
    }

    /**
     * Add a key into maxPQ.
     * @param v the key to add to maxPQ.
     */
    /*
    Complexity Analysis:
        Time complexity: O(n).
        Space complexity: O(1).
     */
    @Override
    public void insert(Key v) {
        if (isEmpty() || v.compareTo(first.val) > 0) {
            Node<Key> oldFirst = first;
            first = new Node<>();
            first.val = v;
            first.next = oldFirst;
        } else {
            // use the idea like insertion sort.
            Node<Key> newNode = new Node<>();
            newNode.val = v;
            Node<Key> p = first;
            while (p.next != null && p.next.val.compareTo(v) > 0) {
                p = p.next;
            }
            // tail insertion to insert new node into list.
            newNode.next = p.next;
            p.next = newNode;
        }
        n++;
    }

    /**
     * Get the max key from maxPQ.
     * @return the max key in maxPQ.
     * @throws NoSuchElementException if maxPQ is empty.
     */
    /*
    Complexity Analysis:
        Time complexity: O(1).
        Space complexity: O(1).
     */
    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        return first.val;
    }

    /**
     * Delete and return the max key from maxPQ.
     * @return the max key in maxPQ.
     * @throws NoSuchElementException if maxPQ is empty.
     */
    /*
    Complexity Analysis:
        Time complexity: O(1).
        Space complexity: O(1).
     */
    @Override
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        Key del = first.val;
        first = first.next;
        n--;
        return del;
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
     * Returns the number of keys in maxPQ.
     * @return the number of keys in maxPQ.
     */
    @Override
    public int size() {
        return n;
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
     * Unit tests the {@code LinkedPQWithOrder} data structure.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new LinkedPQWithOrder<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(r.nextInt(1000));
        }
        System.out.println("The keys in the test maxPQ are the following:");
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
