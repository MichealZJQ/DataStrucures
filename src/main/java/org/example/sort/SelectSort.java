package org.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
//        int [] arr = {25,36,1,-1,-69,987,68};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));

        int[] arr = IntStream.rangeClosed(1,80000).map(i -> (int)(Math.random() * 80000000)).toArray();
        String date1Str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("排序前时间：" + date1Str);
        selectSort(arr);

        String date2Str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("排序后时间：" + date2Str);

//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 每次查找出数组中最小的元素，然后与初始位置的值交换
     * @param arr
     */
    public static void selectSort(int[] arr){
        // 要进行arr.length-1轮排序
        for (int i=0;i<arr.length;i++){
            // 假定本轮最小值是第一个位置
            int minIndex = i;
            int min = arr[i];
            // 从第二个位置开始依次与当前最小值比较
            for (int j = i+1;j<arr.length;j++){
                if (min > arr[j]){
                    // 设置arr[j]为最小值
                    minIndex = j;
                    min = arr[j];
                }
            }
            // 一轮比较完后将最小值放到起始位置
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
