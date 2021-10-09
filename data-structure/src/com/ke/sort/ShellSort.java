package com.ke.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 文档：排序算法2.md
 * 链接：http://note.youdao.com/noteshare?id=d4880ee22ec4ee11eb219b3e7aca7bbd&sub=E22C8850A86C40A5AF66EA5B33ED5DB1
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-10-09 14:20
 **/
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 交换法希尔排序，8000000数据 2100ms
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int temp = 0;
        for (int gap = array.length; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                // 遍历各组中所有的元素（共 gap 组，每组有 array.length / gap 个元素），步长 gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 移动法希尔排序，8000000数据 1500ms
     *
     * @param array
     */
    public static void shellSort2(int[] array) {
        int temp = 0;
        int index = 0;
        for (int gap = array.length; gap > 0; gap /= 2) {
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < array.length; i++) {
                index = i;
                temp = array[index];
                while (index - gap >= 0 && temp < array[index - gap]) {
                    // 移动
                    array[index] = array[index - gap];
                    index -= gap;
                }
                // 当退出 while 后，就给 temp 找到插入的位置
                array[index] = temp;
            }
        }
    }
}
