package org.example.sort;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {-1,2,9,67,5,-2};
        System.out.println("排序前：" + Arrays.toString(arr));
//        bubbleSort(arr);

        for (int i = 1; i < arr.length -1; i++){
            if (arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第一轮："+Arrays.toString(arr));
        for (int i=1;i<arr.length - 1;i++){
            if (arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        System.out.println("第二轮："+Arrays.toString(arr));



//        System.out.println("排序后：" + Arrays.toString(arr));

//        int[] array = IntStream.rangeClosed(1, 80000).map((i) -> (int) (Math.random() * 80000000)).toArray();
//        String date1Str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println("排序前时间：" + date1Str);
//        bubbleSort(array);
//        String date2Str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println("排序后时间：" + date2Str);
    }

    public static void bubbleSort(int [] arr){
        // 双层循环
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    // 交换
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
