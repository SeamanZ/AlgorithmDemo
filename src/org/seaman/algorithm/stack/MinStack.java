package org.seaman.algorithm.stack;

import java.util.Stack;

//设计含最小函数min()的栈，要求min\push\pop的时间复杂度都是O(1)。min方法作用是：就能返回栈中的最小值
public class MinStack {
    Stack<Integer> stack = new Stack<>(); //定义用来存储数据的栈
    Stack<Integer> minStack = new Stack<>(); //定义用来存储最小数据的栈

    /**
     * 添加数据，首先是往stack栈中添加
     * 如果最小栈minStack为空，或者栈顶的元素比新添加的元素要大，则将新元素也要添加到辅助栈中
     */
    public void push(int node) {
        stack.push(node);
        if (minStack.isEmpty() || minStack.peek() >= node) {
            minStack.push(node);
        }
    }

    /**
     * 如果stack为空，直接返回
     * 如果stack不为空，得到栈顶元素，同时将栈顶元素弹出
     * 如果最小栈的栈顶元素与stack弹出的元素相等，那么最小栈也要将其弹出
     */
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int node = stack.pop();
        if (minStack.peek() == node) {
            minStack.pop();
        }
    }

    /**
     * 查看栈最小元素
     */
    public int min() {
        return minStack.peek();
    }
}
