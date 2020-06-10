package org.example.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {2,5,3,0,2,3,0,3};
        System.out.println("排序前："+ Arrays.toString(arr));
        countingSort(arr);
        System.out.println("排序后："+ Arrays.toString(arr));
    }

    public static void countingSort(int[] arr){
        // 获取最大
        int max = arr[0];
        for (int i=1;i<arr.length;i++){
            if (max < arr[i]){
                max = arr[i];
            }
        }

        int bucketSize = max + 1;
        int[] bucket = new int[bucketSize];
        for (int i=0;i<bucketSize;i++){
            bucket[i] = 0;
        }

        //计数
        for (int i=0;i<arr.length;i++){
            bucket[arr[i]] ++;
        }

        // 累加
        for (int i=1;i<=max;i++){
            bucket[i] = bucket[i-1] + bucket[i];
        }

        int[] temp = new int[arr.length];
        for (int i = arr.length-1;i>=0;i--){
            int index = bucket[arr[i]] - 1;
            temp[index] = arr[i];
            bucket[arr[i]] --;
        }

        // 将数组copy到原数组
        for (int i=0;i<arr.length;i++){
            arr[i] = temp[i];
        }
    }
}
