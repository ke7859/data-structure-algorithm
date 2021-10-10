package com.ke.sort;

import java.util.Arrays;

/**
 * 基数排序
 * 文档：排序算法2.md
 * 链接：http://note.youdao.com/noteshare?id=d4880ee22ec4ee11eb219b3e7aca7bbd&sub=E22C8850A86C40A5AF66EA5B33ED5DB1
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-10-09 22:08
 **/
public class RadixSort {

    public static void main(String[] args) {
        int[] array = {53, 3, 542, 748, 14, 214};
        redisSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 基数排序，空间换时间，8000000 数据 520ms
     *
     * @param array
     */
    public static void redisSort(int[] array) {
        // 得到数组中最大数，假设第一个数就是最大
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 得到最大数的位数
        int maxLength = (max + "").length();

        // 定义一个二维数组，表示 10 个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][array.length];

        // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 针对每个元素对应的位进行排序处理，第一次个位，第二次百位，第三次千位……
            for (int j = 0; j < array.length; j++) {
                // 取出每个元素的对应位数的值
                int digitOfElement = array[j] / n % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            // 遍历每一个桶，并将桶中的数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    // 循环该桶，即第 k 个一维数组
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素放入到 array
                        array[index++] = bucket[k][l];
                    }
                }
                // 每一轮处理后，需要将每个 bucketElementCounts[k] 重置
                bucketElementCounts[k] = 0;
            }
        }
    }
}
