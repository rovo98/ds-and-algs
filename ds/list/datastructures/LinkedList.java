package com.rovo98.ds.list.datastructures;


import com.rovo98.ds.list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ADT LinkedList, an implement of list.
 *
 * Data
 *      Item val
 *      LinkedList next
 * Operation
 *      LinkedList()                        Initial an empty list with a head node.
 *      LinkedList(Item e)                  create a node with the value of e.
 *      boolean isEmpty()                   Returns true if list is empty.
 *      void clearList()                    Clear up list.
 *      Item getElem(int index)             Get the item at index in list.
 *      int locateElem(Item e)              Returns the index of item if it exists in list, otherwise 0.
 *      void insertElem(int index, Item e)  Insert the item e into list before index.
 *      Item deleteElem(int index)          Delete the item in list at index.
 *      int size()                          Returns the number of items in list.
 *      void addTail(Item e)                Add the item to head tail of list.
 *      void addHead(Item e)                Add the item to the head of list.
 *
 * endADT
 *
 * @author rovo98
 * Date: 3/2/2018
 */
public class LinkedList<Item> implements List<Item> {
    public Item val;
    public LinkedList<Item> next;

    /**
     * Initial an empty list.
     */
    public LinkedList() {
        val =  null;
        next = null;
    }

    /**
     * Initial a list with a node contains value = {@code val}
     * @param val the value of the node.
     */
    public LinkedList(Item val) {
        this.val = val;
        this.next = null;
    }

    public int compareTo(LinkedList other) {
        return this.val.toString().compareTo(other.val.toString());
    }

    /**
     * Returns true if list is empty.
     * @return  {@code true} if list is empty;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.next == null;
    }

    /**
     * Clear the list.
     */
    @Override
    public void clearList() {
        this.next = null;
    }

    /**
     * Get the item at the {@code index} of list.
     * @param index  the index of the item.
     * @return  the item whose index is {@code index}.
     */
    @Override
    public Item getElem(int index) {
        LinkedList<Item> pNode = this;
        int count = 0;
        while (count < index && pNode.next != null) {
            pNode = pNode.next;
            count++;
        }
        if (count < index) {
            throw new NoSuchElementException("The index is out of range.");
        }
        return pNode.val;
    }

    /**
     * Returns the index of the {@code e} in list.
     * @param e searched item.
     * @return  the index if {@code e} in list;
     *          {@code 0} otherwise.
     */
    @Override
    public int locateElem(Item e) {
        LinkedList<Item> pNode = this;
        int count = 0;
        while (pNode.next != null) {
            pNode = pNode.next;
            count++;
            if (pNode.val.equals(e)){
                return count;
            }
        }
        return 0;
    }

    /**
     * Insert the item {@code e} to list before {@code index}.
     * @param index  the insert index.
     * @param e      the insert item.
     */
    @Override
    public void insertElem(int index, Item e) {
        if (index < 1 || index > this.size()+1) {
            throw new UnsupportedOperationException("The insert index is illegal.");
        }
        LinkedList<Item> pNode = this;
        int count = 0;
        while (count < index) {
            pNode = pNode.next;
            count++;
        }
        pNode.next = new LinkedList<>(e);
    }

    /**
     * Remove the item at the {@code index} in list.
     * @param index the index of the item.
     * @return the item after deleting.
     * @throws NoSuchElementException if index is invalid.
     */
    @Override
    public Item deleteElem(int index) {
        if (index < 1 || index > this.size()) {
            throw new NoSuchElementException("The index is out of range.");
        }
        LinkedList<Item> pNode = this;
        LinkedList<Item> deleted;
        int count = 1;
        while (count < index) {
            pNode = pNode.next;
            count++;
        }
        deleted = pNode.next;
        pNode.next = pNode.next.next;
        return deleted.val;
    }

    /**
     * Returns the number of items in list.
     * @return the number of items in list.
     */
    @Override
    public int size() {
        // use the value of the head node to represent the length
        // of a list.
        int count = 0;
        LinkedList<Item> pNode = this;
        while (pNode != null) {
            count++;
            pNode = pNode.next;
        }
        return count;
    }

    /**
     * Add the item {@code e} to the tail of the list.
     * @param e the item to add to the list.
     */
    public void addTail(Item e) {
        LinkedList<Item> pNode = this;
        while (pNode.next != null) {
            pNode = pNode.next;
        }
        pNode.next = new LinkedList<>(e);
    }

    /**
     * Add the item {@code e} to the head of the list.
     * @param e the item to add to the list.
     */
    public void addHead(Item e) {
        LinkedList<Item> newNode = new LinkedList<>(e);
        newNode.next = this.next;
        this.next = newNode;
    }

    /**
     * Returns the print string of the list.
     * @return the print string of the list.
     */
    @Override
    public String toString() {
        LinkedList<Item> pNode = this;
        int count = 0;
        String s = "";
        while (pNode.next != null) {
            pNode = pNode.next;
            count++;
            s = s.concat(pNode.val + " -> ");
            if (count % 40 == 0) {
                s += "\n";
            }
        }
        s += "NULL.";
        return s;
    }
    /**
     * Returns an iterator to the list that iterates over items in list.
     * @return An iterator to the list that iterates over items in list.
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(this);
    }
    private class ListIterator<Item> implements Iterator<Item> {
        private LinkedList<Item> current;
        private ListIterator(LinkedList<Item> head) {
            current = head;
        }
        @Override
        public boolean hasNext() {
            return current.next != null;
        }
        @Override
        public Item next() {
            Item item = current.next.val;
            current = current.next;
            return item;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Unit tests {@code LinkedList} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        // test list : 1->2->3->NULL.
        LinkedList<Integer> testList = new LinkedList<>();
        testList.addTail(1);
        testList.addTail(2);
        testList.addTail(3);

        System.out.println("The input test list is the following:");
        System.out.println(testList.toString());
        // reverse it.
        LinkedList<Integer> rNode = testList.next;
        while (rNode.next != null) {
            LinkedList<Integer> node = rNode.next;
            rNode.next = node.next;
            node.next = testList.next;
            testList.next = node;
        }
        System.out.println("After reversing, it becomes:");
        System.out.println(testList.toString());
    }
}
