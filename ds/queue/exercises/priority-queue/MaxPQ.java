package com.rovo98.ds.queue.exercises.priorityqueue;

/**
 * ADT MaxPQ - max priority queue.
 *
 * Data:
 *
 * Operations:
 *      MaxPQ()             create an empty MaxPQ
 *      MaxPQ(int max)      create an empty MaxPQ where its maximum size is {@code max}.
 *      MaxPQ(Key[] a)      create a MaxPQ containing all elements in array {@code a}.
 *      void insert(Key v)  insert a key to the MaxPQ.
 *      Key max()           get the max key of the MaxPQ
 *      Key delMax()        delete the max Key from MaxPQ.
 *      boolean isEmpty()   Returns true if MaxPQ is empty; otherwise false.
 *      int size()          Returns the number of the keys in MaxPQ.
 * endADT
 *
 * @author rovo98
 *
 * Date: 11/3/2018.
 * Description: learing from algs4.
 */
public interface MaxPQ<Key extends Comparable<Key>> extends Iterable<Key> {
    void insert(Key v);
    Key max();
    Key delMax();
    boolean isEmpty();
    int size();
}
