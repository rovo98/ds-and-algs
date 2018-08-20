package com.rovo98.ds.symbolTable.exercises.symbolTable_exercises;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author : rovo98
 * date : 2018/8/14
 *
 * Sorted Symbol Table interface
 *
 * SortedST()                      Initialize a sorted symbol table.
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
 * Value delete(Key key)                Delete the given key in the table if exists.
 * void deleteMin()                     Delete the minimum key in the table.
 * void deleteMax()                     Delete the maximum key in the table.
 * int size(Key lo, Key hi)             Returns the number of keys between the range of [lo, hi].
 * Iterable<Key> keys(Key lo, Key hi)   Returns a iterator containing the keys between the range of [lo, hi].
 * Iterable<Key> keys()                 Returns a iterator containing all keys.
 *
 * learning from algs4.
 */
public class BinarySearchSortedST<Key extends Comparable<Key>, Value> implements SortedST<Key, Value> {
    private Key[] keys;     // Array to store keys
    private Value[] values; // Array to store values.
    private int n;          // the number of the key-value pairs.

    /**
     * Initialize a empty sorted Symbol Table.
     */
    @SuppressWarnings("unchecked")
    public BinarySearchSortedST() {
        this.keys = (Key[]) new Comparable[1];
        this.values = (Value[]) new Object[1];
        this.n = 0;
    }


    /**
     * A tool function to resizing the arrays
     *
     * @param newSize the new size of the arrays.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Key[] copyKeys = (Key[]) new Comparable[newSize];
        Value[] copyValues = (Value[]) new Object[newSize];
        System.arraycopy(keys, 0, copyKeys, 0, n);
        System.arraycopy(values, 0, copyValues, 0, n);
        keys = copyKeys;
        values = copyValues;
    }

    /**
     * A tool function using {@code binarySearch} to verify whether the given key
     * was in the table or not.
     *
     * @param key the given key to verify.
     * @return {@code index} of the given key if it in the table;
     * {@code -1} otherwise.
     */
    private int binarySearch(Key key) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int flag = keys[mid].compareTo(key);
            if (flag == 0) return mid;
            else if (flag > 0) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    /**
     * Returns the number of key-values pairs in the symbol table.
     *
     * @return the number of key-values paris in the symbol table.
     */
    /*
    Complexity Analysis:
        TC : O(1)
        SC : O(1)
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns the number of the keys between the key {@code lo} and the key {@code hi}.
     *
     * @param lo the startIndex key
     * @param hi the endIndex key.
     * @return the number of the keys between the key {@code lo} and the key {@code hi}
     * @throws IllegalArgumentException if the key {@code lo} or the key {@code hi} was invalid.
     */
    /*
    Complexity Analysis:
        TC : O(log N)
        SC : O(1)
     */
    @Override
    public int size(Key lo, Key hi) {
        if (lo == null || hi == null) throw new IllegalArgumentException("The given keys can not be null");
        int lIndex = this.binarySearch(lo);
        int rIndex = this.binarySearch(hi);
        if (lIndex < 0 || rIndex < 0) throw new IllegalArgumentException("The given keys are invalid");
        return (rIndex - lIndex) + 1;
    }

    /**
     * Returns trues if the given key in the symbol table.
     *
     * @param key the key to validate.
     * @return {@code true} if the given {@code key} in the table;
     * {@code false} otherwise.
     * @throws IllegalArgumentException if the given key is null.
     */
    /*
    Complexity Analysis:
        TC : O(log N)
        SC : O(1)
     */
    @Override
    public boolean contains(Key key) {
        return this.get(key) != null;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if the table is empty;
     * {@code false} otherwise.
     */
    /*
    Complexity Analysis:
        TC : O(1)
        SC : O(1)
     */
    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    /**
     * Put a key-values pair into the symbol table.
     *
     * @param key   the key of the key-value pair.
     * @param value the values of the key-value pair
     * @throws UnsupportedOperationException if the key is null.
     * @throws NoSuchElementException if hte value is null and the given key is not in the table.
     */
    /*
    Complexity Analysis:
        TC : O(N) for insertion sorting loop
        SC : O(1)
     */
    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new UnsupportedOperationException("The key can not be null");
        if (value == null) {
            delete(key);
            return;
        }
        int index = this.binarySearch(key);
        if (index > -1) {               // if the key is already exists.
            values[index] = value;
            return;
        }
        if (n == keys.length) {
            resize(keys.length * 2);
        }
        // using the idea of the insertion sorting algorithm to make the keys sorted.
        int j = n - 1;
        while (j >= 0 && keys[j].compareTo(key) > 0) {
            keys[j + 1] = keys[j];
            values[j + 1] = values[j];
            j--;
        }
        keys[j + 1] = key;
        values[j + 1] = value;
        n++;
    }

    /**
     * Returns the value of the given key if the key exists in the table.
     *
     * @param key the key of a key-value pair.
     * @return the {@code value} of the given {@code key} if the key exists in the table;
     * {@code null} otherwise.
     * @throws IllegalArgumentException if the given key is null.
     */
    /*
    Complexity Analysis:
        TC : O(log N) for binary Searching
        SC : O(1)
     */
    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key can not be null");
        int index = this.binarySearch(key);
        if (index > -1) return values[index];
        return null;
    }

    /**
     * Returns the maximum key in the symbol table.
     *
     * @return the maximum key in the symbol table.
     * @throws UnsupportedOperationException if the table is empty.
     */
    /*
    Complexity Analysis:
        TC : O(1)
        SC : O(1)
     */
    @Override
    public Key max() {
        if (this.isEmpty()) throw new UnsupportedOperationException("The table now is empty");
        return keys[n];
    }

    /**
     * Returns the minimum key in the symbol table.
     *
     * @return the minimum key in the symbol table.
     * @throws UnsupportedOperationException if the table is empty.
     */
    /*
    Complexity Analysis:
        TC : O(1)
        SC : O(1)
     */
    @Override
    public Key min() {
        if (this.isEmpty()) throw new UnsupportedOperationException("The table now is empty");
        return keys[0];
    }

    /**
     * Remove the key-value pair which contains the given {@code key}, and return the {@code value} of the pair.
     *
     * @param key the key of the key-value pair to be deleted.
     * @return {@code value} of the deleted key-value pair.
     * @throws NoSuchElementException if the key not in the table.
     */
    /*
    Complexity Analysis:
        TC : O(N) for moving the elements.
        SC : O(1)
     */
    @Override
    public Value delete(Key key) {
        int index = this.binarySearch(key);
        if (index < 0) throw new NoSuchElementException("The key is not in the table");
        Value deleted = values[index];
        keys[index] = null;
        values[index] = null;
        for (int i = index; i < n; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        n--;
        if (n == keys.length / 4) {
            resize(keys.length / 2);
        }
        return deleted;
    }

    /**
     * Delete the maximum key in the table and return its value.
     *
     * @return value of the maximum key.
     */
    /*
    Complexity Analysis:
        TC : O(N)
        SC : O(1)
     */
    @Override
    public Value deleteMax() {
        return delete(max());
    }

    /**
     * Delete the minimum key in the table and return its value.
     *
     * @return value of the minimum key.
     */
    /*
    Complexity Analysis:
        TC : O(N)
        SC : O(1)
     */
    @Override
    public Value deleteMin() {
        return delete(min());
    }

    /**
     * Get the value of the most closest key that smaller than the given key.
     *
     * @param key the given key
     * @return the value of the most closest key that smaller than the given key.
     * @throws NoSuchElementException if the given key is not in the table.
     */
    /*
    Complexity Analysis:
        TC : O(log N)
        SC : O(1)
     */
    @Override
    public Key floor(Key key) {
        int index = this.binarySearch(key);
        if (index < 0) throw new NoSuchElementException("the key is not in the table");
        return (index == 0) ? keys[index] : keys[index - 1];
    }

    /**
     * Get the value of the most closest key that greater than the given key.
     *
     * @param key the given key
     * @return the value of the most closest key that greater than the given key.
     * @throws NoSuchElementException if the given key is not in the table.
     */
    /*
    Complexity Analysis:
        TC : O(log N)
        SC : O(1)
     */
    @Override
    public Key ceiling(Key key) {
        int index = this.binarySearch(key);
        if (index < 0) throw new NoSuchElementException("the key is not in the table");
        return (index == (n - 1)) ? keys[index] : keys[index + 1];
    }

    /**
     * Returns the number of the key that smaller than the given {@code key}.
     *
     * @param key the given key
     * @return the number of the keys that smaller than the given {@code key};<br>
     * returns {@code 0} if the key not in the table.
     */
    /*
    Complexity Analysis:
        TC : O(log N)
        SC : O(1)
     */
    @Override
    public int rank(Key key) {
        int index = this.binarySearch(key);
        return (index < 0) ? 0 : index;
    }

    /**
     * Get k-th key from the symbol table.
     *
     * @param k the index of the key
     * @return the k-th key in the symbol table.
     * @throws IllegalArgumentException if the {@code k} is out of the range of [1, n]
     */
    /*
    Complexity Analysis:
        TC : O(1)
        SC : O(1)
     */
    @Override
    public Key select(int k) {
        if (k < 1 || k > n) throw new IllegalArgumentException("k is out of the range of [1, n]");
        return keys[k - 1];
    }

    /**
     * Returns a iterator that contains all keys in the table in the ascending order.
     *
     * @return a iterator that contains all the keys in the table in ascending order.
     */
    /*
    Complexity Analysis:
        TC : O(1)
        SC : O(1)
     */
    @Override
    public Iterable<Key> keys() {
        return new BinarySearchSortedSTIterator(select(1), select(n));
    }

    /**
     * Returns a iterator that contains the keys between the key {@code lo} and the key {@code hi}.
     *
     * @param lo the startIndex key
     * @param hi the endIndex key.
     * @return A iterator that contains the keys between the key {@code lo} and the key {@code hi}
     * @throws IllegalArgumentException if the key {@code lo} or the key {@code hi} was invalid.
     */
    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null) throw new IllegalArgumentException("Invalid keys given");
        return new BinarySearchSortedSTIterator(lo, hi);
    }

    private class BinarySearchSortedSTIterator implements Iterable<Key> {
        private int startIndex;
        private int endIndex;

        BinarySearchSortedSTIterator(Key lo, Key hi) {
            this.startIndex = binarySearch(lo);
            this.endIndex = binarySearch(hi);
        }

        @Override
        public Iterator<Key> iterator() {
            return new STIterator();
        }

        private class STIterator implements Iterator<Key> {
            private int n = startIndex;

            @Override
            public boolean hasNext() {
                return n < endIndex;
            }

            @Override
            public Key next() {
                if (!hasNext()) throw new NoSuchElementException();
                return keys[n++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Unit test for this class {@code BinarySearchSortST}
     *
     * @param args the command arguments.
     */
    public static void main(String[] args) {
        BinarySearchSortedST<String, Integer> BST = new BinarySearchSortedST<>();
        String[] test = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < test.length; i++) {
            BST.put(test[i], i);
        }
        System.out.println("The input test symbol table:");
        for (String key : BST.keys()) {
            System.out.println(key + " -> " + BST.get(key) + " ");
        }
        System.out.println("The number of keys in symbol table is " + BST.size() + ".");
    }
}
