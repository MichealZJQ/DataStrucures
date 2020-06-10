package org.example.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        int [] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr,int left,int right,int[] temp) {
        if (left < right){
            int mid = (left + right) /2 ;
            mergeSort(arr,left,mid,temp);// 左边拆解
            mergeSort(arr,mid+1,right,temp); // 右边拆解
            merge(arr,left,mid,right,temp); // 合并
        }
    }

    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        // 将mid左右两边看成是两个数组，合并放到temp中，最后将temp放到arr
        int i = left;//mid左边数组的指针
        int j = mid+1;//mid右边数据的指针
        int k = 0;//temp数组的指针
        while (i <= mid && j <= right){
            if (arr[i] > arr[j]){
                temp[k++] = arr[j++];
            }else {
                temp[k++] = arr[i++];
            }
        }
        // 处理剩余的数据，比如两边长度不等，一边遍历完，另外一边就需要把剩余的元素一次放到temp中
        while (i <= mid){// 左边还有剩余
            temp[k++] = arr[i++];
        }
        while (j<=right){//右边还有剩余
            temp[k++] = arr[j++];
        }

        // 将temp复制到arr
        k = 0;
        while (left <= right){
            arr[left++] = temp[k++];
        }
        System.out.println(Arrays.toString(arr));
    }
}