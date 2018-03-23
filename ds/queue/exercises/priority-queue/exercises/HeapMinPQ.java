package com.rovo98.ds.queue.exercises.priorityqueue.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT HeapMinPQ, an implementation of minPQ.
 *
 * Data:
 *      Key[] heap
 *      int n
 * Operations:
 *      HeapMinPQ()             Initializes an empty priority queue.
 *      HeapMinPQ(Key[] a)      Initializes a priority queue containing all keys from array {@code a}.
 *      void insert(Key key)    Add a key into priority queue.
 *      Key min()               Returns a minimum key from priority queue.
 *      Key delMin()            Remove a minimum key and returns it.
 *      boolean isEmpty()       Returns true if priority is empty.
 *      int size()              Returns the number of keys in priority queue.
 * endADT
 * @author rovo98
 * Date: 2018.3.22.
 * learning from algs4.
 */
public class HeapMinPQ<Key extends Comparable<Key>> implements MinPQ<Key> {
    private Key[] heap;
    private int n;

    /**
     * Initializes an empty priority queue.
     */
    @SuppressWarnings("unchecked")
    public HeapMinPQ() {
        heap = (Key[]) new Comparable[2];
        n = 0;
    }

    /**
     * Initializes a priority queue containing all keys from array {@code a}
     * @param a the array where its keys will be added to queue.
     */
    @SuppressWarnings("unchecked")
    public HeapMinPQ(Key[] a) {
        heap = (Key[]) new Comparable[a.length+1];
        for (int i = 1; i < heap.length; i++) {
            heap[i] = a[i-1];
        }
        n = a.length + 1;
    }

    /**
     * Resize the queue with a new size.
     * @param max the new size of the queue.
     */
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max+1];
        for (int i = 1; i <= n; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    /**
     * Add a key into priority queue.
     * @param key the key to add to queue.
     */
    @Override
    public void insert(Key key) {
        if (n == heap.length - 1) {
            resize((heap.length - 1) * 2);
        }
        heap[++n] = key;
        swim(n);
    }

    /**
     * Returns a minimum key.
     * @return a minimum key.
     * @throws NoSuchElementException if minPQ is empty.
     */
    @Override
    public Key min() {
        if (n == 0) {
            throw new NoSuchElementException("minPQ underflow!");
        }
        return heap[1];
    }

    /**
     * Remove a minimum key and return it.
     * @return a minimum key.
     * @throws NoSuchElementException if minPQ is empty.
     */
    @Override
    public Key delMin() {
        if (n == 0) {
            throw new NoSuchElementException("minPQ underflow!");
        }
        Key min = heap[1];
        exch(1, n--);
        heap[n+1] = null;
        sink(1);
        if (n == ((heap.length - 1) / 4)) {
            resize((heap.length - 1) / 2);
        }
        return min;
    }
    /****************************************
     * Heap helper functions.
     ***************************************/
    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j+1, j)) {
                j++;
            }
            if (less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /******************************************
     * General helper functions.
     ******************************************/
    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }
    private void exch(int i, int j) {
        Key temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    /**
     * Returns true if priority queue is empty.
     * @return {@code true} if this queue is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys in queue.
     * @return the number of keys in queue.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns an iterator that iterates over keys in queue.
     * @return an iterator that iterates over keys in queue.
     */
    @Override
    public Iterator<Key> iterator() {
        return new MinPQIterator();
    }
    private class MinPQIterator implements Iterator<Key> {
        private int i = n;
        public boolean hasNext() {
            return i > 0;
        }
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return heap[i--];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests the {@code HeapMinPQ} data type.
     * @param args command arguments.
     */
    public static void main(String[] args) {
        MinPQ<Integer> pq = new HeapMinPQ<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            pq.insert(r.nextInt(100));
//            System.out.println(pq.min());
        }
        System.out.println("The input test pq :");
        for (int key : pq) {
            System.out.print(key + " ");
        }
        System.out.println();
        System.out.println("The priorities :");
        while (!pq.isEmpty()) {
            System.out.print(pq.delMin() + " ");
        }
        System.out.println("\nThe length now is :" + pq.size());
    }
}
