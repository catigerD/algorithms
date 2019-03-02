package com.dengchong.algorithms.sort;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 8, 3, 6, 2, 5, 1, 0};
        int[] res = mergingSort(arr);
        println(res);
    }


    /*
     * 1. 从第一个元素开始,该元素可被认为已排序
     * 2. 取出下一个元素,在已排序的元素序列中从后向前扫描
     * 3. 如果该元素(已排序)大于新元素,将该元素移到下一位置
     * 4. 重复步骤3,直到找到已排序的元素小于或等于新元素
     * 5. 将新元素插入到该元素后.break
     * 6. 重复2-5步骤
     *
     * */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = i; j >= 0; j--) {
                if (j > 0 && arr[j - 1] > temp) { //1.注意边界条件array[j-1]中j>0;
                    arr[j] = arr[j - 1];
                } else {
                    arr[j] = temp;
                    break; //2.注意break
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { //arr.length - 1,最后一个元素不需要排序
            int min = i; //仅仅记录下标即可
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {//通过min是否变化判断
                swap(arr, i, min);
            }
        }
    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            max_heapify(arr, i);

            swap(arr, i, 0);
        }
    }

    private static void max_heapify(int[] arr, int limit) {
        int parentId = (limit - 1) / 2;
        for (; parentId >= 0; parentId--) {
            int left = parentId * 2 + 1;
            int right = (left + 1) > limit ? left : (left + 1);//右节点可能不存在
            int maxChild = arr[left] > arr[right] ? left : right;
            if (arr[maxChild] > arr[parentId]) {
                swap(arr, maxChild, parentId);
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            for (int j = 0; j < i && (j + 1) < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void quickSort(int[] arr, int low, int hight) {
        if (low >= hight) {
            return;
        }
        int left = low;
        int right = hight;
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= temp) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, hight);
    }

    public static int[] mergingSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int num = arr.length / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, num);
        int[] rightArr = Arrays.copyOfRange(arr, num, arr.length);
        return mergeTwoArray(mergingSort(leftArr), mergingSort(rightArr));
    }

    private static int[] mergeTwoArray(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        return result;
    }

    public static void println(int[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            System.out.print(arrs[i] + " ");
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
