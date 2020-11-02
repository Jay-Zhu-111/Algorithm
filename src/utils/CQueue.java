package utils;

import java.util.Stack;

//8
class CQueue {

    private final Stack<Integer> inputStack = new Stack<>();
    private final Stack<Integer> outputStack = new Stack<>();

    public CQueue() {

    }

    public void appendTail(int value) {
        inputStack.push(value);
    }

    public int deleteHead() {
        if(outputStack.isEmpty()){
            while(!inputStack.isEmpty()){
                outputStack.push(inputStack.pop());
            }
            if(outputStack.isEmpty())
                return -1;
            else
                return outputStack.pop();
        }
        else{
            return outputStack.pop();
        }
    }
}

/*
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */