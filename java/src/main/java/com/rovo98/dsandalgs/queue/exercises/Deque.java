package com.rovo98.dsandalgs.queue.exercises;

/**
 * ADT Deque
 *
 * Data
 *
 *      int n                       the number of items in deque.
 * Operation
 *      boolean isEmpty()           Returns true if deque is empty, otherwise false.
 *      int size()                  Returns the number of items in deque.
 *      void pushLeft(Item item)    Add an item into the left of deque.
 *      void pushRight(Item item)   Add an item into the right of deque.
 *      Item popLeft()              Remove an item from the left of deque.
 *      Item popRight()             Remove an item from the right of deque.
 *
 * endADT
 * @author rovo98
 * Date: 15/2/2018
 *
 */
public interface Deque<Item> extends Iterable<Item> {
    boolean isEmpty();
    int size();
    void pushLeft(Item item);
    void pushRight(Item item);
    Item popLeft();
    Item popRight();
}
