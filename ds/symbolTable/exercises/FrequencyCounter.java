package com.rovo98.ds.symbolTable.exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A demo for testing Sorted Symbol Table {@code BinarySearchSortedST}.
 *
 * @author : rovo98
 * date : 2018/8/15
 */
public class FrequencyCounter {
    public static void main(String[] args) {
        int minLen = 8;  // minimum key length
        BinarySearchSortedST<String, Integer> st = new BinarySearchSortedST<>();

        try {
            Scanner scanner = new Scanner(new File(".//tale.txt"));
//            Scanner scanner = new Scanner(new File(".//tinyTale.txt"));
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word.length() < minLen) continue;
                if (!st.contains(word)) st.put(word, 1);
                else                    st.put(word, st.get(word) + 1);
            }
            // find the most frequency word
            String max = "";
            st.put(max, 0);
            for (String word : st.keys()) {
                if (st.get(word) > st.get(max)) {
                    max = word;
                }
            }
            System.out.println("the most frequency word :" + max + ", times: " + st.get(max));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
