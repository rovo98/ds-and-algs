package com.rovo98.dsandalgs.list.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT ArrayList, an implement of list using array.
 *
 * Data
 *      Item[] data
 *      int length                  the number of items in list.
 * Operation
 *      ArrayList()                 Initial an empty list.
 *      boolean isEmpty()           Returns true if list is empty, otherwise false.
 *      int size()                  Returns the number of items in list.
 *      void clearList()            clear up the list.
 *      void insertElem()           Add an item to list.
 *      Item deleteElem(int index)  Remove an item from list at index.
 *      Item getElem(int index)     Get an item from list at index.
 *      int locateElem(Item item)   Locate the item, if in list return index of it, otherwise -1.
 *      void add(Item e)            Add an item to the list.
 *
 * endADT
 * @author rovo98
 * Date: 3/2/2018
 */
@SuppressWarnings("unchecked")
public class ArrayList<Item> implements List<Item> {
    private Item[] data;
    private int length;

    /**
     * Initial an empty list.
     */
    public ArrayList() {
        this.data = (Item[]) new Object[1];
        this.length = 0;
    }

    /**
     * Returns true if list is empty.
     * @return   {@code true} if list is empty;
     *           {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Clear up the list.
     */
    @Override
    public void clearList() {
        for (int i = 0; i < length; i++) {
            data[i] = null;
        }
        this.length = 0;
    }

    /**
     * Resize the list.
     * @param max the length of the new list.
     */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }
    /**
     * Get the item at index in list.
     * @param index the index of the item.
     * @return the item at the index in list.
     */
    @Override
    public  Item getElem(int index) {
        // If the list doesn't contains any elements.
        if (isEmpty()) {
            throw new NoSuchElementException("The list is underflow.");
        }
        // If the list is illegal.
        if (index < 1 || index > this.length) {
            throw new NoSuchElementException("The index is illegal.");
        }
        return data[index-1];
    }
    /**
     * Locate the item in list.
     * @param e the item to be located.
     * @return  {@code index} of item in list;
     *          {@code -1} if item not in list.
     */
    @Override
    public int locateElem(Item e) {
        for (int i=0; i<this.length; i++) {
            if (data[i].equals(e)) {
                return i+1;
            }
        }
        return 0;
    }

    /**
     * Insert an item into the list.
     * @param index the item insert before.
     * @param e     the item to insert into.
     */
    @Override
    public void insertElem(int index, Item e) {
        // If the list is full, resize the list.
        if (length == data.length) {
            resize(2*data.length);
        }
        // If index is illegal.
        if (index < 1 || index > length+1) {
            throw new UnsupportedOperationException("The insert index is illegal.");
        }
        if (index <= length) { // If index not at the end of the list.
            // move the elements that after the insert element before.
            for (int i = length-1; i > index-2; i--) {
                data[i+1] = data[i];
            }
        }
        // insert the new node and then refresh the length.
        data[index-1] = e;
        this.length++;
    }

    /**
     * Add an item to the list.
     * @param e the item to add to list.
     */
    public void add(Item e) {
        if (length == data.length) { // If the list is full, resize it.
            resize(2* data.length);
        }
        data[length++] = e;
    }

    /**
     * delete the item in list at index of {@code index}
     * @param index the index of the item.
     * @return the deleted item if success, otherwise {@code throw
     *          UnsupportedOperationException}
     */
    @Override
    public Item deleteElem(int index) {
        // If the index is illegal.
        if (index < 1 || index > length) {
            throw new UnsupportedOperationException("The delete index is illegal.");
        }
        // move the elements that after the delete element forward.
        for (int i = index-1; i < length-1; i++) {
            data[i] = data[i+1];
        }
        // refresh the length and then return deleted element.
        length--;
        if (length > 0 && length == data.length/4) {
            resize(data.length/2);
        }
        return data[index-1];
    }

    /**
     * Returns the print string of the list.
     * @return the print string of the list.
     */
    public String toString() {
        String s = "";
        for (Item e : data) {
            s = s + e + " ";
        }
        return s;
    }
    /**
     * Returns the number of items in list.
     * @return the number of items in list.
     */
    @Override
    public int size() {
        return length;
    }

    /**
     * Returns an iterator to the list that iterates over items in the list.
     * @return An iterator to the list that iterates over items in the list.
     */
    public Iterator<Item> iterator() {
        return new ArrayListIterator();
    }
    private class ArrayListIterator implements Iterator<Item> {
        private int i = length;
        public boolean hasNext() {
            return i > 0;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[--i];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests {@code ArrayList} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
       ArrayList<Integer> testList = new ArrayList<>();
       testList.add(1);
       testList.add(2);
       testList.add(3);
       System.out.println("The input test list is the following:");
       System.out.println(testList.toString());
    }
}
