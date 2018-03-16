package com.rovo98.ds.uf.datastructures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ADT UF (树高度加权)
 *
 * Data
 *      int[] parent        // parent[i] = parent of i
 *      byte[] rank         // rank[i] = rank of subtree rooted at i (never more than 31)
 *      int count           the number of components
 * Operation
 *      UF()                            Initializes an empty union-find data structure with n sites.
 *                                      0 through n-1. Each site is initially in its own component.
 *      int find(int p)                 Returns the component identifier with component containing site p.
 *      void union(int p, int q)        Merges the component containing site p with the component containing site q.
 *      boolean connected(int p, int q) Returns true if site p and site q are in the same component.
 *      int count()                     Returns the number of components.
 * endADT
 * @author rovo98
 * Date: 15/2/2018
 * Learning from algs4.
 */
public class UF implements UF_API {
    private int[] parent;
    private byte[] rank;
    private int count;

    /**
     * Initializes an empty union-find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own component.
     * @param n the number of the sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    @Override
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // path compression by halving
            p = parent[p];
        }
        return p;
    }

    /**
     * Returns the number of components.
     * @return the number of components (between {@code 1} and {@code n})
     */
    @Override
    public int count() {
        return count;
    }

    /**
     * Returns true if the two sites are in the same component.
     *
     * @param p the integer representing one site.
     * @param q the integer representing the other site.
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *          {@code false} otherwise.
     * @throws IllegalArgumentException unless
     *          both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the component containing site {@code q}.
     *
     * @param p the integer representing one site.
     * @param q the integer representing the other site.
     * @throws IllegalArgumentException unless
     *          both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // make root of smaller rank point to root of larger rank.
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        }
        else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        }
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }
    // validate that p is a valid index.
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }


    /**
     * Unit tests {@code UF} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        UF uf = new UF(10);
        try {
            FileReader fr = new FileReader("E:\\Program Files\\ideaProjects\\" +
                    "JavaDataStructure\\src\\com\\rovo98\\ds\\uf\\exercise\\UF_test.txt");
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String s = br.readLine();
                if (s == null) {
                    break;
                }
                String[] sites = s.split("-");
                int site_1 = Integer.parseInt(sites[0]);
                int site_2 = Integer.parseInt(sites[1]);
                System.out.println(site_1 + " " + site_2);
                if (!uf.connected(site_1, site_2)) {
                    uf.union(site_1, site_2);
                } else {
                    System.out.println("These two sites are in the same component already.");
                }
            }
            System.out.println(uf.count() + " components!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
