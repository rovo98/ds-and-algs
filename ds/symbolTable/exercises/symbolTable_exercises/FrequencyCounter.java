package com.rovo98.ds.symbolTable.exercises;

import com.rovo98.ds.queue.datastructures.LinkedQueue;
import com.rovo98.ds.queue.datastructures.Queue;

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
    /**
     * For testing the class {@code BinarySearchSortedST}.
     * @param args command arguments.
     */
    public static void main(String[] args) {
        int minLen = 1;  // minimum key length
        BinarySearchSortedST<String, Integer> st = new BinarySearchSortedST<>();

        try {
            Scanner scanner = new Scanner(new File(".//tinyTale.txt"));
//            Scanner scanner = new Scanner(new File(".//tale.txt"));
            int count = 0;
            while (scanner.hasNext()) {
                String word = scanner.next();
                count++;
                if (word.length() < minLen) continue;
                if (!st.contains(word)) st.put(word, 1);
                else                    st.put(word, st.get(word) + 1);
            }
            System.out.println("We had processed " + count + " words.");
            // find all the most frequency word
            String max = "";
            Queue<String> queue = new LinkedQueue<>();
            st.put(max, 0);
            for (String word : st.keys()) {
                if (st.get(word) > st.get(max)) {
                    max = word;
                }
            }
            // add all the max frequency word into the queue.
            for (String word : st.keys()) {
                if (st.get(word).equals(st.get(max))) {
                    queue.enqueue(word);
                }
            }
            System.out.println("the most frequency word are the following:");
            for (String word : queue) {
                System.out.println("word: " + word + ", times: " + st.get(word));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
