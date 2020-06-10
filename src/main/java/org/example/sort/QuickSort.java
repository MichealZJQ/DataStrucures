package org.example.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70,-1,900,4561};//,-1,900,4561
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        quickSort(arr,0,arr.length-1);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right){
        if (left < right){
            int pivot = getPivot(arr,left,right);
            quickSort(arr,left,pivot-1);
            quickSort(arr,pivot + 1,right);
        }
    }

    private static int getPivot(int[] arr, int left, int right) {
        int temp = arr[left];// 以第一个元素作为基准
        System.out.println("基准="+temp);
        while (left < right){
            // 先遍历右边，找到比基准小的元素，将次元素放到基准的位置
            while (left<right && temp <= arr[right]){
                right --;
            }
            arr[left] = arr[right];
            // 然后遍历左边，找到第一个比基准大的元素，将此元素放到right指针的位置
            while (left<right && temp >= arr[left]){
                left ++;
            }
            arr[right] = arr[left];
            // 不断循环，知道left>=right,退出循环
        }
        // 第一轮结束，此时的left=right，将基准放到这个位置，继续下一轮循环
        arr[left] = temp;
        System.out.println("本轮循环之后：" + Arrays.toString(arr));
        System.out.printf("本轮循环之后基准的位置：%s,基准值为：%d", left,arr[left]);
        System.out.println();
        return left;
    }
}
