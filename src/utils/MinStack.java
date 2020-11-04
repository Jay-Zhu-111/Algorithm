package utils;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {

    private final Stack<Integer> stack = new Stack<>();
    private final Stack<Integer> stack_min = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if(stack_min.isEmpty() || x <= stack_min.peek())
            stack_min.push(x);
        stack.push(x);
    }

    public void pop() {
        int temp = stack.pop();
        if(temp == stack_min.peek())
            stack_min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stack_min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
