package com.rovo98.dsandalgs.symbolTable.exercises;

import com.rovo98.dsandalgs.symbolTable.ds.BinarySearchST;

import java.io.InputStream;
import java.util.Objects;
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
        InputStream is = ScoreRankPrinter.class.getClassLoader().getResourceAsStream("symbol_table/scoreRank.txt");
        Scanner scanner = new Scanner(Objects.requireNonNull(is));
        while (scanner.hasNext()) {
            st.put(scanner.next(), scanner.next());
        }
        for (String key : st.keys()) {
            System.out.println("Rank: " + key + " -> score: " + st.get(key));
        }
    }
}
