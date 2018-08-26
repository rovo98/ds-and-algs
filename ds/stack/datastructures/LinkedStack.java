package com.rovo98.ds.stack.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT LinkedStack, an implements of stack using linked list.
 *
 * Data
 *      Node first
 *      int n                   number of the items in stack.
 * Operation
 *      LinkedStack()           Initial an empty stack.
 *      boolean isEmpty()       Returns true if stack is empty, otherwise false.
 *      int size()              Returns the number of items in stack.
 *      void push(Item item)    Add an item to stack.
 *      Item pop()              Remove an item from stack.
 *      Item peek()             Get an item from stack.
 *
 * endADT
 * @author rovo98
 * Date: 2/2/2018
 */
public class LinkedStack<Item> implements Stack<Item> {
    private Node<Item> first;
    private int n;
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    /**
     * Initial an empty stack.
     */
    public LinkedStack() {
        first = null;
        n = 0;
    }

    /**
     * Copy stack s.
     * @param s the stack to be copied.
     */
    public LinkedStack(LinkedStack<Item> s) {
        Node<Item> p = s.first;
        while (p != null) {
            Node<Item> oldFirst = first;
            first = new Node<>();
            first.item = p.item;
            first.next = oldFirst;
            n++;
            p = p.next;
        }
    }
    /**
     * Returns true if the stack is empty.
     * @return {@code true} if the stack is empty;
     *         {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return first == null; // or n == 0;
    }

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack.
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Add an item to the stack.
     * @param item the item to add to the stack.
     */
    @Override
    public void push(Item item) {
        // insert the new item to the head of the linked list.
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    /**
     * Remove an item from the stack.
     * @return the item removed from the stack.
     */
    @Override
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is underflow.");
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * Get an item from the stack.
     * @return the item got from the stack.
     */
    @Override
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is underflow.");
        }
        return first.item;
    }

    /**
     * Returns an iterator to the stack that iterates through items in the stack in LIFO order.
     * @return An iterator to the stack that iterates through items in the stack in LIFO order.
     */
    @Override
    public Iterator<Item> iterator() {
        return new LinkedStackIterator<>(first);
    }
    private class LinkedStackIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        private LinkedStackIterator(Node<Item> first) {
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
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests the {@code LinkedStack} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Stack<String> stack = new LinkedStack<>();
        String[] testTobe = {"to", "-", "or", "not", "to", "be", "-", "that", "is"};
        for (int i = 0; i < testTobe.length; i++) {
            if (testTobe[i].equals("-")) {
                System.out.print(stack.pop()+" ");
            } else {
                stack.push(testTobe[i]);
            }
        }
        System.out.print("("+stack.size()+" left on stack).");
    }
}
