package org.example.linkedlist;

/**
 * 约瑟夫问题  循环单链表
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(50);
        linkedList.disPlay();

        linkedList.countBoy(1,2,50);
    }
}

class CircleSingleLinkedList{
    private Boy first = null;

    public void addBoy(int num){
        if (num < 1){
            System.out.println("请输入正确的人数");
            return;
        }
        Boy cur = null;
        for (int i=1;i<=num;i++){
            Boy boy = new Boy(i);
            if (1==i){
                first = boy;
                boy.next = boy;
                cur = first;
            }else{
                cur.next = boy;
                cur = cur.next;
                boy.next = first;
            }
        }
    }

    public void disPlay(){
        if (first == null){
            System.out.println("当前没有小孩");
            return;
        }
        Boy cur = first;
        while (true){
            System.out.println(cur);
            if (cur.next != first){
                cur = cur.next;
            }else {
                break;
            }
        }
    }

    /**
     * 出圈   约瑟夫问题
     * @param startNo 开始数数的编号
     * @param countNum 每次数几个数
     * @param nums 共有多少个小孩
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("请输入正确的参数");
            return;
        }
        // 辅助指针，指向最后一个节点
        Boy helper = first;
        while (helper.next != first){
            helper = helper.next;
        }
        // first和helper移动开始数数的位置
        for (int i=0;i<startNo-1;i++){
            first = first.next;
            helper = helper.next;
        }
        // 开始数数
        while(helper != first){
            // 往后数countNum-1个位置
            for (int j=0;j<countNum - 1;j++){
                helper = helper.next;
                first = first.next;
            }
            // 此时first指针所指的位置就是要删除的节点
            System.out.println(first.no+"出圈");
            first = first.next;
            helper.next = first;
        }
        //最后只剩下一个节点
        System.out.println(first.no + "最后出圈");
    }
}

class Boy{
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
