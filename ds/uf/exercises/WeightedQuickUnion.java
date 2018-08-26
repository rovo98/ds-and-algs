package com.rovo98.ds.uf.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ADT WeightedQuickUnion, 路径压缩加权Union-Find (节点和加权)
 *
 * Data
 *      int[] parent
 *      int[] rank
 *      int count
 * Operation
 *      WeightedQuickUnion(int n)           Initializes an empty weighted-Quick-Union date structure with n sites
 *                                          0 through n-1. Each site initially in its own component.
 *      int find(int p)                     Returns the component identifier for the component containing site p.
 *      void union(int p, int q)            Merges the component containing site p with the component containing site q.
 *      int count()                         Returns the number of components.
 *      void validate(int p)                Validate the site p.
 *      boolean connected(int p, int q)     Returns true if two sites in the same component.
 * endADT
 * @author rovo98
 * Date: 15/2/2018
 *
 */
public class WeightedQuickUnion {
    private int[] parent;
    private int[] rank;
    private int count;

    /**
     * Initializes an empty weighted-quick-union data structure with {@code n}
     * sites {@code 0} through {@code n-1}.
     * @param n the number of sites.
     * @throws IllegalArgumentException unless {@code n >= 0}
     */
    public WeightedQuickUnion(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     * @param p the integer representing one site.
     * @return the component identifier for the component containing site {@code p}.
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // path compression by halving.
            p = parent[p];
        }
        return p;
    }

    /**
     * Merges the component containing site {@code p} with the component containing site {@code q}.
     * @param p the integer representing one site.
     * @param q the integer representing the other site.
     * @throws IllegalArgumentException unless
     *          both {@code 0 <= p < n} and {@code 0 <= q < n}.
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        } else {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }
        count--;
    }
    // Validate the index p.
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p > n) {
            throw new IllegalArgumentException("Index " + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * Returns the number of components.
     * @return the number of components.
     */
    public int count() {
        return count;
    }

    /**
     * Returns true two sites are in the same components.
     * @param p the integer representing one site.
     * @param q the integer representing the other site.
     * @return {@code true} if two sites are in the same components;
     *         {@code false} otherwise.
     * @throws IllegalArgumentException unless
     *          both {@code 0 <= p < n} and {@code 0 <= q < n}.
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public static void main(String[] args) {
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        try {
            FileReader fr = new FileReader("E:\\Program Files\\ideaProjects\\" +
                    "JavaDataStructure\\src\\com\\rovo98\\ds\\uf\\exercise\\quick-find_for_wqf.txt");
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
                if (!wqu.connected(site_1, site_2)) {
                    wqu.union(site_1, site_2);
                }
            }
            System.out.println(wqu.count() + " components!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
