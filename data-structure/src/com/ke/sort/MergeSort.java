package com.ke.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 文档：排序算法2.md
 * 链接：http://note.youdao.com/noteshare?id=d4880ee22ec4ee11eb219b3e7aca7bbd&sub=E22C8850A86C40A5AF66EA5B33ED5DB1
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-10-09 20:06
 **/
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序，8000000 数据 1100ms
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void mergeSort(int[] arr, int low, int high) {
        // 中间索引
        int middle = (high + low) / 2;
        if (low < high) {
            // 处理左边，分
            mergeSort(arr, low, middle);
            // 处理右边，分
            mergeSort(arr, middle + 1, high);
            // 合
            merge(arr, low, middle, high);
        }
    }

    /**
     * 归并
     *
     * @param arr
     * @param low    从哪开始
     * @param middle 从哪分割，中间位置
     * @param high   从哪结束
     */
    public static void merge(int[] arr, int low, int middle, int high) {
        // 用于存储归并后的临时数组，结束位置 - 开始位置 + 1
        int[] temp = new int[high - low + 1];
        // 记录第一个数组中需要遍历的下标
        int i = low;
        // 记录第二个数组中需要遍历的下标
        int j = middle + 1;
        // 记录再临时数组中存放的下标
        int index = 0;
        // 遍历两个数组，取出小的数字，放入临时数组中
        while (i <= middle && j <= high) {
            // 第一个数组的数据更小
            if (arr[i] <= arr[j]) {
                // 把小的数据放入临时数组中
                temp[index] = arr[i];
                // 下标后移
                i++;
            } else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        // 处理多余的数据
        while (j <= high) {
            temp[index] = arr[j];
            j++;
            index++;
        }
        while (i <= middle) {
            temp[index] = arr[i];
            i++;
            index++;
        }

        // 把临时数组中的数据重新存入原数组
        for (int k = 0; k < temp.length; k++) {
            arr[k + low] = temp[k];
        }
    }
}
