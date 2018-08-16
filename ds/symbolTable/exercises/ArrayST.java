package com.rovo98.ds.symbolTable.exercises;

import com.rovo98.ds.symbolTable.datastructures.ST;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author : rovo98
 * date : 2018/8/16
 *
 * An implementation of interface {@code ST} using Resizing arrays without BinarySearch algorithm.
 *
 * This is a exercise to use array to implement the basical API of the {@code ST}.
 *
 * ArrayST()                            Initializes an empty symbol table.
 * void put(Key key, Value val)    Add a key-value into table, delete key if val is null.
 * Value get(Key key)              Get the value of the {@code key}, returns null if key doesn't exists.
 * void delete(Key key)            Delete the key-value
 * boolean contains(Key key)       Returns true if {@code key} in symbol table.
 * boolean isEmpty()               Returns true if symbol table if empty, otherwise false.
 * int size()                      Returns the number of key-value in symbol table.
 * Iterable<Key> keys()            Returns the all keys in symbol table.
 *
 */
public class ArrayST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Key[] keys;         // array to store the keys.
    private Value[] values;     // array to store the values.
    private int n;              // the number of the key-value pair in the table.

    /**
     * Initialize a empty ArrayST.
     */
    @SuppressWarnings("unchecked")
    public ArrayST() {
        this.keys = (Key[]) new Comparable[1];
        this.values = (Value[]) new Object[1];
        this.n = 0;
    }

    /**
     * A tool function for resizing the arrays.
     * @param newSize the new size of the resizing arrays.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Key[] copyKeys = (Key[]) new Comparable[newSize];
        Value[] copyValues = (Value[]) new Object[newSize];
        System.arraycopy(keys, 0, copyKeys, 0, n);
        System.arraycopy(values, 0, copyValues, 0, n);
        keys = copyKeys;
        values = copyValues;
    }

    /**
     * Returns true if the symbol table is empty.
     * @return {@code true} if the symbol table is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns true if the given key exists in the table.
     * @param key the given key
     * @return {@code true} if the given key exists in the table;
     *          {@code false} otherwise.
     * @throws IllegalArgumentException if the given key is null.
     */
    @Override
    public boolean contains(Key key) {
        return this.get(key) != null;
    }

    /**
     * Returns the number of the key-value pairs in the table.
     * @return the number of the key-value pairs in the table.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Put a key-value into the table, if the value is null then delete the key in the table.
     * @param key the key of the given key-value pair.
     * @param value the value of the given key-value pair.
     * @throws IllegalArgumentException if the given key is null.
     */
    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("The given key is null");
        if (value == null) {
            delete(key);
            return;
        }
        if (n == keys.length) {
            resize(keys.length * 2);
        }
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(key) == 0) { // if the given key is already exists.
                values[i] = value;
                return;
            }
        }
        // otherwise put the new key-value pair into the table.
        keys[n] = key;
        values[n++] = value;
    }

    /**
     * Get the Value of the given {@code key}
     * @param key the given key.
     * @return the value of the given {@code key} if key exists in the table;
     *          {@code null} otherwise.
     * @throws IllegalArgumentException if the given key is null.
     */
    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is null");
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(key) == 0) {
                return values[i];
            }
        }
        return null;
    }

    /**
     * Remove the key-value pair which contains the given {@code key}
     * @param key the key of deleting key-value pair.
     * @throws IllegalArgumentException if the given {@code key} is null.
     * @throws NoSuchElementException if the given {@code key} is not in the table.
     */
    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("The key to delete can not be null");
        for (int i = 0; i < n; i++) {
            if (keys[i].compareTo(key) == 0) {
                keys[i] = null;         // set the deleted key-value pair as null.
                values[i] = null;
                for (int j = i + 1; j < n; j++) { // moving the rest of the keys and values.
                    keys[j] = keys[j+1];
                    values[j] = values[j+1];
                }
                n--;
                if (n == keys.length / 4) {
                    resize(keys.length / 2);
                }
                return;
            }
        }
        throw new NoSuchElementException("The key is not in the table.");
    }

    /**
     * Returns an iterable obj which contains all the keys in the table.
     * @return an iterable obj which contains all the keys in the table.
     */
    @Override
    public Iterable<Key> keys() {
        return new ArraySTIt(this.n);
    }

    private class ArraySTIt implements Iterable<Key> {
        private int n;

        ArraySTIt(int n) {
            this.n = n;
        }

        @Override
        public Iterator<Key> iterator() {
            return new ArraySTIterator();
        }

        private class ArraySTIterator implements Iterator<Key> {
            private int start = 0;

            @Override
            public boolean hasNext() {
                return start < n;
            }

            @Override
            public Key next() {
                if (!hasNext()) throw new NoSuchElementException();
                return keys[start++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Unit test for this class {@code ArrayST}
     * @param args command arguments.
     */
    public static void main(String[] args) {
        ArrayST<String, Integer> st = new ArrayST<>();
        String[] test = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < test.length; i++) {
            st.put(test[i], i);
        }
        System.out.println("The input test symbol table:");
        for (String key : st.keys()) {
            System.out.println(key + " -> " + st.get(key) + " ");
        }
        System.out.println("The number of keys in symbol table is " + st.size() + ".");
    }
}
