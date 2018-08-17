package com.rovo98.ds.symbolTable.exercises;

/**
 * @author : rovo98
 * date : 2018/8/14
 *
 * Sorted Symbol Table interface
 *
 *      SortedST()                      Initialize a sorted symbol table.
 * void put(Key key, Value value)       Put a key-value pair into table; update value if key exists, delete key-value pair
 *                                      if key is {@code null}
 * Value get(Key key)                   Get the value of the given {@code key}, returns {@code null} if key no in table.
 * boolean contains(Key key)            Returns {@code true} if table contains {@code key}; otherwise returns {@code false}.
 * boolean isEmpty()                    Returns {@code true} if table is empty; otherwise {@code false}.
 * int size()                           Returns the number of the key-value pairs in the symbol table.
 * Key max()                            Returns the maximum key in the table.
 * Key min()                            Returns the minimum key in the table.
 * Key floor(Key key)                   Returns the maximum key that less than the given {@code key}
 * Key ceiling(Key key)                 Returns the minimum key that greater than the given {@code key}.
 * int rank(Key key)                    Returns the number of the key that less than the given {@code key}.
 * Key select(int k)                    Returns the k-th key in the sorted order.
 * void deleteMin()                     Delete the mininum key in the table.
 * void deleteMax()                     Delete the maximum key in the table.
 * int size(Key lo, Key hi)             Returns the number of keys between the range of [lo, hi].
 * Iterable<Key> keys(Key lo, Key hi)   Returns a iterator containing the keys between the range of [lo, hi].
 * Iterable<Key> keys()                 Returns a iterator containing all keys.
 *
 * learning from algs4.
 */
public interface SortedST<Key extends Comparable<Key>, Value> {
    void put(Key key, Value value);
    Value get(Key key);
    Value delete(Key key);
    boolean contains(Key key);
    boolean isEmpty();
    int size();
    Key max();
    Key min();
    Key floor(Key key);
    Key ceiling(Key key);
    int rank(Key key);
    Key select(int k);
    Value deleteMin();
    Value deleteMax();
    int size(Key lo, Key hi);
    Iterable<Key> keys(Key lo, Key hi);
    Iterable<Key> keys();
}
