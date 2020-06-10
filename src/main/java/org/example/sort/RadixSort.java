package org.example.sort;

import java.util.Arrays;

/**
 * 基数排序   桶排序的变形
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        System.out.println("排序前："+ Arrays.toString(arr));
        radixSort(arr);
    }

    public static void radixSort(int[] arr){
        // 获取最大元素
        int max = arr[0];
        for (int i=1;i<arr.length;i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //获取最大元素的位数
        int maxLength = (max + "").length();

        // 10个桶，每个桶是一个一维数组
        int[][] bucket = new int[10][arr.length];

        // 记录每个桶存放的数据个数
        int[] bucketElementCounts = new int[10];

        // 最大元素有多少位，循环多少次
        for (int i=0,n=1;i<maxLength;i++,n *= 10){
            // 依次取出各个元素的个位数，放到对应下标的桶中
            for (int j=0;j<arr.length;j++){
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k=0;k<bucketElementCounts.length;k++){
                if (bucketElementCounts[k] != 0){
                    for (int l=0;l<bucketElementCounts[k];l++){
                        arr[index++] = bucket[k][l];
                    }
                }
                // 每个桶中元素取完之后  清空
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+(i+1)+"轮排序后："+Arrays.toString(arr));
        }
    }
}
