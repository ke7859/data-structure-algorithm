package com.ke.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 文档：排序算法2.md
 * 链接：http://note.youdao.com/noteshare?id=d4880ee22ec4ee11eb219b3e7aca7bbd&sub=E22C8850A86C40A5AF66EA5B33ED5DB1
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-10-09 15:51
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {3, 4, 6, 7, 2, 7, 2, 8, 0};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序，8000000 条数据 770ms
     *
     * @param array
     * @param start
     * @param end
     */
    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            // 把数组中的第 0 个数字作为标准数
            int st = array[start];
            // 记录需要排序的下标
            int low = start;
            int high = end;
            // 循环找比标准数大的数和比标准数小的数
            while (low < high) {
                // 右边的数字比标准数大
                while (low < high && st <= array[high]) {
                    high--;
                }
                // 使用右边的数字替换左边的数字
                array[low] = array[high];

                // 如果左边的数字比标准数小
                while (low < high && st >= array[low]) {
                    low++;
                }
                array[high] = array[low];
            }

            // 把标准数赋给低或高所在的位置的元素
            array[low] = st;

            // 处理所有的小的数字
            quickSort(array, start, low);
            // 处理所有的大的数字
            quickSort(array, low + 1, end);
        }
    }
}
