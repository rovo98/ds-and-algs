package com.rovo98.dsandalgs.queue.exercises.pq;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT ArrayPQWithOrder, an implement of MaxPQ. This implement is based on ArrayStack.
 *
 * Data:
 *      Key[] data;
 *      int n                       the number of keys in maxPQ.
 *      ArrayPQWithOrder()          Initializes an empty maxPQ.
 *      ArrayPQWithOrder(Key[] a)   Initialize a maxPQ containing all keys from array {@code a}.
 *      boolean isEmpty()           Returns true if maxPQ is empty, otherwise false.
 *      int size()                  Returns the number of keys in maxPQ.
 *      Key max()                   Get the max key from the maxPQ.
 *      Key delMax()                Delete and return the max key in the maxPQ.
 *      void insert(Key v)          Insert a key into maxPQ.
 * endADT
 *
 * @author rovo98
 * Date : 11/3/2018
 * learning from algs4.
 */

public class ArrayPQWithOrder<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] data;
    private int n;

    /**
     * Initializes an empty maxPQ.
     */
    @SuppressWarnings("unchecked")
    public ArrayPQWithOrder() {
        data = (Key[]) new Comparable[1];
        n = 0;
    }

    /**
     * Initializes a maxPQ containing all keys in array {@code a}.
     * @param a the array where its elements are added to maxPQ.
     */
    public ArrayPQWithOrder(Key[] a) {
        data = a;
        n = a.length;
    }

    /**
     * Resize the PQ with a new size {@code max}.
     * @param max the size of the resized PQ.
     */
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i < n; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    /**
     * Insert a key into maxPQ.
     * @param v the key to insert into maxPQ.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    @Override
    public void insert(Key v) {
        if (n == data.length) {
            resize(data.length * 2);
        }

        // use the idea of insertion sort.
        int i = n - 1;
        while (i >= 0 && v.compareTo(data[i]) < 0) {
            data[i+1] = data[i];
            i--;
        }
        data[i+1] = v;

        n++;
    }

    /**
     * Get the max key from the maxPQ.
     * @return the max key got from the maxPQ.
     * @throws NoSuchElementException if maxPQ is empty.
     */
    // Time complexity : O(1).
    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        return data[n-1];
    }

    /**
     * Delete and return max key in the maxPQ.
     * @return the max key got from the maxPQ.
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
        Key del = data[--n];
        data[n] = null;
        if (n > 0 && n == data.length / 4) {
            resize(data.length / 2);
        }
        return del;
    }
    /**
     * Returns true if maxPQ is empty.
     * @return {@code true} if maxPQ is empty;
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
     * Returns an iterator that iterates all the keys in maxPQ.
     * @return an iterator that iterates all the keys in maxPQ.
     */
    @Override
    public Iterator<Key> iterator() {
        return new ArrayPQIterator();
    }
    private class ArrayPQIterator implements Iterator<Key> {
        private int i = n;
        @Override
        public boolean hasNext() {
            return i > 0;
        }
        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[--i];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests the {@code ArrayPQWithOrder} data structure.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new ArrayPQWithOrder<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(r.nextInt(1000));
        }
        System.out.println("The keys in the maxPQ are the following:");
        for (int key : maxPQ) {
            System.out.print(key + " ");
        }
        System.out.println();
        System.out.println("The priorities from test PQ are the following:");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.delMax() + " ");
        }
        System.out.println();
    }
}
