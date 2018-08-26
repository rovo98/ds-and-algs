package com.rovo98.ds.symbolTable.exercises;

import com.rovo98.ds.symbolTable.datastructures.BinarySearchST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Read score and rank from a file. then print out the information.
 *
 *
 * @author : rovo98
 * date : 2018/8/16
 */
public class ScoreRankPrinter {
    /**
     * Unit test for interface {@code ST}
     * @param args command arguments.
     */
    public static void main(String[] args) {
        BinarySearchST<String, String> st = new BinarySearchST<>();
        try {
            Scanner scanner = new Scanner(new File(".//scoreRank.txt"));
            while (scanner.hasNext()) {
                st.put(scanner.next(), scanner.next());
            }
            for (String key : st.keys()) {
                System.out.println("Rank: " + key + " -> score: " + st.get(key));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
