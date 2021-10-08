package com.ke.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 文档：排序算法2.md
 * 链接：http://note.youdao.com/noteshare?id=d4880ee22ec4ee11eb219b3e7aca7bbd&sub=E22C8850A86C40A5AF66EA5B33ED5DB1
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-10-08 14:18
 **/
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, 20};
        bubbleSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    /**
     * 冒泡排序，时间复杂度 O(n²)
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        // 临时变量
        int temp = 0;
        // 优化冒泡排序，表示是否进行过交换
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 如果前面的数比后面的大，则交换
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            // 说明在一趟排序中，一次交换都没有发生
            if (!flag) {
                break;
            } else {
                // 重置 flag，进行下次判断
                flag = false;
            }
        }
    }
}
