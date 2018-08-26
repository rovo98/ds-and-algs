package com.rovo98.ds.symbolTable.datastructures;

/**
 * ADT Symbol Table, ST
 *
 * Data
 *
 * Operations:
 *      ST()                            Initializes an empty symbol table.
 *      void put(Key key, Value val)    Add a key-value into table, delete key if val is null.
 *      Value get(Key key)              Get the value of the {@code key}, returns null if key doesn't exists.
 *      void delete(Key key)            Delete the key-value
 *      boolean contains(Key key)       Returns true if {@code key} in symbol table.
 *      boolean isEmpty()               Returns true if symbol table if empty, otherwise false.
 *      int size()                      Returns the number of key-value in symbol table.
 *      Iterable<Key> keys()            Returns the all keys in symbol table.
 * endADT
 *
 * @author rovo98
 * Date: 2018.3.25.
 *
 * Learning from algs4.
 */
public interface ST<Key extends Comparable<Key>, Value> {
    void put(Key key, Value val);
    Value get(Key key);
    void delete(Key key);
    boolean contains(Key key);
    boolean isEmpty();
    int size();
    Iterable<Key> keys();
}
