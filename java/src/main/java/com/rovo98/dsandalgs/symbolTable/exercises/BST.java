package com.rovo98.dsandalgs.symbolTable.exercises;

import com.rovo98.dsandalgs.queue.ds.LinkedQueue;
import com.rovo98.dsandalgs.queue.ds.Queue;

/**
 * A simple implementation of interface {@code BinarySearchTreeST}.
 *
 * Sorted Symbol Table interface
 *
 * BST()                                Initialize a sorted symbol table.
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
 * void delete(Key key)                Delete the given key in the table if exists.
 * void deleteMin()                     Delete the minimum key in the table.
 * void deleteMax()                     Delete the maximum key in the table.
 * int size(Key lo, Key hi)             Returns the number of keys between the range of [lo, hi].
 * Iterable<Key> keys(Key lo, Key hi)   Returns a iterator containing the keys between the range of [lo, hi].
 * Iterable<Key> keys()                 Returns a iterator containing all keys.
 *
 * learning from algs4.
 *
 * @author rovo98
 * @date 2018.9.22
 */
public class BST<Key extends Comparable<Key>, Value> implements BinarySearchTreeST<Key, Value> {
    private Node root;

    // BinarySearch Tree Node defining here.
    private class Node {
        private Key key;
        private Value value;
        private int N;
        private Node left, right;

        Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * initialize the BST.
     */
    public BST() {
        this.root = null;
    }

    // tool for getting the number of the child nodes of one node.
    private int size(Node root) {
        if (root == null) return 0;
        else return root.N;
    }

    /**
     * Returns the number of the nodes of the BST.
     *
     * @return the number of the nodes of the BST.
     */
    @Override
    public int size() {
        return size(root);
    }

    private Value get(Node node, Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is invalid");
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.value;
    }

    /**
     * Get the value of the given key.
     *
     * @param key the given key.
     * @return the value of the given key if the key exists in the symbol table.
     * @throws IllegalArgumentException if the given key is invalid.
     */
    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Node put(Node node, Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("The given key can not be null");
        if (node == null) return new Node(key, value, 1);

        int cmp = key.compareTo(node.key);
        if      (cmp < 0)   node.left = put(node.left, key, value);
        else if (cmp > 0)   node.right =  put(node.right, key, value);
        else node.value = value;   // update the value if the key is already in the table.

        node.N = size(node.left) + size(node.right) + 1; // update the counter of this node.
        return node;
    }

    /**
     * Put the new key-value pair into the symbol table,
     * update the key-value pair if the pair is already exists.
     *
     * @param key   the key of the key-value pair.
     * @param value the value of the key-value pair.
     * @throws IllegalArgumentException if the given key is null.
     */
    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node delete(Node node, Key key) {
        int cmp = key.compareTo(node.key);
        if (cmp > 0) node.right = delete(node.right, key);
        else if (cmp < 0) node.left = delete(node.left, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node t = node;
            node = min(node.right);
            node.right = deleteMin(node.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }


    /**
     * Delete the key-value which contains the given key.
     *
     * @param key the given key.
     * @throws IllegalArgumentException      if the given key is invalid.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is invalid!");
        if (root == null) throw new UnsupportedOperationException("The table is empty!");
        root = delete(root, key);
    }

    /**
     * Returns true if the BST is empty.
     *
     * @return {@code true} if the BST is empty;
     * {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Returns true if the given key exists in the BST.
     *
     * @param key the given key.
     * @return {@code true} if the given key exists in the BST.
     * {@code false} otherwise.
     */
    @Override
    public boolean contains(Key key) {
        return this.get(key) != null;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    /**
     * Get the maximum key from the BST
     *
     * @return the maximum key in the BST.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public Key max() {
        if (isEmpty()) throw new UnsupportedOperationException("The table is now empty!");
        return max(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    /**
     * Get the minimum key from the BST.
     *
     * @return the minimum key in the BST.
     * @throws UnsupportedOperationException if the table is empty.
     */
    @Override
    public Key min() {
        if (root == null) throw new UnsupportedOperationException("The table now is empty!");
        return min(root).key;
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(node.left, key);
        else if (cmp > 0) return 1 + size(node.left) + rank(node.right, key);
        else return size(node.left);
    }

    /**
     * Returns the number of the keys that less than the given key.
     *
     * @param key the given key.
     * @return the number of the keys that less than the given key.
     * @throws IllegalArgumentException if the given key is invalid.
     */
    @Override
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is invalid");

        return rank(root, key);
    }

    private Node select(Node node, int k) {
        if (node == null) return null;

        // compare the k with the number of this node's left child nodes.
        int t = size(node.left);
        if (t > k) return select(node.left, k);
        else if (t < k) return select(node.right, k - t - 1);
        else return node;
    }

    /**
     * Returns the key at index in the table.
     * @param k the index of the key
     * @return the key at index in the table.
     */
    @Override
    public Key select(int k) {
        if (k <= 0 && k > this.size())
            throw new IllegalArgumentException("The given index 'k' is out of range!");
        Node result = select(root, k);
        return result == null ? null : result.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        else if (cmp > 0) return floor(node.right, key);

        Node t = floor(node.left, key);
        if (t != null) return t;
        else return node;
    }

    /**
     * Returns the minimum key that greater than or equals to the given key.
     *
     * @param key the given key.
     * @return the minimum key that greater than or equals to the given key.
     * @throws IllegalArgumentException if the given key is invalid.
     */
    @Override
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is invalid!");
        Node cNode = ceiling(root, key);
        if (cNode != null) return cNode.key;
        return null;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        else if (cmp < 0) return floor(node.left, key);

        Node t = floor(node.right, key);
        if (t != null) return t;
        else return node;
    }

    /**
     * Returns the maximum key that less or equals to the given key.
     *
     * @param key the given key.
     * @return the maximum key that less or equals to the given key.
     * {@code null} if the given key not in the table.
     * @throws IllegalArgumentException if the given key is invalid.
     */
    @Override
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("The given key is invalid!");
        Node fNode = floor(root, key);
        if (fNode == null) return null;
        return fNode.key;
    }


    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1; // update the counter.
        return node;
    }

    /**
     * Delete the minimum key from the BST.
     *
     * @throws UnsupportedOperationException if the symbol table is empty.
     */
    @Override
    public void deleteMin() {
        if (root == null) throw new UnsupportedOperationException("The BST is now empty!");
        root = deleteMin(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Delete the maximum key from the BST.
     *
     * @throws UnsupportedOperationException if the symbol table is empty.
     */
    @Override
    public void deleteMax() {
        if (root == null) throw new UnsupportedOperationException("The BST is now empty!");
        root = deleteMax(root);
    }


    /**
     * Returns an iterator containing all keys with ascending order.
     * @return an iterator containing all keys with ascending order.
     */
    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Returns an iterator containing keys between the key {@code lo} <br>
     *     and the key {@code hi}.
     * @param lo the left end of the keys range.
     * @param hi the right end of the keys range.
     * @return an iterator containing keys between the key {@code lo} and the
     * key {@code hi}.
     */
    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedQueue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
        if (cmphi > 0) keys(node.right, queue, lo, hi);
    }

    /**
     * Unit test for this class {@code BST}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        String[] test = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < test.length; i++) {
            bst.put(test[i], i);
        }
        System.out.println("The input test symbol table:");
        for (String key : bst.keys()) {
            System.out.println(key + " -> " + bst.get(key) + " ");
        }
        System.out.println("The number of keys in symbol table is " + bst.size() + ".");
        System.out.println("The rank of the key 'A' is : " + bst.rank("A"));
        System.out.println(bst.select(bst.rank("A")).equals("A"));
    }
}
