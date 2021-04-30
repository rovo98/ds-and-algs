package com.rovo98.dsandalgs.stack.algs;

import com.rovo98.dsandalgs.stack.ds.LinkedStack;
import com.rovo98.dsandalgs.stack.ds.Stack;

/**
 * An algorithm to sort a stack using recursion.
 *
 * It's also called "Reversing a stack".
 *
 * @author rovo98
 * Date: 4/3/2018
 *
 */
/*
The idea of the approach is to hold all values in function call stack until
the stack becomes empty.
When the stack becomes empty, insert all held items one by one in sorted order.

 */
public class SortAStackUsingRecursion {
    /**
     * Sort a stack using recursion.
     * @param stack the stack where need to be sorted.
     */
    public static void sortStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int temp = stack.pop();
            sortStack(stack);
            sortedInsert(stack, temp);
        }
    }

    /**
     * Insert the {@code item} into the right place of stack.
     * @param stack the stack where need to be modified.
     * @param item the item to insert
     */
    private static void sortedInsert(Stack<Integer> stack, int item) {
        if (stack.isEmpty() || item > stack.peek()) {
            stack.push(item);
        } else {
            int temp = stack.pop();
            sortedInsert(stack, item);
            stack.push(temp);
        }
    }

    /**
     * Unit tests the {@code sortStack}.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Stack<Integer>  stack = new LinkedStack<>();
        /*
        input: 1 - top
               2
               ..
               10

         output: 10 - top
                  9
                  ..
                  1
         */
        for (int i = 10; i > 0; i--) {
            stack.push(i);
        }
        System.out.println("The input test elements in stack are the following:");
        for (int elem : stack) {
            System.out.print(elem + " ");
        }
        System.out.println();
        sortStack(stack);
        System.out.println("After sorting the stack is becomes:");
        for (int elem : stack) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}
