package org.example.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        DoubleNode node1 = new DoubleNode(1,null,null);
        DoubleNode node2 = new DoubleNode(2,null,null);
        DoubleNode node3 = new DoubleNode(3,null,null);
        DoubleNode node4 = new DoubleNode(4,null,null);
        DoubleNode node5 = new DoubleNode(5,null,null);

        linkedList.addLastNode(node1);
        linkedList.addLastNode(node2);
        linkedList.addLastNode(node3);
        linkedList.addLastNode(node4);
        linkedList.addLastNode(node5);
        System.out.println("链表尾部添加之后：");
        linkedList.disPlay();
        System.out.println("===========================分隔线==============================");
        DoubleLinkedList linkedList1 = new DoubleLinkedList();
        DoubleNode node6 = new DoubleNode(6,null,null);
        DoubleNode node7 = new DoubleNode(7,null,null);
        DoubleNode node8 = new DoubleNode(8,null,null);
        DoubleNode node9 = new DoubleNode(9,null,null);
        DoubleNode node10 = new DoubleNode(10,null,null);

        linkedList1.addFirstNode(node6);
        linkedList1.addFirstNode(node7);
        linkedList1.addFirstNode(node8);
        linkedList1.addFirstNode(node9);
        linkedList1.addFirstNode(node10);
        System.out.println("链表头部添加之后：");
        linkedList1.disPlay();
        System.out.println("===========================分隔线==============================");

        linkedList.updateNode(new DoubleNode(1,null,null));

        System.out.println("===========================分隔线==============================");

        linkedList.delNode(new DoubleNode(2,null,null));
        linkedList.disPlay();

        System.out.println("===========================分隔线==============================");

        linkedList.addOrder(new DoubleNode(6,null,null));
        linkedList.addOrder(new DoubleNode(100,null,null));
        linkedList.addOrder(new DoubleNode(50,null,null));
        linkedList.disPlay();
    }
}

class DoubleLinkedList{

    private DoubleNode headNode = new DoubleNode(0,null,null);

    public DoubleNode getHeadNode() {
        return headNode;
    }

    /**
     * 后添加
     */
    public void addLastNode(DoubleNode node){
        DoubleNode cur = headNode;
        while (cur.next != null){
            cur = cur.next;
        }
        node.pre = cur;
        cur.next = node;
        System.out.println("尾部添加成功："+node);
    }

    /**
     * 顺序添加 后加
     */
    public void addOrder(DoubleNode node){
        DoubleNode cur = headNode;
        if (cur.next == null){
            cur.next = node;
            node.pre = cur;
            return;
        }
        while (cur.next != null){
            if (cur.next.val > node.val){
                break;
            }else if (cur.next.val == node.val){
                System.out.println("节点已存在，无法添加" + node);
                return;
            }
            cur = cur.next;
        }
        node.next = cur.next;
        node.pre = cur;
        if (cur.next != null){
            cur.next.pre = node;
        }
        cur.next = node;
        System.out.println("添加成功"+node);
    }

    /**
     * 前添加
     */
    public void addFirstNode(DoubleNode node){
        if (headNode.next == null){
            node.pre = headNode;
            headNode.next = node;
            System.out.println("头部添加成功："+node.val);
            return;
        }
        node.next = headNode.next;
        node.pre = headNode;
        headNode.next.pre = node;
        headNode.next = node;
        System.out.println("头部添加成功："+node.val);
    }

    /**
     * 修改
     */
    public void updateNode(DoubleNode node){
        DoubleNode cur = headNode.next;
        boolean flag = false;
        if (cur == null){
            System.out.println("链表为空...");
            return;
        }
        while (cur != null){
            if (cur.val == node.val){
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag){
            // TODO: 2020/4/11  修改操作

            System.out.println("修改完成");
        }else{
            System.out.println("未找到要修改的节点");
        }
    }

    /**
     * 删除
     */
    public void delNode(DoubleNode node){
        DoubleNode cur = headNode.next;
        boolean flag = false;
        if (cur == null){
            System.out.println("链表为空");
            return;
        }
        while (cur != null){
            if (cur.val == node.val){
                flag = true;
                break;
            }
            cur = cur.next;
        }

        if (flag){
            cur.pre.next = cur.next;
            if (cur.next != null){// 避免删除最后一个节点   NPE异常
                cur.next.pre = cur.pre;
            }
        }else {
            System.out.println("未找到要删除的节点");
        }
    }

    /**
     * 遍历
     */
    public void disPlay(){
        DoubleNode cur = headNode.next;
        if (cur == null){
            System.out.println("链表为空...");
            return;
        }
        while (cur != null){
            System.out.println(cur);
            cur = cur.next;
        }
    }
}

class DoubleNode{
    public int val;
    public DoubleNode pre;
    public DoubleNode next;

    public DoubleNode(int val, DoubleNode pre, DoubleNode next) {
        this.val = val;
        this.pre = pre;
        this.next = next;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "val=" + val +
//                ", pre=" + pre +
//                ", next=" + next +
                '}';
    }
}