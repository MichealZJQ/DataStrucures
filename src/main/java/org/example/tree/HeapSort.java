package org.example.tree;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        // 获取第一个非叶子节点a，调整以a为root的树为大顶堆，然后依次找前面的非叶子节点，进行调整
        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        /**
         * 将堆顶元素与末尾元素交换，将最大元素放到末尾
         * 继续调整堆结构，使其满足大顶堆，然后继续交换堆顶和末尾
         */
        int temp = 0;
        for (int j=arr.length-1;j>0;j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 再次调整
            adjustHeap(arr,0,j);
        }
    }

    /**
     * 将数组调整为大顶推
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        // 当前节点的左子节点2*i+1
        for (int k = 2*i+1;k<length;k=2*k+1){
            if (k+1<length && arr[k] < arr[k+1]){// 左子节点小于右子节点，则将k指向右子节点
                k++;
            }
            if (arr[k] > temp){// 子节点中最大的值大于父节点，则交换位置
                arr[i] = arr[k];
                i = k;// 交换完，将i指向k，继续往最大子节点的子节点查找
            }else {
                break;
            }
        }
        arr[i] = temp;
    }
}
