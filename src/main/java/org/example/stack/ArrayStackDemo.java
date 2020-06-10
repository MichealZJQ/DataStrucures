package org.example.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.disPlay();
        stack.pop();
        stack.pop();
        stack.disPlay();
    }
}

class ArrayStack{
    private int maxSize;
    private int[] values;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        values = new int[maxSize];
    }

    /**
     * 是否满
     */
    public boolean isFull(){
        return top == maxSize-1;
    }

    /**
     * 是否空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int val){
        if (isFull()){
            System.out.println("栈已经满...");
            return;
        }
        values[++top] = val;
    }

    /**
     * 出栈
     */
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈为空...");
        }
        return values[top--];
    }

    /**
     * 显示栈
     */
    public void disPlay(){
        if (isEmpty()){
            System.out.println("栈为空...");
        }
        for (int i=top; i>=0; i--){
            System.out.println(values[i]);
        }
    }

    /**
     * 获取栈顶，不出栈
     */
    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("栈为空...");
        }
        return values[top];
    }
}