package com.rovo98.ds.graph.algorithms;

/**
 * disjoin set(并查集) ：
 * Use data structure disjoin set to find out whether there is
 * a cycle in a simple connected and undirected graph.
 *
 * @author rovo98
 * Date: 17/2/2018
 */
class Graph {
    int V ;   // the number of the vertices
    int E ;   // the number of the edges
    Edge edge[] ; // collection of all edges

    class Edge {
        int src ;
        int dest ;
    }
    // Create a graph with V vertices and E edges
    public Graph(int V, int E) {
        this.V = V ;
        this.E = E ;
        edge = new Edge[E] ;
        for (int i=0; i<E; i++) {
            edge[i] = new Edge() ;
        }
    }
    // A utility function to the subset of an element
    public int find(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]]; // path halving.
            i = parent[i];
        }
        return i;
    }
    // A utility function to union two subsets
    public void union(int[] parent, byte[] rank, int x, int y) {
        int xset = find(parent, x) ;
        int yset = find(parent, y) ;
        // If two sites are already in the same component.
        if (xset == yset) {
            return;
        }
        // Always add the smaller tree to the bigger one.
        if (rank[xset] > rank[yset]) {
            parent[yset] = xset;
        } else if (rank[xset] < rank[yset]) {
            parent[xset] = yset;
        }
        else {
            parent[yset] = xset;
            rank[xset]++;
        }
    }
    public boolean isCycle(Graph graph) {
        // Allocate memory for creating V subsets
        int parent[] = new int[V];
        byte rank[] = new byte[V];

        // Initialize all subsets as single element set
        for (int i = 0; i < graph.V; i++) {
            // i means the representative of the subset is itself
            parent[i] = i;
            rank[i] = 0;
        }

        // Iterate through all edges of graph, find out subset of both of every edge
        // , if both subsets are same, then there is cycle in graph.
        for (int i = 0; i < graph.E; i++) {
            int x = graph.find(parent, graph.edge[i].src);
            int y = graph.find(parent, graph.edge[i].dest);
            if (x == y) {
                return true;
            }
            graph.union(parent, rank, x, y);
        }
        return false;
    }
}
public class DetectCycleDemo {
    public static  void main(String[] args) {
        /* Create the graph
        0
        | \
        |   \
        |     \
        1-------2
         */
        int V = 3;
        int E = 3;
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;

        // add edge 1-2
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;

        if (graph.isCycle(graph)) {
            System.out.println("graph contains cycle!");
        } else {
            System.out.println("graph doesn't contains cycle");
        }
    }
}
