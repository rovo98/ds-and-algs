package com.rovo98.ds.queue.exercises.priorityqueue.exercises;

/**
 * ADT MinPQ
 *
 * Data:
 *      queue[]
 *      int n               The number of keys in priority queue.
 * Operation:
 *      void insert(Key key)    Add a key into priority queue.
 *      Key min()               Returns a minimum key from priority queue.
 *      Key delMin()            Remove a minimum key and returns it.
 *      boolean isEmpty()       Returns true if priority is empty.
 *      int size()              Returns the number of keys in priority queue.
 * endADT
 *
 * @author rovo98
 * Date: 2018.3.22
 * learning from algs4.
 */
public interface MinPQ<Key extends Comparable<Key>> extends Iterable<Key> {
    void insert(Key key);
    Key min();
    Key delMin();
    boolean isEmpty();
    int size();
}
