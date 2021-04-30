package com.rovo98.dsandalgs.queue.exercises;

/**
 * ADT RandomQueue, a kind of queue.
 *
 * Data
 *
 * Operation
 *      RandomQueue()           Initial an empty RandomQueue.
 *      boolean isEmpty()       Returns true if RandomQueue is empty, otherwise false.
 *      int size()              Returns the number of items in queue.
 *      Item dequeue()          Remove an item from the queue randomly.
 *      Item sample()           Get an item from the queue randomly.
 *      void enqueue()          Add an item into queue.
 * endADT
 *
 * @author rovo98
 * 15/2/2018
 */
public interface RandomQueue<Item> extends Iterable<Item> {
    boolean isEmpty();
    void enqueue(Item item);
    Item dequeue();
    Item sample();
    int size();
}
