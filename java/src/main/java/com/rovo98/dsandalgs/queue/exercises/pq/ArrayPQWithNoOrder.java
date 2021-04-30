package com.rovo98.dsandalgs.queue.exercises.pq;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT ArrayPQWithNoOrder, an implement of maxPQ. This implement is based on ArrayStack.
 *
 * Data:
 *     Key[] data
 *     int n                        the number of the keys in maxPQ.
 *
 * Operations
 *      ArrayPQWithNoOrder()        Initializes an empty maxPQ.
 *      ArrayPQWithNoOrder(Key[] a) Initializes a maxPQ containing all keys in array {@code a}.
 *      boolean isEmpty()           Returns true if maxPQ is empty, otherwise false.
 *      int size()                  Returns the number of keys in maxPQ.
 *      void insert(Key v)          Add a key into maxPQ.
 *      Key max()                   Get the max key from the maxPQ.
 *      Key delMax()                Delete and return the max key in the maxPQ.
 *
 * endADT
 *
 * @author rovo98
 * Date: 11/3/2018
 * learing from algs4.
 */

public class ArrayPQWithNoOrder<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] data;
    private int n;

    /**
     * create an empty MaxPQ.
     */
    @SuppressWarnings("unchecked")
    public ArrayPQWithNoOrder() {
        data = (Key[]) new Comparable[1];
        n = 0;
    }

    /**
     * create a MaxPQ containing all the elements in array {@code a}.
     * @param a the array where its elements are added to MaxPQ.
     */
    @SuppressWarnings("unchecked")
    public ArrayPQWithNoOrder(Key[] a) {
       data = a;
       n = a.length;
    }

    /**
     * resize the PQ with the new size {@code max}.
     * @param max the size of the new PQ.
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
     * add a key into MaxPQ.
     * @param v the key to add to the MaxPQ.
     */
    @Override
    public void insert(Key v) {
        if (n == data.length) {
            resize(2 * data.length);
        }
        data[n++] = v;
    }

    /**
     * get the max key from the MaxPQ.
     *
     * @return the max key from the MaxPQ.
     * @throws java.util.NoSuchElementException if MaxPQ is empty.
     */
    @Override
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (data[i].compareTo(data[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return data[maxIndex];
    }

    /**
     * delete and return the max key in the maxPQ.
     * @return  the max key in the maxPQ.
     * @throws NoSuchElementException if maxPQ is empty.
     */
    @Override
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The maxPQ is empty.");
        }
        // use a idea of selection sort to find the index of maximum key.
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (data[i].compareTo(data[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        // exchange the max key with corner element.
        Key temp = data[n-1];
        data[n-1] = data[maxIndex];
        data[maxIndex] = temp;

        Key del = data[--n]; // the max key.
        data[n] = null;      // release the corner obj.
        if (n > 0 && n == data.length/4) {
            resize(data.length / 2);
        }
        return del;
    }
    /**
     * Return true if MaxPQ is empty.
     * @return {@code true} if MaxPQ is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys in MaxPQ.
     * @return the number of keys in MaxPQ.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns a iterator that iterates all the keys in maxPQ.
     * @return a iterator that iterates all the keys in maxPQ.
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
     * Unit tests the {@code maxPQ} data structure.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new ArrayPQWithNoOrder<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            maxPQ.insert(r.nextInt(100));
        }
        System.out.println("The keys from the test maxPQ are the following:");
        for (int key : maxPQ) {
            System.out.print(key + " ");
        }
        System.out.println();
        System.out.println("Do a loop deleting the max keys from the maxPQ:");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.delMax() + " ");
        }
        System.out.println();
        System.out.println("The sequence above is the priorities sorted.");
    }
}
