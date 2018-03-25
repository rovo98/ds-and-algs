package com.rovo98.ds.symbolTable.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT  SequentialSearchST, an implementation of Symbol Table using singly-linked-list with no order.
 *
 * Data:
 *          Node first
 *          int n                               the number of the keys in symbol table.
 * Operations:
 *          void put(Key key, Value val)        add a key-value into table. update value if key is already exists.
 *          Value get(Key key)                  Get the value of the {@code key} if key is in table.
 *          void delete(Key key)                Delete a key-value.
 *          boolean contains(Key key)           Returns true if key in symbol table.
 *          boolean isEmpty()                   Returns true if symbol table if empty, otherwise false.
 *          int size()                          Returns the number of keys in symbol table.
 *          Iterable<Key> keys()                Returns an iterator that iterates over all keys in symbol table.
 *
 * endADT
 * @author rovo98
 * Date: 2018.3.25
 *
 * Learning from algs4.
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
    private Node<Key, Value> first;
    private int n;

    private class Node<K, V> {
        private K key;
        private V val;
        private Node<K, V> next;

        private Node(K key, V val, Node<K, V> next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public SequentialSearchST() {
        first = null;
        n = 0;
    }
    /**
     * Returns the value of the {@code key}
     * @param key the key belongs to a key-value.
     * @return the value of the {@code key};
     *          {@code null} if {@code key} not in symbol table.
     */
    /*
    Complexity Analysis:
        Time complexity: O(n)
        Space complexity: O(1).
     */
    @Override
    public Value get(Key key) {
        Node<Key, Value> pNode = first;
        while (pNode != null) {
            if (key.equals(pNode.key)) {
                return pNode.val;
            }
            pNode = pNode.next;
        }
        return null;
    }

    /**
     * Add a key-value into symbol table
     * @param key the key of the key-value to add to table.
     * @param value the value of the key-value to add to table.
     */
    /*
    Complexity Analysis:
        Time complexity: O(n)
        Space Complexity: O(1).
     */
    @Override
    public void put(Key key, Value value) {
        if (value == null) {
            delete(key);
            return;
        }
        Node<Key, Value> pNode = first;
        while (pNode != null) {
            if (key.equals(pNode.key)) {        // update the val if key is already in table.
                pNode.val = value;
                return;
            }
            pNode = pNode.next;
        }
        // otherwise.
        first = new Node<>(key, value, first);
        n++;

    }

    /**
     * Remove a key in symbol table.
     * @param key the key to be removed.
     * @throws NoSuchElementException if key not in symbol table.
     */
    // Time complexity: O(n).
    @Override
    public void delete(Key key) {
        if (!contains(key)) {
            throw new NoSuchElementException("key is not in symbol table.");
        }
        if (first.key.equals(key)) {
            first = first.next;
            n--;
            return;
        }
        Node<Key, Value> pNode = first;
        while (pNode.next != null) {
            if (pNode.next.key.equals(key)) {
                pNode.next = pNode.next.next;
                n--;
                return;
            }
            pNode = pNode.next;
        }
    }

    /**
     * Returns true if {@code key} in symbol table.
     * @param key the key to be searched.
     * @return  {@code true} if key in symbol table.
     *          {@code false} otherwise.
     */
    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }
    /**
     * Returns true if symbol table is empty.
     * @return {@code true} if symbol table is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of key-value in symbol table.
     * @return the number of key-value in symbol table.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns an iterator that iterates over keys in symbol table.
     * @return an iterator that iterates over keys in symbol table.
     */
    @Override
    public Iterable<Key> keys() {
        return new SequentialSearchSTIterator(first);
    }
    private class SequentialSearchSTIterator implements Iterable<Key> {
       private Node<Key, Value> current;
       private SequentialSearchSTIterator(Node<Key, Value> first) {
           this.current = first;
       }

       @Override
       public Iterator<Key> iterator() {
           return new STIterator(current);
       }
       // A symbol table iterator that iterates over keys in symbol table.
       private class STIterator implements Iterator<Key> {
           private Node<Key, Value> current;
           private STIterator(Node<Key, Value> first) {
               this.current = first;
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
               Key del = current.key;
               current = current.next;
               return del;
           }
           @Override
           public void remove() {
               throw new UnsupportedOperationException();
           }
       }
    }

    /**
     * Unit tests the {SequentialSearchST} data type.
     * @param args command arguments.
     */
    public static void main(String[] args) {
        ST<Integer, String> st = new SequentialSearchST<>();
        String[] test = {"mshinoda", "john", "chester", "bennington", "mike"};
        for (int i = 1; i < 6; i++) {
            st.put(i, test[i-1]);
        }
        System.out.println("The input test symbol table:");
        for (int key : st.keys()) {
            System.out.println(key + " -> " + st.get(key) + " ");
        }
        System.out.println("The number of keys in symbol table is " + st.size() + ".");
    }
}
