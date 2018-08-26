package com.rovo98.ds.list.exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT DoubleNode
 *
 * Data
 *      Item val
 *      DoubleNode previous
 *      DoubleNode next
 * Operation
 *      DoubleNode()                                Initial an empty doubleList node.
 *      DoubleNode(Item item)                       Initial a doubleList node contains item.
 *      void addHead(Item item)                     Add an item to the head of the list.
 *      void addTail(Item item)                     Add an item to the tail of the list.
 *      Item deleteHead()                           Remove the item at the head of list.
 *      Item deleteTail()                           Remove the last item in the list.
 *      void insertAfter(int index, Item item)      Insert an item into list after index.
 *      void insertBefore(int index, Item item)     Insert an item into list before index.
 *      Item deleteAt(int index)                    Remove the item at index.
 *
 * endADT
 *
 * @author rovo98
 * Date: 13/2/2018
 *
 */
public class DoubleNode<Item> implements Iterable<Item> {
    public Item val;
    public DoubleNode<Item> previous;
    public DoubleNode<Item> next;

    /**
     * Initial an empty double node.
     */
    public DoubleNode() {
        val = null;
        previous = null;
        next = null;
    }
    public DoubleNode(Item item) {
        val = item;
        previous = null;
        next = null;
    }

    /**
     * Add an item to the head of the double list.
     * @param item the item to add to the list.
     */
    // Time complexity: O(1).
    // Space complexity: O(1).
    public  void addHead(Item item) {
        DoubleNode<Item> newNode = new DoubleNode<>(item);
        newNode.next = this.next;
        newNode.previous = this;
        this.next = newNode;
    }

    /**
     * Add an item to the tail of the double list.
     * @param item the item to add to list.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    public void addTail(Item item) {
        DoubleNode<Item> dNode = this;
        while (dNode.next != null) {
            dNode = dNode.next;
        }
        DoubleNode<Item> newNode = new DoubleNode<>(item);
        newNode.previous = dNode;
        dNode.next = newNode;
    }

    /**
     * Remove an item from the head of the double list.
     * @return the item removed from the list.
     * @throws NoSuchElementException if list is empty.
     */
    // Time complexity: O(1).
    // Space complexity: O(1).
    public Item deleteHead() {
        if (this.next == null) {
            throw new NoSuchElementException("The DoubleList is underflow.");
        }
        Item item  = this.next.val;
        this.next.next.previous = this;
        this.next = this.next.next;
        return item;
    }

    /**
     * Remove an item from the tail of the list.
     * @return the item removed from the list.
     * @throws NoSuchElementException if list is empty.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    public Item deleteTail() {
        if (this.next == null) {
            throw new NoSuchElementException("The DoubleList is underflow.");
        }
        DoubleNode<Item> dNode = this;
        while (dNode.next.next != null) {
            dNode = dNode.next;
        }
        Item item = dNode.next.val;
        dNode.next = null;
        return item;
    }

    /**
     * Insert an item into list after {@code index}
     * @param index the index before the inserted item.
     * @param item  the item to insert into list.
     * @throws UnsupportedOperationException if index is out of range.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    public void insertAfter(int index, Item item) {
        if (index < -1 || index > this.size()) {
            throw new UnsupportedOperationException("The index is out of the range of list.");
        }
        int count = 0;
        DoubleNode<Item> dNode = this;
        DoubleNode<Item> newNode = new DoubleNode<>(item);
        while (count < index) {
            dNode = dNode.next;
            count++;
        }
        newNode.next = dNode.next;
        dNode.next.previous = newNode;
        newNode.previous = dNode;
        dNode.next = newNode;
    }

    /**
     * Insert an item into list before {@code index}.
     * @param index the index of inserted item.
     * @param item the item to insert into list.
     * @throws UnsupportedOperationException if index is out of range.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    public void insertBefore(int index, Item item) {
        if (index < 0 || index > this.size() + 1) {
            throw new UnsupportedOperationException("The index is out of the range of list.");
        }
        int count = 1;
        DoubleNode<Item> dNode = this;
        DoubleNode<Item> newNode = new DoubleNode<>(item);
        while (count < index) {
            dNode = dNode.next;
            count++;
        }
        newNode.next = dNode.next;
        dNode.next.previous = newNode;
        newNode.previous = dNode;
        dNode.next = newNode;
    }

    /**
     * Remove an item from the list at index of {@code index}.
     * @param index the index of the deleting item.
     * @throws UnsupportedOperationException if index if out of range.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    public Item deleteAt(int index) {
        if (index < 0 || index > this.size()) {
            throw new NoSuchElementException("The index is out of the range of list.");
        }
        int count = 1;
        DoubleNode<Item> dNode = this;
        while (count < index) {
            dNode = dNode.next;
            count++;
        }
        Item item = dNode.next.val;
        dNode.next = dNode.next.next;
        dNode.next.next.previous = dNode;
        return item;
    }

    /**
     * Returns the number of items in list.
     * @return the number of items in list.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    public int size() {
        DoubleNode dNode = this;
        int count = 0;
        while (dNode.next != null) {
            dNode = dNode.next;
            count++;
        }
        return count;
    }

    /**
     * Returns the list print string.
     * @return the list print string.
     */
    // Time complexity: O(n).
    // Space complexity: O(1).
    @Override
    public String toString() {
        String s = "";
        for (Item item : this) {
            s = new StringBuilder().append(s).append(item).append(" ").toString();
        }
        return s;
    }

    /**
     * Returns an iterator to this list that iterates through items in list.
     * @return An iterator to this list that iterates through items in list.
     */
    @Override
    public Iterator<Item> iterator() {
        return new DoubleListIterator<>(this.next);
    }
    private class DoubleListIterator<Item> implements Iterator<Item> {
        private DoubleNode<Item> current;
        private DoubleListIterator(DoubleNode<Item> head) {
            current = head;
        }
        @Override
        public boolean hasNext() {
            return current.next != null;
        }
        @Override
        public Item next() {
            Item item = current.val;
            current = current.next;
            return item;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    /**
     * Unit tests {@code DoubleNode} date type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        DoubleNode<Integer> testList = new DoubleNode<>();
        for (int i = 0; i < 6; i++) {
            testList.addTail(i);
        }
        System.out.println("The input test list is: " + testList.toString());
        testList.insertAfter(1, 10);
        System.out.println("After calling the function 'insertAfter(1, 10)': " + testList.toString());
        testList.insertBefore(2, 11);
        System.out.println("After calling the function 'insertBefore(2, 11)': " + testList.toString());
        testList.addHead(100);
        System.out.println("After calling the function 'addHead(100)': " + testList.toString());
        int deletedNode = testList.deleteTail();
        System.out.println("After calling the function 'deleteTail()': " + testList.toString() +
                            ", deleted: " + deletedNode);
        int deletedNode_1 = testList.deleteHead();
        System.out.println("After calling the function 'deleteHead()': " + testList.toString() +
                            ", deleted: " + deletedNode_1);
        int deletedNode_2 = testList.deleteAt(1);
        System.out.println("After calling the function 'deleteAt(1)': " + testList.toString() +
                            ", deleted: "+ deletedNode_2);
    }
}
