package com.rovo98.ds.queue.datastructures;

/**
 * ADT  CircleQueue, an implement of queue using Fixed size array.
 *
 * Data
 *      Item[] queue
 *      int first
 *      int last
 *      int N
 * Operation
                CircleQueue()       Initial an empty circle queue.
 *      void    enqueue(Item item)  Add an item into queue.
 *      Item    dequeue()           Remove an item from queue.
 *      boolean isEmpty()           Returns true if queue is empty, otherwise false.
 *      int     size()              Returns the number of items in queue.
 * endADT
 * @author rovo98
 * Date: 1/2/2018
 */
@SuppressWarnings("unchecked")
public class CircleQueue<Item> {
    private Item[] queue = (Item[]) new Object[1];
    private int first = 0;
    private int last = 0;
    private int N = 0;

    /**
     * Returns true if queue is empty.
     * @return  {@code true} if queue is empty;
     *          {@code false} otherwise.
     */
    public boolean isEmpty() {
        return first == last; // or N == 0;
    }

    /**
     * Returns the number of items in queue.
     * @return the number of items in queue.
     */
    public int size() {
        return N;
    }

    /**
     * Add an item to queue.
     * @param item the item to add to queue.
     */
    public void enqueue(Item item) {
        // 循环队列满了时
        if ((last + 1) % queue.length == first) { // or N == queue.length
           return;
        }
        queue[last++] = item;
        last = last % queue.length;
        N++;
    }

    /**
     * Remove an item from queue.
     * @return the item removed from queue.
     */
    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item = queue[first++];
        first = first % queue.length;
        N--;
        return item;
    }

    /**
     * Unit tests {@code CircleQueue} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {

    }
}
