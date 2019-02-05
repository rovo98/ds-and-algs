package com.rovo98.ds.queue.datastructures;

/**
 * ADT Queue (LILO)
 *
 * Data
 *       queue
 *       int n                  the number of items in queue.
 * Operation
 *      boolean isEmpty()       Returns true if queue is empty, otherwise false.
 *      int size()              Returns the number of items in queue.
 *      void enqueue(Item item) Add an item to queue.
 *      Item dequeue()          Remove an item from queue.
 *      Item peek()             Get the top item from queue.
 *
 * endADT
 * @author rovo98
 * Date: 2/2/2018
 */
public interface Queue<Item> extends Iterable<Item> {
    boolean isEmpty();
    int size();
    void enqueue(Item item);
    Item dequeue();
    Item peek();
}
