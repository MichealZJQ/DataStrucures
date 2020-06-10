package org.example.hashtab;

import java.util.Scanner;

/**
 * 哈希表
 */
public class HashTab {
    private int size;
    private EmpLinkedList[] arr;

    public static void main(String[] args) {
        HashTab tab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key){
                case "add" -> {
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字：");
                    String name = scanner.next();
                    Emp emp = new Emp(id,name);
                    tab.addHash(emp);
                }
                case "find" -> {
                    System.out.println("请输入要查找的雇员id");
                    int eid = scanner.nextInt();
                    tab.find(eid);
                }
                case "list" -> tab.list();
                case "exit" -> {
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("请输入正确的命令");
            }
        }
    }

    public HashTab(int size){
        this.size = size;
        arr = new EmpLinkedList[size];

        //初始化
        for (int i=0;i<size;i++){
            arr[i] = new EmpLinkedList();
        }
    }

    /**
     * hashtab添加
     */
    public void addHash(Emp emp){
        int hid = calHash(emp.id);
        arr[hid].add(emp);
    }

    /**
     * hashtab查找
     */
    public void find(int id){
        int hid = calHash(id);
        arr[hid].findById(id);
    }

    /**
     * hashtab显示
     */
    public void list(){
        for (int i=0;i<size;i++){
            System.out.print("第" + (i+1) +"个链表的数据：" );
            arr[i].list();
        }
    }

    /**
     * 哈希函数
     */
    public int calHash(int id){
        return id % size;
    }
}


class EmpLinkedList{
    private Emp head;

    /**
     * 添加
     */
    public void add(Emp emp){
        if (head == null){
            head = emp;
            return;
        }

        Emp curr = head;
        while (curr.next != null){
            curr = curr.next;
        }
        curr.next = emp;
    }

    /**
     * 查找
     */
    public void findById(int id){
        if (head == null){
            System.out.println("链表为空");
            return;
        }
        Emp curr = head;
        while (true){
            if (curr == null){
                System.out.println("未找到要查询的雇员");
                break;
            }
            if (curr.id == id){
                System.out.println("找到查询的雇员信息[" + curr.id + "," + curr.name +"]");
                break;
            }
            curr = curr.next;
        }
    }

    /**
     * 显示
     */
    public void list(){
        if (head == null){
            System.out.println("链表为空");
            return;
        }

        Emp curr = head;
        while (curr != null){
            System.out.print(" => id: " + curr.id + ",name: " + curr.name);
            curr = curr.next;
        }
        System.out.println();
    }
}

class Emp{
    public Integer id;
    public String name;
    public Emp next;

    public Emp(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
