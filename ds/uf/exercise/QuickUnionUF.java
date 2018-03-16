package com.rovo98.ds.uf.exercise;

import com.rovo98.ds.uf.datastructures.UF_API;

/**
 * ADT QuickUnionUF
 *
 * Data
 *      int[] parent
 *      int count
 * Operation
 *      QuickUnionUF(int n)             Initializes an empty union-find data structure with n sites
 *                                      0 through n-1. Each site initially in its own component.
 *      ind find(int p)                 Returns the component identifier for the component containing site p.
 *      void union(int p, int q)        Merges the component containing site p with the component containing site q.
 *      int count()                     Returns the number of the components.
 *      boolean connected(int p, int q) Returns true if two sites are in the same component.
 * endADT
 * @author rovo98
 * Date: 16/2/2018
 */
public class QuickUnionUF implements UF_API {
    private int[] parent; // parent[i] = i
    private int count;    // the number of components.

    /**
     * Initializes an empty union-find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site initially in its own component.
     *
     * @param n the number of sites.
     * @throws IllegalArgumentException unless {@code n > 0}
     */
    public QuickUnionUF(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site.
     * @return the component identifier for the component containing site {@code p}.
     * @throws IllegalArgumentException unless {@code 0 <= p < n}.
     */
    @Override
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
    // validate the site p.
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p > n) {
            throw new IllegalArgumentException("Index " + p + "is not between 0 and " +  (n-1));
        }
    }

    /**
     * Merges the component containing site {@code p} with the component containing site {@code q}.
     *
     * @param p the integer representing one site.
     * @param q the integer representing the other site.
     * @throws IllegalArgumentException unless
     *      both {@code 0 <= p < n} and {@code 0 <= q < n}.
     */
    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        parent[rootP] = rootQ;
    }

    /**
     * Returns the number of components.
     * @return the number of components.
     */
    @Override
    public int count() {
        return count;
    }

    /**
     * Returns true if two sites are in the same component.
     * @param p the integer representing one site.
     * @param q the integer representing the other site.
     * @return {@code true} if two sites are in the same component;
     *          {@code false} otherwise.
     * @throws IllegalArgumentException unless
     *      both {@code 0 <= p < n} and {@code 0 <= q < n}.
     */
    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Unit tests {@code QuickUnionUF} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {

    }
}
