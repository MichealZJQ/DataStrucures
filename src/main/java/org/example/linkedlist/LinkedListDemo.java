package org.example.linkedlist;

import java.util.Iterator;
import java.util.Stack;

public class LinkedListDemo {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Node node5 = new Node(10,null);
        Node node4 = new Node(7,null);
        Node node3 = new Node(4,null);
        Node node2 = new Node(2,null);
        Node node1 = new Node(1,null);

        linkedList.addOrderNode(node1);
        linkedList.addOrderNode(node3);
        linkedList.addOrderNode(node5);
        linkedList.addOrderNode(node4);
        linkedList.addOrderNode(node2);
        System.out.println("链表1：");
        linkedList.disPlay();

        LinkedList linkedList1 = new LinkedList();
        Node node6 = new Node(30,null);
        Node node7 = new Node(6,null);
        Node node8 = new Node(5,null);
        Node node9 = new Node(3,null);
        Node node10 = new Node(1,null);

        linkedList1.addOrderNode(node10);
        linkedList1.addOrderNode(node9);
        linkedList1.addOrderNode(node8);
        linkedList1.addOrderNode(node7);
        linkedList1.addOrderNode(node6);
        System.out.println("链表2：");
        linkedList1.disPlay();

        Node mergeLinked = LinkedList.mergeLinked(linkedList, linkedList1);
        System.out.println("合并之后链表：");
        Node cur = mergeLinked.next;
        while (cur != null){
            System.out.println(cur);
            cur = cur.next;
        }

//        linkedList.addOrderNode(node2);
//        linkedList.delNode(node2);
//        linkedList.disPlay();
//        int count = LinkedList.getCount(linkedList.getHeadNode());
//        System.out.println(count);
//
//        Node lastIndexNode = LinkedList.getLastIndexNode(linkedList.getHeadNode(), 5);
//        System.out.println(lastIndexNode);
//
//        System.out.println("链表反转：");
//        LinkedList.reverse(linkedList.getHeadNode());
//        linkedList.disPlay();
//        System.out.println("从尾到头打印：");
//        LinkedList.reverseLink(linkedList.getHeadNode());
    }


}

class LinkedList{
    private Node headNode = new Node(0,null);

    public Node getHeadNode() {
        return headNode;
    }

    public void addNode(Node node){
        Node temp = headNode;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        System.out.println("节点添加成功：" + node);
    }

    /**
     * 顺序添加
     * @param node
     */
    public void addOrderNode(Node node){
        Node temp = headNode;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.value > node.value){
                break;
            }else if (temp.next.value == node.value){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            throw new RuntimeException("添加的节点已经存在："+node);
        }
        node.next = temp.next;
        temp.next = node;
        System.out.println("节点添加成功：" + node);
    }

    /**
     * 修改
     */
    public void updateNode(Node node){
        Node temp = headNode.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.value == node.value){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            // 修改
            // TODO: 2020/4/11

            System.out.println("修改成功");
        }else {
            System.out.println("没有找到要修改的节点："+node);
        }
    }

    /**
     * 删除
     */
    public void delNode(Node node){
        Node temp = headNode;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.value == node.value){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            //删除
            temp.next = temp.next.next;
            System.out.println("删除成功");
        }else {
            System.out.println("未找到要删除的节点："+node);
        }
    }

    public void disPlay(){
        Node temp = headNode.next;
        while (true){
            if (temp == null){
                return;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 获取节点个数
     */
    public static int getCount(Node headNode){
        Node temp = headNode;
        int count = 0;
        while (true){
            if (temp.next == null)
                return count;
            temp = temp.next;
            count ++;
        }
    }

    /**
     * 获取倒数第K个节点
     */
    public static Node getLastIndexNode(Node headNode, int k){
        Node temp = headNode.next;
        int totalCnt = getCount(headNode);
        if (temp == null){
            return null;
        }
        if (k <= 0 || k > totalCnt){
            return null;
        }
        for (int i=0;i<totalCnt-k;i++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 链表反转 腾讯面试题
     */
    public static void reverse(Node headNode){
        Node reverseHeadNode = new Node(0,null);
        Node temp = headNode.next;
        if (temp == null || temp.next == null){// 为空或者只有一个则无需反转
            return;
        }
        Node next = null;// 保存当前节点的下一个节点 也就是head.next.next
        while (temp != null){
            // 方法一：
//            addInHead(reverseHeadNode,temp);
//            temp = temp.next;
            // 方法二：
            next = temp.next;
            temp.next = reverseHeadNode.next;
            reverseHeadNode.next = temp;
            temp = next;
        }
        headNode.next = reverseHeadNode.next;
    }

    /**
     * 头部添加节点
     */
    public static void addInHead(Node headNode, Node node){
        Node tempNode = new Node(node.value,null);
        if (headNode.next == null){
            headNode.next = tempNode;
            return;
        }
        tempNode.next = headNode.next;
        headNode.next = tempNode;
    }

    /**
     * 从尾到头打印
     * 方式一：可以先反转，然后打印，缺点：改变原始的链表
     * 方式二：利用栈结构的  先进后出
     */
    public static void reverseLink(Node headNode){
        Stack<Node> stack = new Stack<>();
        Node cur = headNode.next;
        while (cur != null){
            stack.add(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序单链表
     */
    public static Node mergeLinked(LinkedList linkedList1, LinkedList linkedList2){
        Node node1 = linkedList1.getHeadNode();
        Node node2 = linkedList2.getHeadNode();
        Node mergedHeadNode = new Node(0,null);
        Node cur1 = node1.next;
        Node cur2 = node2.next;
        if (cur1 == null && cur2 == null){
            return mergedHeadNode;
        }
        Node pre = mergedHeadNode;
        while (cur1 != null && cur2 != null){
            if (cur1.value <= cur2.value){
                pre.next = cur1;
                cur1 = cur1.next;
            }else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        // 解决一个遍历完 另外一个没遍历的问题
        pre.next = cur1 == null ? cur2 : cur1;
        return mergedHeadNode.next;
    }
}

class Node{
    public int value;
    public Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
//                ", next=" + next +
                '}';
    }
}
