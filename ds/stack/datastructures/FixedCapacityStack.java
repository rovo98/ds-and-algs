package com.rovo98.ds.stack.datastructures;

/**
 * ADT 定容栈 (FixedCapacityStack)
 *
 * Data:
 *      Item[] stack
 *      int N
 * Operation:
 *              FixedCapacityStackOfStrings(int cap)   创建一个容量为cap的空栈
 *      void    push(Item item)                        添加一个元素
 *      Item    pop()                                  删除最近添加的元素
 *      boolean isEmpty()                              栈是否为空
 *      int     size()                                 栈中的元素的数量
 *      void    reSize(int max)                        重新调整栈的大小
 *
 * endADT
 */
@SuppressWarnings("unchecked")
public class FixedCapacityStack<Item> {
    private Item[] stack;       // stack entries
    private int N;              // size
    public FixedCapacityStack(int cap) {
        this.stack = (Item[]) new Object[cap];
    }
    // 将元素压入栈顶
    public void push(Item item) {
        // 不再判断栈是否已经满，而是重新调整栈的容量
        if (N == this.stack.length) {
            reSize(2 * stack.length);  // 增加一倍的容量
        }
        this.stack[N++] = item;
    }
    // 栈顶元素出栈，动态调整栈的容量
    public Item pop() {
        Item item = stack[--N];
        stack[N] = null;      //  避免对象游离
        if (N > 0 && N == stack.length/4) {
            reSize(stack.length/2);
        }
        return item;
    }
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return this.N;
    }
    private void reSize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i=0; i<N; i++) {
            temp[i] = this.stack[i];
        }
        this.stack = temp;
    }
}
