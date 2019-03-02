package com.dengchong.algorithms.sort;

public class Search {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 8, 3, 6, 2, 5, 1, 0};
        System.out.println(binarySearch(arr, 5));
    }

    //二分查找的前提是已排序数组
    public static boolean binarySearch(int[] array, int key) {
        Sort.quickSort(array, 0, array.length - 1);
        int low = 0;
        int height = array.length - 1;
        while (low <= height) {
            int mid = (low + height) / 2;
            if (array[mid] == key) {
                return true;
            } else if (array[mid] > key) {
                height = mid + -1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}
