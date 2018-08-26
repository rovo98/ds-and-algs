package com.rovo98.ds.stack.exercises;

import com.rovo98.ds.stack.datastructures.LinkedStack;

/**
 * 使用两个栈实现四则运算表达式求值 -- Dijkstra 的双栈算术表达式求值算法
 *
 * @author rovo98
 * Date: 2/2/2018
 */
public class Evaluate {
    /**
     * Dijkstra 双栈算术表达式求值算法实现
     * @param expression  待计算求值的算术表达式
     * @return 计算所得的结果
     */
    public double compute(String expression) {
        LinkedStack<Character> ops = new LinkedStack<>();
        LinkedStack<Double> vals = new LinkedStack<>();

        for (int i = 0; i < expression.length(); i++) {
            // 如果是运算符压入栈
            char c = expression.charAt(i);
            if (c == '('){}
            else if (c == '+')
                ops.push(c);
            else if (c == '-')
                ops.push(c);
            else if (c == '*')
                ops.push(c);
            else if (c == '/')
                ops.push(c);
            else if (c == ')') { // 当遇到 ) 时， 弹出运算符和操作数，计算结果并压入栈
                char op = ops.pop();
                double v = vals.pop();
                if (op == '+')
                    v = vals.pop() + v;
                else if (op == '-')
                    v = vals.pop() - v;
                else if (op == '*')
                    v = vals.pop() * v;
                else if (op == '/')
                    v = vals.pop() / v;
                vals.push(v);
            }
            else {
                // 将操作数压入栈
                vals.push(Double.parseDouble(""+c));
            }
        }
        // 返回最终运算结果
        return vals.pop();
    }

    // Driver the program to test the method above.
    public static void main(String[] args) {
        Evaluate evaluate = new Evaluate();
        String testExp = "((5+3)*2)";
        double result = evaluate.compute(testExp);
        System.out.println("The result of expression "+testExp+" is "+result);
    }
}
