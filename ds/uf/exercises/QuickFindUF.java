package com.rovo98.ds.uf.exercise;

import com.rovo98.ds.uf.datastructures.UF;
import com.rovo98.ds.uf.datastructures.UF_API;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ADT QuickFindUF
 *
 * Data
 *      int[]  parent
 *      int count
 * Operation
 *      QuickFindUF(int n)              Initializes an empty union-find data structure with n sites 0 through n-1.
 *                                      Each site initially in its own component.
 *      int find(int p)                 Returns the component identifier for the component containing site p.
 *      void union(int p, int q)        Merges the component containing site p with the component containing site q.
 *      int count()                     Returns the number of components.
 *      boolean connected(int p, int q) Returns true if two sites are in the same component.
 * endADT
 * @author rovo98
 * Date: 16/2/2018
 */
public class QuickFindUF implements UF_API {
    private int[]  parent; // parent[i] = i;
    private int count;     // the number of components.

    /**
     * Initializes an empty QuickFindUF data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site initially in its own component.
     * @param n the number of sites.
     * @throws IllegalArgumentException unless {@code n > 0}
     */
    public QuickFindUF(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * Returns the number of the components.
     * @return the number of the components.
     */
    @Override
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     * @param p the integer representing one site.
     * @return the component identifier for the component containing site {@code p}.
     * @throws IllegalArgumentException unless {@code 0 <= p < n}.
     */
    @Override
    public int find(int p) {
        validate(p);
        return parent[p];
    }
    // Validate the site p.
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p > n) {
            throw new IllegalArgumentException("Index " + p + " is not between 0 and " + (n-1));
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
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == rootP) {
                parent[i] = rootQ;
            }
        }
        count--;
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
     * Unit tests  {@code QuickFindUF} data type.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        UF uf = new UF(10);
        try {
            FileReader fr = new FileReader("../datastructures/UF_test.txt");
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
