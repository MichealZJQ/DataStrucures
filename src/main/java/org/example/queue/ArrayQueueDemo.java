package org.example.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
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
                case 'd' -> queue.disPlayQueue();
                case 'e' -> {
                    scanner.close();
                    loop = false;
                }
                default -> System.out.println("请输入正确的指令");
            }
        }
    }
}

class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] val;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        val = new int[this.maxSize];
        front = rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满...");
            return;
        }
        val[++rear] = n;
        System.out.println("添加成功");
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列没有数据...");
        }
        return val[++front];
    }

    public void disPlayQueue(){
        if (isEmpty()){
            System.out.println("队列没有数据...");
            return;
        }
        for (int i=0;i<val.length;i++) {
            System.out.printf("arr[%d]=%d", i % maxSize, val[i % maxSize]);
            System.out.print(" ");
        }
        System.out.println();
    }
}
