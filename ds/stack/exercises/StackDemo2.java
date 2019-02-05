package com.rovo98.ds.stack.exercises;

import com.rovo98.ds.stack.datastructures.LinkedStack;

import java.io.*;

/**
 * 判断一组序列能不能通过栈的进栈和出栈的混合操作得到，序列由0到9组成。
 *
 * Example 1:
 * Input: 4321098765
 * Output: true
 * Explanation:
 * procession 1: push: 0, 1, 2, 3, 4
 *               pop: 4
 * procession 2: push:
 *               pop: 3
 * ...
 * procession 6:  push: 5, 6, 7, 8, 9
 *                pop: 9
 * procession 10: push:
 *                pop: 5
 *
 * @author rovo98
 * Date: 2/2/2018
 */
public class StackDemo2 {
    /*
    Complexity Analysis:
        Time complexity: O(n).
        Space complexity: O(1).
     */
    public boolean isLegalSequence(String sequence) {
        /*
        模拟序列生成的进栈和出栈混合操作
        遍历序列，模拟进栈，直到进栈数等于序列当前所在位置字符的值；
        模拟出栈，直到栈顶元素不再与序列当前位置字符值相等。
        重复以上操作直到序列遍历结束。
         */
        int index = 0;
        int count = 0;
        LinkedStack<Integer> stack = new LinkedStack<>();
        while (count < sequence.length()) {
            stack.push(count++);
            if (stack.peek() != Integer.parseInt("" + sequence.charAt(index))) { // 判断是否仍需进栈
                continue;
            }
            while (!stack.isEmpty()) {  // 模拟出栈操作
                if (stack.peek() == Integer.parseInt("" + sequence.charAt(index))) {
                    stack.pop();
                    index++;
                } else {
                    break;
                }
            }
        }
        // 栈为空，序列可操作得到，否则不可操作得到
       return stack.isEmpty();
    }

    /**
     * Unit tests the method {@code isLegalSequence()}
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        StackDemo2 sd = new StackDemo2();
        try {
            FileReader fr = new FileReader("E:"+ File.separator+"Program Files"+
                    File.separator+"ideaProjects"+File.separator+"javaDataStructure"+File.separator+
                    "src"+File.separator+"com"+File.separator+"rovo98"+File.separator+"ds"+
                    File.separator+"stack"+ File.separator+"exercises"+File.separator+"StackDemo2.txt");
//            FileReader fr = new FileReader("StackDemo2.txt");
            BufferedReader br = new BufferedReader(fr);
            String seq;
            int count = 0;
            while (true) {
                seq = br.readLine();
                if (seq == null) {
                    break;
                }
                count++;
                if (sd.isLegalSequence(seq)) {
                    System.out.println(count + ": " + seq + " is legal.");
                } else {
                    System.out.println(count + ": " + seq + " is illegal.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
