package org.example.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);

        root.preOrder();
    }

    public static Node createHuffmanTree(int[] arr){
        // 将数组转换成从小到大的List<Node>
        List<Node> list = new ArrayList<>(arr.length);
        for (int i : arr) {
            list.add(new Node(i));
        }
        // 依次取出最小的两个元素，与两个元素的和构成一个数,直到list只剩下一个元素为止
        while (list.size() > 1){
            // 排序
            Collections.sort(list);

            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parentNode = new Node(leftNode.value+rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 移除出去的两个最小元素
            list.remove(leftNode);
            list.remove(rightNode);
            // 将父节点添加到list中
            list.add(parentNode);
        }
        // 最后集合中只剩下树的根节点，返回即可
        return list.get(0);
    }
}

class Node implements Comparable<Node>{

    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }
}
