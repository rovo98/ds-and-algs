package com.rovo98.ds.queue.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT LinkedSteque, an implement of steque using linked list.
 *
 * Data
 *      ListNode first
 *      ListNode last
 *      int n
 * Operation
 *      LinkedSteque()              Initial an empty steque.
 *      boolean isEmpty()           Returns true if steque is empty, otherwise false.
 *      int size()                  Returns the number of items in steque.
 *      void push(Item item)        Add an item into steque using method push of stack.
 *      void enqueue(Item item)     Add an item into steque using method enqueue of queue.
 *      Item pop()                  Remove an item from steque.
 * endADT
 * @author rovo98
 * Date: 14/2/2018
 */
public class LinkedSteque<Item> implements Steque<Item> {
    private ListNode<Item> first;
    private ListNode<Item> last;
    private int n;

    class ListNode<item> {
        item item;
        ListNode<item> next;
    }

    /**
     * Initial an empty steque.
     */
    public LinkedSteque() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * Returns true if steque is empty.
     * @return      {@code true} if steque is empty;
     *              {@code false} otherwise.
     */
    // Time complexity: O(1).
    // Space complexity: O(1).
    @Override
    public boolean isEmpty() {
        return n == 0; // or first == null;
    }

    /**
     * Returns the number of items in steque.
     * @return the number of items in steque.
     */
    // Time complexity: O(1).
    // Space complexity: O(1).
    @Override
    public int size() {
        return n;
    }

    /**
     * Add an item into steque using push method of stack.
     * @param item the item to add to steque.
     */
    // Time complexity: O(1).
    // Space complexity: O(1).
    @Override
    public void push(Item item) {
        ListNode<Item> oldFirst = first;
        first = new ListNode<>();
        first.item = item;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldFirst;
        }
        n++;
    }

    /**
     * Add an item into steque using enqueue method of queue.
     * @param item the item to add to steque.
     */
    // Time complexity: O(1).
    // Space complexity: O(1).
    @Override
    public void enqueue(Item item) {
        ListNode<Item> oldLast = last;
        last = new ListNode<>();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    /**
     * Remove an item from steque.
     * @return the item removed from steque.
     * @throws NoSuchElementException if the steque is underflow.
     */
    // Time complexity: O(1).
    // Space complexity: O(1).
    @Override
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("The steque is underflow.");
        }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        n--;
        return item;
    }

    /**
     * Returns the print string for the steque.
     * @return the print string for the steque.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item).append(" ");
        }
        return s.toString();
    }
    /**
     * Returns an iterator to this steque that iterates over items in steque.
     * @return An iterator ot this steque that iterates over items in steque.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    @Override
    public Iterator<Item> iterator() {
        return new StequeIterator(first);
    }
    private class StequeIterator implements Iterator<Item> {
        private ListNode<Item> current;
        private StequeIterator(ListNode<Item> first) {
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
     * Unit tests {@code LinkedSteque} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        LinkedSteque<Integer> steque = new LinkedSteque<>();
        for (int i = 0; i < 6; i++) {
            steque.push(i);
            steque.enqueue(i);
        }
        System.out.println("The input test steque is " + steque.toString());
    }
}
