package com.ke.search;

/**
 * 插值查找
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-12-27 23:08
 **/
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 20);
        System.out.println("index = " + index);
    }

    /**
     * 插值查找，要求数组有序
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param finaVal 查找的值
     * @return 返回下标
     */
    public static int insertValueSearch(int[] arr, int left, int right, int finaVal) {
        System.out.println("查找次数");
        // 必须有判断条件
        if (left > right || finaVal < arr[0] || finaVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出 mid
        int mid = left + (right - left) * (finaVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        // 说明应该向右递归
        if (finaVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, finaVal);
        } else if (finaVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, finaVal);
        } else {
            return mid;
        }
    }
}
