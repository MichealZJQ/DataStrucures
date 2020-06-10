package org.example.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 前提是数组有序，
 * 若无序，则需要先排序 再查找
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {1,8,10,89,1000,1234};
//        int retVal = binarySearch(arr,0,arr.length-1,-8);
//        System.out.println(retVal);

//        int[] arr = {1,8,10,89,1000,1000,1000,1234};
//        List list = binarySearch2(arr, 0, arr.length - 1, 1000);
//        System.out.println(list);

        int[] arr = {1,3,4,5,6,8,8,8,11,18};
        int search3 = binarySearch3(arr, 0, arr.length - 1, 8);
        System.out.println(search3);

//        int[] arr = {1,3,4,5,6,8,8,8,11,18};
        int search4 = binarySearch4(arr, 0, arr.length - 1, 8);
        System.out.println(search4);

//        int[] arr = {1,3,4,5,6,8,8,8,11,18};
        int search5 = binarySearch5(arr, 0, arr.length - 1, 7);
        System.out.println(search5);

//        int[] arr = {1,3,4,5,6,8,8,8,11,18};
        int search6 = binarySearch6(arr, 0, arr.length - 1, 7);
        System.out.println(search6);

        System.out.println("=================================================");
        int bsearch1 = bsearch1(arr, arr.length, 8);
        System.out.println(bsearch1);
        int bsearch2 = bsearch2(arr, arr.length, 8);
        System.out.println(bsearch2);
        int bsearch3 = bsearch3(arr, arr.length, 7);
        System.out.println(bsearch3);
        int bsearch4 = bsearch4(arr, arr.length, 7);
        System.out.println(bsearch4);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){
        if (left > right){
            throw new RuntimeException("未找到要查找的值："+findVal);
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal == findVal){
            return mid;
        }else if (findVal > midVal){
            return binarySearch(arr,mid+1,right,findVal);
        }else {
            return binarySearch(arr,left,mid-1,findVal);
        }
    }

    public static List binarySearch2(int[] arr, int left, int right, int findVal){
        if (left > right || findVal > arr[arr.length - 1] || findVal < arr[0]){
            throw new RuntimeException("未找到要查找的值："+findVal);
        }
//        int mid = (left + right) / 2;// 若left和right很大，可能会超出int的取值范围
        int mid = left + (right - left) / 2;// 或者    left + ((right - left) >> 1)
        int midVal = arr[mid];
        if (midVal == findVal){
            List<Integer> retList = new ArrayList<>();
            // 向左扫描
            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                retList.add(temp);
                temp --;
            }
            // 向右扫描
            temp = mid + 1;
            while (temp <= arr.length && arr[temp] == findVal){
                retList.add(temp);
                temp ++;
            }
            // 将中间的值加进去
            retList.add(mid);
            return retList;
        }else if (findVal > midVal){
            return binarySearch2(arr,mid+1,right,findVal);
        }else {
            return binarySearch2(arr,left,mid-1,findVal);
        }
    }

    /**
     * 查找第一个值等于给定值的元素
     * {1,3,4,5,6,8,8,8,11,18}  查找8时，返回数组下标为5
     * @return
     */
    public static int binarySearch3(int[] arr, int left, int right, int findVal){
        if (left > right || findVal > arr[arr.length-1] || findVal < arr[0])
            throw new RuntimeException("未找到要查找的值："+findVal);

        int mid = left + ((right - left) >> 1);
        int midVal = arr[mid];
        if (findVal > midVal){
            return binarySearch3(arr,mid+1,right,findVal);
        }else if (findVal < midVal){
            return binarySearch3(arr,left,mid-1,findVal);
        }else {
            //相等 则继续判断左边一位是否也等于findValue
            while (mid != 0 && arr[mid - 1] == findVal){
                mid --;
            }
            return mid;
        }

    }

    /**
     * 查找最后一个值等于给定值的元素
     * {1,3,4,5,6,8,8,8,11,18}  查找8时，返回数组下标为7
     */
    public static int binarySearch4(int[] arr, int left, int right, int findVal){
        if (left > right || findVal > arr[arr.length-1] || findVal < arr[0])
            throw new RuntimeException("未找到要查找的值："+findVal);

        int mid = left + ((right - left) >> 1);
        int midVal = arr[mid];
        if (findVal > midVal){
            return binarySearch4(arr,mid+1,right,findVal);
        }else if (findVal < midVal){
            return binarySearch4(arr,left,mid-1,findVal);
        }else {
            //相等 则继续判断右边一位是否也等于findValue
            while (mid != arr.length-1 && arr[mid + 1] == findVal){
                mid ++;
            }
            return mid;
        }
    }

    /**
     * 查找第一个大于等于给定值的元素
     * {1,3,4,5,6,8,8,8,11,18}  查找7时，返回数组下标为5
     */
    public static int binarySearch5(int[] arr, int left, int right, int findVal){
        if (left > right || findVal > arr[arr.length-1] || findVal < arr[0])
            throw new RuntimeException("未找到要查找的值："+findVal);

        int mid = left + ((right - left) >> 1);
        int midVal = arr[mid];
        if (findVal > midVal){
            return binarySearch5(arr,mid+1,right,findVal);
        }else {
            while (mid != 0 && arr[mid - 1] >= findVal){
                mid--;
            }
            return mid;
        }
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * {1,3,4,5,6,8,8,8,11,18}  查找7时，返回数组下标为4
     */
    public static int binarySearch6(int[] arr, int left, int right, int findVal){
        if (left > right || findVal > arr[arr.length-1] || findVal < arr[0])
            throw new RuntimeException("未找到要查找的值："+findVal);

        int mid = left + ((right - left) >> 1);
        int midVal = arr[mid];
        if (findVal >= midVal){
            while (mid != arr.length-1 && arr[mid + 1] <= findVal){
                mid++;
            }
            return mid;
        }else {
            return binarySearch5(arr,left,mid-1,findVal);
        }
    }

    /**
     * 查找第一个值等于给定值的元素
     */
    public static int bsearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     */
    public static int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     */
    public static int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    public static int bsearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid =  low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
}
