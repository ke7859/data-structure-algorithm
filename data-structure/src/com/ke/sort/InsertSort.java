package com.ke.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 文档：排序算法2.md
 * 链接：http://note.youdao.com/noteshare?id=d4880ee22ec4ee11eb219b3e7aca7bbd&sub=E22C8850A86C40A5AF66EA5B33ED5DB1
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-10-08 16:06
 **/
public class InsertSort {

    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 插入排序
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < array.length; i++) {
            // 定义待插入的数
            insertVal = array[i];
            // 即 array[1] 的前面这个数的下标
            insertIndex = i - 1;
            // 给 insertVal 找到插入的位置，1保证数组不越界，2说明待插入的数还没有找到插入位置
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                // 将 array[insertIndex] 后移
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            // 当退出 while 循环时，说明插入的位置找到，为 insertIndex + 1
            array[insertIndex + 1] = insertVal;

        }
    }
}
