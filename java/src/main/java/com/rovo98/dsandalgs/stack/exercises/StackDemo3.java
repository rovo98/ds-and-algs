package com.rovo98.dsandalgs.stack.exercises;

import com.rovo98.dsandalgs.stack.ds.LinkedStack;
import com.rovo98.dsandalgs.stack.ds.Stack;

/**
 * 一个整数 N 的二进制表示.
 *
 *
 */
public class StackDemo3 {
    public String IntegerToBinary(int N) {
        Stack<Integer> stack = new LinkedStack<>();
        while (N > 0) {
            stack.push(N % 2);
            N /= 2;
        }
        String result = "";
        for (int d : stack) {
            result = result + d;
        }
        return result;
    }

    // Driver the program to test the method above.
    public static void main(String[] args) {
        StackDemo3 sd3 = new StackDemo3();
        final int N = 50;
        System.out.println(N + " 的二进制表示: " + sd3.IntegerToBinary(N));
    }
}
