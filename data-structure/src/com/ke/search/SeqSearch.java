package com.ke.search;

/**
 * 线性查找
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-12-12 16:25
 **/
public class SeqSearch {

    public static void main(String[] args) {
        // 没有顺序的数组
        int[] arr = {1, 9, 11, -11, 34, 89};
        int index = seqSearch(arr, 2);
        if (index == -1) {
            System.out.println("没有查找到");
        } else {
            System.out.println("找到了，下标为=" + index);
        }
    }

    /**
     * 这里我们实现的线性查找是找到一个，就返回
     *
     * @param arr
     * @param value
     * @return 返回下标 -1为未找到
     */
    public static int seqSearch(int[] arr, int value) {
        // 线性查找是逐一比对
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
