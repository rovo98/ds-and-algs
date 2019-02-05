package com.rovo98.ds.stack.exercises;

import com.rovo98.ds.stack.datastructures.ArrayStack;

/**
 * 输入一串字符串(由字母和'-'组成), 使用栈处理，当遇到字母时进栈，遇到'-'时
 * ，将栈顶元素出栈。
 * Example 1:
 * Input: it was - the best - of times - - - it was - the - -
 * Output:was best times of the was the it,  1 string left on stack.
 *
 * @author rovo98
 * Date: 2/2/2018
 */
public class StackDemo1 {
    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        String[] tobe = {"it", "was", "-", "the", "best", "-", "of", "times",
                            "-", "-", "-", "it", "was", "-", "the", "-", "-"};
        for (int i = 0; i < tobe.length; i++) {
            String it = tobe[i];
            if (!it.equals("-")) {
                stack.push(it);
            } else {
                if (!stack.isEmpty()) {
                    System.out.print(stack.pop() + "  ");
                }
            }
        }
        System.out.print("("+stack.size()+" left on stack)");
    }
}
