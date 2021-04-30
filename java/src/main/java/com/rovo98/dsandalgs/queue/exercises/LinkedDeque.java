package com.rovo98.dsandalgs.queue.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT LinkedDeque, an implement of deque using DoubleDirected Linked list.
 *
 * Data:
 *      DListNode first             the head of the deque.
 *      DListNode last              the tail of the deque.
 *      int n                       the number of items in deque.
 * Operation
 *        LinkedDeque()             Initial an empty deque.
 *        boolean isEmpty()         Returns true if deque is empty, otherwise false.
 *        int size()                Returns the number of items in deque.
 *        void pushLeft(Item item)  Add an item into the left of the deque.
 *        void pushRight(Item item) Add an item into the right of the deque.
 *        Item popLeft()            Remove an item from the left of the deque.
 *        Item popRight()           Remove an item from the right of the deque.
 * endADT
 * @author rovo98
 * Date: 15/2/2018
 */
public class LinkedDeque<Item> implements Deque<Item> {
    private DListNode<Item> first;
    private DListNode<Item> last;
    private int n;

    class DListNode<item> {
        item item;
        DListNode<item> previous;
        DListNode<item> next;
    }

    /**
     * Initial an empty deque.
     */
    public LinkedDeque() {
        first = null;
        last = null;
        n = 0;
    }
    /**
     * Returns true if deque is empty.
     * @return  {@code true} if deque is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return n == 0; // first == null
    }

    /**
     * Returns the number of items in deque.
     * @return the number of items in deque.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Add an item into the left of the deque.
     * @param item the item to add to deque.
     */
    @Override
    public void pushLeft(Item item) {
        DListNode<Item> oldFirst = first;
        first = new DListNode<>();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.previous = first;
            first.next = oldFirst;
        }
        n++;
    }

    /**
     * Add an item into the right of the deque.
     * @param item the item to add to deque.
     */
    @Override
    public void pushRight(Item item) {
        DListNode<Item> oldLast = last;
        last = new DListNode<>();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            last.previous = oldLast;
            oldLast.next = last;
        }
        n++;
    }

    /**
     * Remove an item from the left of deque.
     * @return the item removed from the left of deque.
     * @throws NoSuchElementException if deque is underflow.
     */
    @Override
    public Item popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is underflow.");
        }
        Item item = first.item;
        first.next.previous = null;
        first = first.next;
        n--;
        return item;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item).append(" ");
        }
        return s.toString();
    }
    /**
     * Remove an item from the right of deque.
     * @return the item removed from the right of deque.
     * @throws NoSuchElementException if deque is underflow.
     */
    @Override
    public Item popRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is underflow.");
        }
        Item item = last.item;
        last.previous.next = null;
        last = last.previous;
        n--;
        return item;
    }

    /**
     * Returns an iterator to the deque that iterates over items in deque from the left to right.
     * @return An iterator to the deque that iterates over items in deque from the left to right.
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }
    private class DequeIterator implements Iterator<Item> {
        private DListNode<Item> current;
        private DequeIterator(DListNode<Item> first) {
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests {@code LinkedDeque} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        LinkedDeque<Integer> deque = new LinkedDeque<>();
        for (int i = 1; i < 5; i++) {
            deque.pushLeft(i);
            deque.pushRight(i);
        }
        System.out.println("The input test deque is " + deque.toString() + ", size: " + deque.size());
        int deletedItem1 = deque.popLeft();
        int deletedItem2 = deque.popRight();
        System.out.println("After removing items from deque: " + deque.toString() + ",deleted:"
                            + deletedItem1 + ", " + deletedItem2 + ", size: " + deque.size());
    }
}
