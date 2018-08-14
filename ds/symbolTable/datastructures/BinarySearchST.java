package com.rovo98.ds.symbolTable.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT BinarySearchST, an implementation of ST(Symbol Table) using resizing arrays and binarySearch algorithm.
 *
 * Data:
 *          Key[] keys
 *          Value[] values
 *          int n                           The number of keys in symbol table.
 * Operations:
 *          BinarySearchST()                Initializes an empty symbol table.
 *          boolean isEmpty()               Returns true if symbol table is empty, otherwise false.
 *          boolean contains(Key key)       Returns true if {@code key} in symbol table, {@code false} otherwise.
 *          int size()                      Returns the number of keys in symbol table.
 *          void put(Key key, Value value)  Add a key-value into symbol table, update value of a key-value if {@code key} already in table.
 *          void delete(Key key)            Remove a key-value from symbol table.
 *          Value get(key key)              Get the value of a key-value which key is {@code key}, returns null if {@code key} not in table.
 *          Iterable<Key> keys()            Returns an iterable obj that iterates over keys in symbol table.
 * endADT
 * @author rovo98
 * Date: 2018.3.25
 *
 * Learning from algs4.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;

    /**
     * Initializes an empty symbol table.
     */
    @SuppressWarnings("unchecked")
    public BinarySearchST() {
        keys = (Key[]) new Comparable[1];
        values= (Value[]) new Object[1];
        n = 0;
    }

    /**
     * Resizing the keys and values arrays.
     * @param max the new size of the resizing arrays.
     */
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        Key[] keys_copy = (Key[]) new Comparable[max];
        Value[] values_copy = (Value[]) new Object[max];
        for (int i = 0; i < n; i++) {
            keys_copy[i] = keys[i];
            values_copy[i] = values[i];
        }
        keys = keys_copy;
        values = values_copy;
    }

    /**
     * Returns the value of the {@code key}
     * @param key the key to be searched.
     * @return  the value of the {@code key} if key in symbol table;
     *          {@code null} otherwise.
     */
    /*
    Time complexity: O(log n).
     */
    @Override
    public Value get(Key key) {
        int index = binarySearch(key);
        if (index < 0) {
            return null;
        }
        return values[index];
    }

    /**
     * Add a key-value into symbol table
     * @param key the key of key-value to add to symbol table;
     * @param value the value of key-value to add to symbol table.
     */
    /*
    Complexity Analysis:
        Time complexity: O(n).
        Space complexity: O(1).
     */
    @Override
    public void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }
        int index = binarySearch(key);
        if (index > -1) { // if the key is already in keys, update its value.
            values[index] = value;
            return;
        }
        if (n == keys.length) {
            resize(keys.length * 2);
        }
        // use a idea of insertion sort the insert the key to keys[0, .. n-1]
        int j = n - 1;
        for (; j >= 0 && key.compareTo(keys[j]) < 0; j--) {
            keys[j+1] = keys[j];
            values[j+1] = values[j];
        }
        keys[j+1] = key;
        values[j+1] = value;
        n++;
    }

    /**
     * Remove a key-value from symbol table.
     * @param key the key of a key-value to remove.
     * @throws NoSuchElementException if {@code key} not in symbol table.
     */
    // Time  complexity: O(n).
    @Override
    public void delete(Key key) {
        if (!contains(key)) {
            throw new NoSuchElementException("The key in not in symbol table.");
        }
        int index = binarySearch(key);
        keys[index] = null;
        values[index] = null;
        for (int i = index; i < n; i++) {
            keys[i] = keys[i+1];
            values[i] = values[i+1];
        }
        n--;
        if (n == keys.length / 4) {
            resize(keys.length / 2);
        }
    }
    /**
     * Returns true if {@code key} in symbol table.
     * @param key the key to be searched.
     * @return  {@code true} if key in symbol table;
     *          {@code false} otherwise.
     */
    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Returns true if symbol table is empty
     * @return {@code true} if symbol table if empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys in symbol table.
     * @return the number of keys in symbol table.
     */
    @Override
    public int size() {
        return n;
    }
    /**************************************
     * BinarySearch tool.
     **************************************/
    private int binarySearch(Key key) {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (keys[mid].compareTo(key) == 0) {
                return mid;
            } else if (keys[mid].compareTo(key) > 0) {
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Returns an iterable obj that iterates over keys in symbol table in ascending order.
     * @return an iterable obj that iterates over keys in symbol table in ascending order.
     */
    @Override
    public Iterable<Key> keys() {
        return new BinarySearchSTIterator();
    }
    private class BinarySearchSTIterator implements Iterable<Key> {
            public Iterator<Key> iterator() {
                return new STIterator();
            }
            // A symbol table iterator that iterates over keys in table.
            private class STIterator implements Iterator<Key> {
                private int i = 0;
                public boolean hasNext() {
                    return i < n;
                }
                public Key next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return keys[i++];
                }
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            }
    }

    /**
     * Unit tests the {@code BinarySearchST} data type.
     * @param args command arguments.
     */
    public static void main(String[] args) {
        ST<String, Integer> st = new BinarySearchST<>();
        String[] test = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < test.length; i++) {
            st.put(test[i], i);
        }
        System.out.println("The input test BinarySearchST is the following:");
        for (String key : st.keys()) {
            System.out.println(key + " -> " + st.get(key) + " ");
        }
        System.out.println("The number of keys is " + st.size() + ".");
    }
}
