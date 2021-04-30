package com.rovo98.dsandalgs.symbolTable.exercises;

/**
 * @author rovo98
 * @date 2018.9.22
 *
 * Sorted Symbol Table interface
 *
 *      BinarySearchTreeST()            Initialize a sorted symbol table.
 * void put(Key key, Value value)       Put a key-value pair into table; update value if key exists, delete key-value pair
 *                                      if key is {@code null}
 * Value get(Key key)                   Get the value of the given {@code key}, returns {@code null} if key no in table.
 * boolean contains(Key key)            Returns {@code true} if table contains {@code key}; otherwise returns {@code false}.
 * boolean isEmpty()                    returns {@code true} if table is empty; otherwise {@code false}.
 * int size()                           returns the number of the key-value pairs in the symbol table.
 * key max()                            returns the maximum key in the table.
 * key min()                            returns the minimum key in the table.
 * key floor(key key)                   returns the maximum key that less than the given {@code key}
 * key ceiling(key key)                 returns the minimum key that greater than the given {@code key}.
 * int rank(key key)                    returns the number of the key that less than the given {@code key}.
 * key select(int k)                    returns the k-th key in the sorted order.
 * void delete(key key)                delete the given key in the table if exists.
 * void deleteMin()                     delete the minimum key in the table.
 * void deleteMax()                     delete the maximum key in the table.
 * iterable<key> keys()                 returns a iterator containing all keys.
 * Iterable<Key> keys(Key lo, Key hi)   Returns a iterator containing the keys between the range of [lo, hi].
 *
 * learning from algs4.
 */

public interface BinarySearchTreeST<Key extends Comparable<Key>, Value> {
    void put(Key key, Value value);
    Value get(Key key);
    void delete(Key key);
    boolean contains(Key key);
    boolean isEmpty();
    int size();
    Key max();
    Key min();
    Key floor(Key key);
    Key ceiling(Key key);
    int rank(Key key);
    Key select(int k);
    void deleteMin();
    void deleteMax();
    Iterable<Key> keys();
    Iterable<Key> keys(Key lo, Key hi);
}
