package com.rovo98.ds.stack.datastructures;

/**
 * ADT Stack (LIFO)
 *
 * Data
 *      stack
 *      int n                   the number of items in stack.
 * Operation
 *      Stack()                 Initial an empty stack.
 *      boolean isEmpty()       Returns true if stack is empty, otherwise false.
 *      void push(Item item)    Add an item to stack.
 *      Item pop()              Remove an item from stack.
 *      int  size()             Returns the number of items in stack.
 *      Item peek()             Get an item from stack.
 *
 * endADT
 * @author rovo98
 * Date: 2/2/2018
 */
public interface Stack<Item> extends Iterable<Item>{
    boolean isEmpty();
    int size();
    void push(Item item);
    Item pop();
    Item peek();
}
