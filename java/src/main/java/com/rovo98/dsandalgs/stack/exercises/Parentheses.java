package com.rovo98.dsandalgs.stack.exercises;

import com.rovo98.dsandalgs.stack.ds.LinkedStack;
import com.rovo98.dsandalgs.stack.ds.Stack;

import java.io.*;
import java.util.Objects;

/**
 * Stack exercise:
 * write a function using stack to find out whether a string containing parentheses
 * is valid or not.
 * example:
 * Input: {{()}}
 * Output: true
 *
 * Input: {}(}[]
 * Output: false
 * 使用栈判断括号匹配是否完整。
 *
 * @author rovo98
 * Date: 3/2/2018
 */
public class Parentheses {
    /*
    Complexity Analysis:
        Time complexity: O(n).
        Space complexity: O(n).
     */
    /**
     * Returns true if {@code charA} and {@code charB} is matched.
     * @param charA  a left parentheses character.
     * @param charB  a right parentheses character.
     * @return      {@code true} if charA and charB is matched;
     *              {@code false} otherwise.
     */
    private boolean isMatch(char charA, char charB) {
        if (charA == '(' && charB == ')') {
            return true;
        }
        else if (charA == '[' && charB == ']') {
            return true;
        }
        else if (charA == '{' && charB == '}') {
            return true;
        }
        return false;
    }

    /**
     * Returns true is parentheses in {@code s} is valid.
     * @param s     the input string which contains parentheses.
     * @return   {@code true} if s is valid;
     *           {@code false} otherwise.
     */
    public boolean isValidParentheses(String s) {
        final String LEFT_PARENTHESES = "[{(";
        final String RIGHT_PARENTHESES = "]})";

        Stack<Character> stack = new LinkedStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (LEFT_PARENTHESES.contains("" + c)) {
                stack.push(c);
            } else if (RIGHT_PARENTHESES.contains("" + c)) {
                if (isMatch(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Unit tests the method {@code isValidParentheses} above.
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        try {
            InputStream is = Parentheses.class.getClassLoader().getResourceAsStream("stack/Parentheses.txt");
            InputStreamReader ir = new InputStreamReader(Objects.requireNonNull(is));
            BufferedReader br = new BufferedReader(ir);
            String str;
            int count = 0;
            while (true) {
                str = br.readLine();
                if (str == null) {
                    break;
                }
                count++;
                if (p.isValidParentheses(str)) {
                    System.out.println(count + ": " + str + " is legal.");
                } else {
                    System.out.println(count + ": " + str + " is illegal.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
