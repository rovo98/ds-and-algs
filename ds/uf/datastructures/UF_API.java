package com.rovo98.ds.uf.datastructures;

/**
 * ADT UF_API
 *
 * Data
 *      int[] parent
 *      byte[] rank
 *      int count
 * Operation
 *      UF_API(int n)                   Initializes an empty union-find data structure with n sites
 *                                      0 through n-1. Each site is initially in its own component.
 *      int find(int p)                 Returns the component identifier for the component containing site p.
 *      int count()                     Returns the number of components.
 *      boolean connected(int p, int q) Returns true if the two sites are in the same component.
 *      void union(int p, int q)        Merges the component containing site p with the component containing q.
 * endADT
 * @author rovo98
 * Date: 15/2/2018
 */
public interface UF_API {
    int find(int p);
    int count();
    boolean connected(int p, int q);
    void union(int p, int q);
}
