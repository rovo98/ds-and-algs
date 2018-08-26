package com.rovo98.ds.symbolTable.exercises.symbolTable_exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author : rovo98
 * date : 2018/8/16
 *
 * Using ordered Linked-list to implement the interface {@code SortedST}.
 *
 * OrderedSequentialSearchST()     Initialize a sorted symbol table.
 * void put(Key key, Value value)       Put a key-value pair into table; update value if key exists, delete key-value pair
 * if key is {@code null}
 * Value get(Key key)                   Get the value of the given {@code key}, returns {@code null} if key no in table.
 * boolean contains(Key key)            Returns {@code true} if table contains {@code key}; otherwise returns {@code false}.
 * boolean isEmpty()                    Returns {@code true} if table is empty; otherwise {@code false}.
 * int size()                           Returns the number of the key-value pairs in the symbol table.
 * Key max()                            Returns the maximum key in the table.
 * Key min()                            Returns the minimum key in the table.
 * Key floor(Key key)                   Returns the maximum key that less than the given {@code key}
 * Key ceiling(Key key)                 Returns the minimum key that greater than the given {@code key}.
 * int rank(Key key)                    Returns the number of the key that less than the given {@code key}.
 * Key select(int k)                    Returns the k-th key in the sorted order.
 * void deleteMin()                     Delete the mininum key in the table.
 * void deleteMax()                     Delete the maximum key in the table.
 * int size(Key lo, Key hi)             Returns the number of keys between the range of [lo, hi].
 * Iterable<Key> keys(Key lo, Key hi)   Returns a iterator containing the keys between the range of [lo, hi].
 * Iterable<Key> keys()                 Returns a iterator containing all keys.
 */
public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> implements SortedST<Key, Value> {
    private DoubleNode<Key, Value> kvList;  // a double-linked-list to store the keys and values.
    private int n;                          // the number of the key-value pairs in the table.

    private class DoubleNode<K, V> {
        K key;
        V value;
        DoubleNode<K, V> prev;
        DoubleNode<K, V> next;

        DoubleNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        // using heading insertion method to initialize a new node.
        DoubleNode(K key, V value, DoubleNode<K, V> oldNode) {
            this.key = key;
            this.value = value;
            oldNode.prev = this;
            this.next = oldNode;
        }
    }

    public OrderedSequentialSearchST() {
        this.kvList = null;
        this.n = 0;
    }

    /**
     * Returns true if the symbol table is empty.
     *
     * @return {@code true} if the symbol table is empty;
     * {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Returns true if the given key exists in the table.
     *
     * @param key the given key.
     * @return {@code true} if the given key exists in the table;
     * {@code false} otherwise.
     * @throws IllegalArgumentException if the given key is null.
     */
    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key can not be null");
        return this.get(key) != null;
    }

    /**
     * Returns the number of the key-value pairs in the table.
     *
     * @return the number of the key-value pairs in the table.
     */
    @Override
    public int size() {
        return this.n;
    }

    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    /**
     * Put a new key-value pair into the table; update the value if the key already exists.
     *
     * @param key   the key of the given key-value pair.
     * @param value the value of the given key-value pair.
     * @throws IllegalArgumentException if the given key is null.
     */
    /*
    Complexity Analysis:
        TC: O(n)
        SC: O(1)
     */
    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("The given key can not be null");
        if (value == null) {
            delete(key);
            return;
        }
        // if the key already exists in table; update the value.
        DoubleNode<Key, Value> pNode = kvList;
        while (pNode != null) {
            if (pNode.key.compareTo(key) == 0) {
                pNode.value = value;
                return;
            }
            pNode = pNode.next;
        }

        // if the table is empty.
        if (this.isEmpty()) {
            kvList = new DoubleNode<>(key, value);
            n++;
            return;
        }

        pNode = kvList;
        // if the newNode is now the minimum one.
        if (pNode.key.compareTo(key) > 0) {
            kvList = new DoubleNode<>(key, value, kvList);
            n++;
            return;
        }
        // otherwise.
        while (pNode.next != null && pNode.next.key.compareTo(key) < 0) {
            pNode = pNode.next;
        }
        DoubleNode<Key, Value> newNode = new DoubleNode<>(key, value);
        // if the newNode is the maximum one.
        if (pNode.next == null) {
            newNode.prev = pNode;
            pNode.next = newNode;
            n++;
            return;
        }
        // insert the newNode.
        newNode.next = pNode.next;
        pNode.next.prev = newNode;
        pNode.next = newNode;
        newNode.prev = pNode;
        n++;
    }

    /**
     * Returns the value of the given key if the key exists in the table.
     *
     * @param key the given key.
     * @return the value of the given key if the key exists in the table;
     * {@code null} otherwise.
     * @throws IllegalArgumentException if the given key is null.
     */
    /*
    Complexity Analysis:
        TC: O(n)
        SC: O(1)
     */
    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is invalid(null)");

        DoubleNode<Key, Value> pNode = kvList;
        while (pNode != null) {
            if (pNode.key.compareTo(key) == 0) {
                return pNode.value;
            }
            pNode = pNode.next;
        }
        return null;
    }

    /**
     * Returns the maximum key in the table.
     *
     * @return the maximum key in the table.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public Key max() {
        if (this.isEmpty()) throw new UnsupportedOperationException("The table now is empty");
        // return the last node in the kvList.
        DoubleNode<Key, Value> pNode = kvList;
        while (pNode.next != null) {
            pNode = pNode.next;
        }
        return pNode.key;
    }

    /**
     * Returns the minimum key in the table.
     *
     * @return the minimum key in the table.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public Key min() {
        if (this.isEmpty()) throw new UnsupportedOperationException("The table now is empty");
        // return first key in table(minimum)
        return kvList.key;
    }

    /**
     * Returns the value of the deleted key-value pair containing the given key.
     *
     * @param key the given key.
     * @return the value of the deleted key-value pair containing the given key.
     * @throws IllegalArgumentException if the given key is null.
     */
    @Override
    public Value delete(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is null");
        DoubleNode<Key, Value> pNode = kvList;
        Value deleted = null;
        while (pNode != null) {
            if (pNode.key.compareTo(key) == 0) {
                deleted = pNode.value;
                if (pNode.next == null) {      // if the deleting node is the last one.
                    pNode.prev.next = null;
                    n--;
                } else {                         // otherwise.
                    pNode.next.prev = pNode.prev;
                    pNode.prev.next = pNode.next;
                    n--;
                }
                break;
            }
            pNode = pNode.next;
        }
        return deleted;
    }

    /**
     * Delete the maximum key in the table; and then return the value of it.
     *
     * @return the value of the maximum key in the table.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public Value deleteMax() {
        if (this.isEmpty()) throw new UnsupportedOperationException("the table now is empty");
        Value deleted = null;

        // if the table contains only one node.
        if (this.size() == 1) {
            deleted = kvList.value;
            kvList = null;
        } else {
            DoubleNode<Key, Value> pNode = kvList;
            while (pNode.next != null) {
                pNode = pNode.next;
            }
            pNode.prev.next = null;
        }
        n--;
        return deleted;
    }

    /**
     * Remove the minimum key in the table; and then returns its value.
     *
     * @return the value of the minimum key in the table.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public Value deleteMin() {
        if (this.isEmpty()) throw new UnsupportedOperationException("the table is now empty");
        Value deleted = kvList.value;
        kvList = kvList.next;
        kvList.prev = null;
        n--;
        return deleted;
    }

    /**
     * Returns the biggest key that smaller than the given key.
     *
     * @param key the given key
     * @return the biggest key that smaller than the given key if exists;
     * {@code null} otherwise.
     * @throws NoSuchElementException if the table is empty.
     */
    @Override
    public Key floor(Key key) {
        if (this.isEmpty()) throw new NoSuchElementException("The table is now empty");
        DoubleNode<Key, Value> pNode = kvList;
        while (pNode != null) {
            if (pNode.key.compareTo(key) == 0) {
                return (pNode.prev == null) ? pNode.key : pNode.prev.key;
            }
            pNode = pNode.next;
        }
        return null;
    }


    /**
     * Returns the smallest key that greater that the given key.
     *
     * @param key the given key.
     * @return the smallest key that greater that the given key if exists;
     * {@code null} otherwise.
     * @throws NoSuchElementException if the table is empty.
     */
    @Override
    public Key ceiling(Key key) {
        if (this.isEmpty()) throw new NoSuchElementException("The table contains no key-value pairs.");
        DoubleNode<Key, Value> pNode = kvList;
        while (pNode != null) {
            if (pNode.key.compareTo(key) == 0) {
                return (pNode.next == null) ? pNode.key : pNode.next.key;
            }
            pNode = pNode.next;
        }
        return null;
    }

    /**
     * Returns the k-th key in the table.
     *
     * @param k the index of the key
     * @return the k-th key in the table.
     * @throws NoSuchElementException if the index {@code k} is invalid.
     */
    @Override
    public Key select(int k) {
        if (k < 1 || k > this.size()) throw new NoSuchElementException("The index k out of the range");
        DoubleNode<Key, Value> pNode = kvList;
        for (int c = 1; c < k && pNode.next != null; c++) {
            pNode = pNode.next;
        }
        return pNode.key;
    }

    /**
     * Returns the index of the given key if it in the table.
     *
     * @param key the given key.
     * @return the index of the given key if it in the table;
     * {@code 0} otherwise.
     * @throws IllegalArgumentException      if the given key is null.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key can not be null");
        if (this.isEmpty()) throw new UnsupportedOperationException("The table is now empty");
        DoubleNode<Key, Value> pNode = kvList;
        for (int i = 1; pNode != null; i++) {
            if (pNode.key.compareTo(key) == 0) {
                return i;
            }
            pNode = pNode.next;
        }
        // if the key not in the table.
        return 0;
    }

    @Override
    public Iterable<Key> keys() {
        return new OrderedSequentialIter(select(1), select(n));
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return new OrderedSequentialIter(lo, hi);
    }

    private class OrderedSequentialIter implements Iterable<Key> {
        private DoubleNode<Key, Value> list = kvList;
        private Key end;

        OrderedSequentialIter(Key start, Key end) {
            this.end = end;
            while (list != null) {
                if (list.key.compareTo(start) == 0) break;
                list = list.next;
            }
        }

        @Override
        public Iterator<Key> iterator() {
            return new OrderedSequentialIterator();
        }

        private class OrderedSequentialIterator implements Iterator<Key> {
            @Override
            public boolean hasNext() {
                if (list == null) return false;       // When list is empty. avoid NullPointerException.
                return (list.key.compareTo(end) <= 0);
            }

            @Override
            public Key next() {
                if (!hasNext()) throw new NoSuchElementException();
                Key returned = list.key;
                list = list.next;
                return returned;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Unit test for this class {@code OrderedSequentialSearchST}
     *
     * @param args command arguments.
     */
    public static void main(String[] args) {
        OrderedSequentialSearchST<String, Integer> st = new OrderedSequentialSearchST<>();
        String[] test = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < test.length; i++) {
            st .put(test[i], i);
        }
        System.out.println("The input test symbol table:");
        for (String key : st .keys()) {
            System.out.println(key + " -> " + st .get(key) + " ");
        }
        System.out.println("The number of keys in symbol table is " + st .size() + ".");
        System.out.println("The rank of the key 'A' is : " + st .rank("A"));
        System.out.println(st .select(st .rank("A")).equals("A"));
    }
}
