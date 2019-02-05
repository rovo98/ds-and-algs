package com.rovo98.ds.bag.exercises;

/**
 * ADT RandomBag which has the same API with Bag but its iterator iterates the items randomly.
 *
 * Data
 *      ...
 *      int n
 * Operation
 *      boolean isEmpty()       Returns true if the bag is empty, otherwise false.
 *      int size()              Returns the number of items in bag.
 *      void add(Item item)     Add an item to bag.
 * endADT
 * @author rovo98
 * Date: 15/2/2018
 */
public interface RandomBag<Item> extends Iterable<Item> {
    boolean isEmpty();
    int size();
    void add(Item item);
}
