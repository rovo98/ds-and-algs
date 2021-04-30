package com.rovo98.dsandalgs.queue.exercises.pq;


/**
 * Given a string containing a serious of characters
 * The alpha character means put the character  into queue, otherwise remove the maximum character
 *
 * @author rovo98
 * Date: 2018.3.22
 */
public class MaxPQDemo {
    public static void main(String[] args) {
        String inputSeq = "PRIO*R**I*TY***QUE***U*E";
        MaxPQ<Character> pq = new HeapMaxPQ<>();
        int len = inputSeq.length();
        String deleted = "";
        for (int i = 0; i < len; i++) {
            if (inputSeq.charAt(i) == '*') {
                deleted = deleted.concat("" + pq.delMax());
            } else {
                pq.insert(inputSeq.charAt(i));
            }
        }

        System.out.println("The input sequence is " + inputSeq);
        System.out.println("And the result is :" + deleted);
    }
}
