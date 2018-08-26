package com.rovo98.ds.bag.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT LinkedBag, an implement using linked list.
 *
 * Data
 *      Node bag
 *      int    N
 * Operation
 *              LinkedBag()          Initial an empty bag.
 *      void    add(Item item)       Add an item to bag.
 *      boolean isEmpty()            Returns true if bag is empty, otherwise false.
 *      int     size()               Returns the number of items in bag.
 *
 * endADT
 * @author rovo98
 * Date: 3/2/2018
 */
public class LinkedBag<Item> implements Bag<Item> {
    private Node<Item> first;
    private int N;
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }
    public LinkedBag() {
        this.first = null;
        this.N = 0;
    }

    /**
     * Returns true if bag is empty.
     * @return  {@code true} if bag is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return first == null; // or N == 0;
    }

    /**
     * Returns the number of items in bag.
     * @return the number of items in bag.
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * Add an item into the bag.
     * @param item  the item to add to bag.
     */
    @Override
    public void add(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    /**
     * Returns an iterator to this bag that iterates over items in bag.
     * @return An iterator to this bag that iterates over items in bag.
     */
    public Iterator<Item> iterator() {
        return new LinkedBagIterator<>(first);
    }
    private class LinkedBagIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        public LinkedBagIterator(Node<Item> first) {
            this.current = first;
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
     * Unit tests {@code LinkedBag} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Bag<Integer> bag = new LinkedBag<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            bag.add(r.nextInt(100));
        }
        System.out.println("The test input bag is the following:");
        for (int e : bag) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
