package com.rovo98.dsandalgs.bag.ds;

/**
 * ADT Bag
 *
 * Data
 *
 * Operation
 *      boolean isEmpty()       Returns true if bag is empty, otherwise false.
 *      int size()              Returns the number of items in bag.
 *      void add(Item item)     Add an item into bag.
 * endADT
 * @author rovo98
 * Date: 3/2/2018
 */
public interface Bag<Item> extends Iterable<Item> {
    boolean isEmpty();
    int size();
    void add(Item item);
}
