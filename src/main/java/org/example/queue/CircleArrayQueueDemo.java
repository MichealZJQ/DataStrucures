package org.example.queue;

import java.util.Scanner;

/**
 * 存在问题：
 * 队列的元素个数时队列长度-1
 * 有一个位置无法使用
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(5);
        char key = ' ';
        System.out.println("请输入要操作的指令：");
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            key = scanner.next().charAt(0);
            switch (key){
                case 's' -> queue.disPlayQueue();
                case 'a' -> {
                    System.out.println("请输入要添加的数据：");
                    queue.addQueue(scanner.nextInt());
                }
                case 'g' -> {
                    try{
                        int n = queue.getQueue();
                        System.out.println("取出数据为：" + n);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                case 'z' -> System.out.println("队列元素个数：" + queue.size());
                case 'e' -> {
                    scanner.close();
                    loop = false;
                }
                default -> System.out.println("请输入正确的指令");
            }
        }
    }
}

class CircleArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] val;

    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        val = new int[this.maxSize];
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满...");
            return;
        }
        val[rear] = n;
        rear = (rear + 1) % maxSize;
        System.out.println("添加成功");
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列没有数据...");
        }
        int ret = val[front];
        front = (front + 1) % maxSize;
        return ret;
    }

    public void disPlayQueue(){
        if (isEmpty()){
            System.out.println("队列没有数据...");
            return;
        }
        for (int i = front;i<front+size();i++) {
            System.out.printf("arr[%d]=%d", i % maxSize, val[i % maxSize]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
}
