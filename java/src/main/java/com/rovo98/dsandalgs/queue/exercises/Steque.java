package com.rovo98.dsandalgs.queue.exercises;

/**
 * ADT Steque
 *
 * Data
 *      ListNode first
 *      ListNode last
 *      int n
 * Operation
 *      boolean isEmpty()       Returns true if steque is empty, otherwise false.
 *      int size()              Returns the number of items in steque.
 *      void push(Item item)    push an item into steque.
 *      void enqueue(Item item) enqueue an item into steque.
 *      Item pop()              Remove an item from steque.
 *
 * endADT
 * @author rovo98
 * Date: 14/2/2018
 */
public interface Steque<Item> extends Iterable<Item> {
    boolean isEmpty();
    int size();
    void push(Item item);
    void enqueue(Item item);
    Item pop();
}
