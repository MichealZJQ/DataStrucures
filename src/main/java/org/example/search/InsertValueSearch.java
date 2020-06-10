package org.example.search;

/**
 * 插值查找
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        // 在二分查找的基础上将改变mid的计算方式
        // int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left])
        // 当 int[] arr = {1,2,3,4,5,6,.......100};时，若要查找 1或者100，则插值查找的算法要比二分查找快很多
    }
}
