package com.ke.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 文档：排序算法2.md
 * 链接：http://note.youdao.com/noteshare?id=d4880ee22ec4ee11eb219b3e7aca7bbd&sub=E22C8850A86C40A5AF66EA5B33ED5DB1
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-10-08 15:06
 **/
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1, 136, 185};
        selectSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    /**
     * 选择排序，时间复杂度 O(n²)
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 假设最小数的下标
            int minIndex = i;
            // 最小数的值
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                // 说明假设的最小值不是最小
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}
