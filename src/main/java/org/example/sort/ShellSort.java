package org.example.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * todo 不明白
 */
public class ShellSort {

    public static void main(String[] args) {
        int [] arr = {8,9,1,7,2,3,5,4,6,0};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        shellSort2(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }
    //8,9,1,7,2,3,5,4,6,0
    public static void shellSort(int[] arr){
        for (int i = 5;i <arr.length;i++){
            for (int j= i-5;j>=0;j-=5) {
                if (arr[j] < arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        //3, 5, 1, 6, 0, 8, 9, 4, 7, 2
        for (int i = 2;i<arr.length;i++){
            for (int j = i - 2;i>=0;i-=2){
                if (arr[j] > arr[j+2]){
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
    }

    /**
     * 移位发
     * @param arr
     */
    public static void shellSort2(int[] arr){
        for(int gap=arr.length/2;gap>0;gap/=2){
            for (int i = gap;i<arr.length;i++){
                int index = i;
                int temp = arr[index];
                while (index - gap >= 0 && temp < arr[index - gap]){
                    arr[index] = arr[index - gap];
                    index -= gap;
                }

                arr[index] = temp;
            }
        }
    }
}
