package com.rovo98.dsandalgs.queue.exercises.pq;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT HeapMaxPQ, an implementation of MaxPQ using heap.
 *
 * Data:
 *      Key[] heap;
 *      int n                    the number of keys in MaxPQ.
 * Operations:
 *      HeapMaxPQ()              Initializes an empty MaxPQ.
 *      HeapMaxPQ(Key[] a)       Initializes a MaxPQ containing all keys from array {@code a}.
 *      boolean isEmpty()        Returns true if MaxPQ is empty, otherwise false.
 *      int size()               Returns the number of keys in MaxPQ.
 *      void insert(Key v)       Insert a key into MaxPQ.
 *      Key max()                Get the maximum key from the MaxPQ.
 *      Key delMax()             Delete and return the maximum key from MaxPQ.
 *      void resize(int max)     Resize the array with the new size.
 *      void swim(int k)         Adjusting the key at k to the right place.
 *      void sink(int k)         Adjusting the key at k to the right place.
 * endADT
 *
 * @author rovo98
 * Date: 14/3/2018
 * learning from algs4.
 */
public class HeapMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] heap;
    private int n;

    /**
     * Initializes an empty MaxPQ.
     */
    @SuppressWarnings("unchecked")
    public HeapMaxPQ() {
        heap = (Key[]) new Comparable[2];
        n = 0;
    }

    /**
     * Initializes a MaxPQ containing all keys from array {@code a}.
     * @param a the array where its elements are added to MaxPQ.
     */
    @SuppressWarnings("unchecked")
    public HeapMaxPQ(Key[] a) {
        heap = (Key[]) new Comparable[a.length+1];
        for (int i = 0; i < a.length; i++) {
            heap[i+1] = a[i];
        }
        n = a.length + 1;
    }

    /**
     * Resize the heap with a brand new size.
     * @param max the new size of the heap.
     */
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max+1];
        for (int i = 1; i <= n; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    /******************************************
     * Helper for adjust the heap.
     ******************************************/
    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    /******************************************
     *  Helper for adjust the heap.
     ******************************************/
    private void exch(int i, int j) {
        Key temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Adjusting the heap to let the new inserted key
     * swim to the right place to keep the heap sorted.
     * @param k the index of the inserted key.
     */
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k / 2;
        }
    }

    /**
     * Adjusting the heap to let the new root key
     * sink to the right place to keep the heap sorted.
     * @param k the index of the new root key.
     */
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j+1))
                j++;
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * Insert a key into MaxPQ.
     * @param v the key to add to MaxPQ.
     */
    /*
    Complexity Analysis:
        Time complexity: O(log n)
        Space complexity: O(1).
     */
    @Override
    public void insert(Key v) {
        if (n == heap.length-1) {
            resize((heap.length-1) * 2);
        }
        heap[++n] = v;
        swim(n);
    }

    /**
     * Returns the maximum key in MaxPQ.
     * @return the maximum key in MaxPQ.
     */
    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        return heap[n];
    }

    /**
     * Delete and return the maximum key from MaxPQ.
     * @return the maximum key from MaxPQ.
     */
    @Override
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        Key max = heap[1];
        exch(1, n--);
        heap[n+1] = null;
        sink(1);
        if (n == (heap.length-1) / 4) {
            resize((heap.length-1) / 2);
        }
        return max;
    }

    /**
     * Returns true if MaxPQ is empty.
     * @return  {@code true} if MaxPQ is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys in MaxPQ.
     * @return the number of keys in MaxPq.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns an iterator that iterates all keys in MaxPQ.
     * @return an iterator that iterates all keys in MaxPQ.
     */
    @Override
    public Iterator<Key> iterator() {
        return new HeapMaxPQIterator();
    }
    private class HeapMaxPQIterator implements Iterator<Key> {
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
            return heap[i--];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests the {@code HeapMaxPQ} data structure.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new HeapMaxPQ<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(r.nextInt(100));
            System.out.print(maxPQ.max() + " ");
        }
        System.out.println();
        System.out.println("The keys in test MaxPQ are following:");
        for (int key : maxPQ) {
            System.out.print(key + " ");
        }
        System.out.println();
        System.out.println("The priorities from test MaxPQ are the following:");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.delMax() + " ");
        }
        System.out.println();
        System.out.println("The length of maxPQ is now : " + maxPQ.size());
    }
}
