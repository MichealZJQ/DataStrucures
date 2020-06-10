package org.example.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int [] arr = {25,36,1,-1,-69,987,68};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

//        int[] arr = IntStream.rangeClosed(1,80000).map(i -> (int)(Math.random() * 80000000)).toArray();
//        String date1Str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println("排序前时间：" + date1Str);
        insertSort(arr);

//        String date2Str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println("排序后时间：" + date2Str);

        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        for (int i = 1;i<arr.length;i++){
            int insertIndex = i-1;// 要插入的位置为排序位置的前一个，第一次进来的话，即56的前一个-1的下标0
            int insertVal = arr[i];// 要插入（排序）的值，第一次进来的话，即56
            // 若插入的值小于已排序取的最后一个值的话，就继续比较已排序区倒数第二个值，依次类推，直到找到不大于待插入值为止
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                // 已排序取的值往后移动一位
                arr[insertIndex + 1] = arr[insertIndex];
                // 插入位置指针往前移动，比较已排序区下一个
                insertIndex --;
            }
            // 循环结束 表示找到要插入的位置
            // 插入位置为insertIndex  + 1 ，因为insertIndex位置的值是小于insertVal的
            if (insertIndex + 1 == i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
