package org.example.tree;

/**
 * 顺序存储二叉树
 * 第n个节点的左子节点是  2*n+1
 * 第n个节点的右子节点是  2*n+2
 * 第n个节点的父节点是    (n-1)/2
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder(0);
    }
}

class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     */
    public void preOrder(int index){
        // 先输出当前节点
        System.out.println(arr[index]);
        // 输出左子节点
        if (2*index+1 < arr.length){
            preOrder(2*index+1);
        }
        if (2*index+2 < arr.length){
            preOrder(2*index+2);
        }
    }
}