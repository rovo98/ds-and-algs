package com.rovo98.dsandalgs.sorting.exercises;
import com.rovo98.dsandalgs.sorting.*;
//import edu.princeton.cs.algs4.Stopwatch;


/**
 * SortCompare
 * @author rovo98
 * Date: 17/2/2018
 * Learning from algs4.
 */
public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) {
            Insertion.sort(a);
        }
        if (alg.equals(("InsertionImproved"))) {
            Insertion.sortImproved(a);
        }
        if (alg.equals("Selection")) {
            Selection.sort(a);
        }
        if (alg.equals("Shell")) {
            Shell.sort(a);
        }
        if (alg.equals("ShellImproved")) {
            Shell.sortImproved(a);
        }
        if (alg.equals("Bubble")) {
            Bubble.sort(a);
        }
        if (alg.equals("Merge")) {
            Merge.sort(a);
        }
        return timer.elapsedTime();
    }
}
