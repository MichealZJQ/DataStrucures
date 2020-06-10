package org.example.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo2 {
    public static void main(String[] args) {
        CircleArrayQueue2 queue = new CircleArrayQueue2(5);
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

class CircleArrayQueue2{
    private int maxSize;
    private int front;
    private int rear;
    private int[] val;
    private int count;

    public CircleArrayQueue2(int maxSize){
        this.maxSize = maxSize;
        val = new int[this.maxSize];
        front = rear = -1;
    }

    public boolean isFull(){
        return count == maxSize;

    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满...");
            return;
        }
        rear = (rear + 1) % maxSize;
        val[rear] = n;
        count ++;
        System.out.println("添加成功");
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列没有数据...");
        }
        front = (front + 1) % maxSize;
        int ret = val[front];
        count--;
        return ret;
    }

    public void disPlayQueue(){
        if (isEmpty()){
            System.out.println("队列没有数据...");
            return;
        }
        for (int i = front + 1;i<front+1+size();i++) {
            System.out.printf("arr[%d]=%d", i % maxSize, val[i % maxSize]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public int size(){
        return count;
    }
}
