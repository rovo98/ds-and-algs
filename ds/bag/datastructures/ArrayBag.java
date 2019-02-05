package com.rovo98.ds.bag.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * ADT ArrayBag, an implement using array.
 *
 * Data
 *      Item[] bag
 *      int    N
 * Operation
 *              ArrayBag()           Initial an empty bag.
 *      void    add(Item item)       Add an item into bag.
 *      boolean isEmpty()            Returns true if bag is empty, otherwise false.
 *      int     size()               Returns the number of items in bag.
 *      void    reSize(int max)      Resize the bag.
 *
 * endADT
 * @author rovo98
 * Date: 3/2/2018
 */
@SuppressWarnings("unchecked")
public class ArrayBag<Item> implements Bag<Item> {
    private Item[] bag = (Item[]) new Object[1];
    private int N = 0;

    /**
     * Returns true if bag is empty.
     * @return  {@code true} if bag is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return N == 0;
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
     * Add an item into bag.
     * @param item the item to add to bag.
     */
    public void add(Item item) {
        if (N == bag.length) {
            resize(2*bag.length);
        }
        bag[N++] = item;
    }

    /**
     * Resize the bag.
     * @param max the size of new bag.
     */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = bag[i];
        }
        bag = temp;
    }

    /**
     * Returns an iterator to the bag that iterates through items in bag.
     * @return An iterator to the bag that iterates through items in bag.
     */
    public Iterator<Item> iterator() {
        return new ArrayBagIterator();
    }
    private class ArrayBagIterator implements Iterator<Item> {
        private int n = N;
        @Override
        public boolean hasNext() {
            return n > 0;
        }
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return bag[--n];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests {@code ArrayBag} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Bag<Integer> bag = new ArrayBag<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            bag.add(r.nextInt(100));
        }
        System.out.println("The test input bag is the following:");
        for (int elem : bag) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}
