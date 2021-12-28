package com.ke.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找，数组必须是有序的
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-12-12 16:57
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 89, 89, 1000, 1024};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 89);
        System.out.println("结果下标=" + resIndex);
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 89);
        System.out.println("结果下标集：" + list);
    }

    /**
     * 二分查找
     *
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 查找的值
     * @return 找到就返回下标，否则返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 当left > right时，说明递归整个数组没有找到
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        // 向右递归
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        }
        // 向左递归
        else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找找到数组中所有符合查找值的下标
     * 思路：
     * 1. 在找到 mid 索引值，不要马上返回
     * 2. 向 mid 索引值的左边扫描，将所有满足查找值的元素的下标加入到集合ArrayList中
     * 3. 向 mid 索引值的右边扫描，将所有满足查找值的元素的下标加入到集合ArrayList中
     * 4. 将ArrayList返回
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 当left > right时，说明递归整个数组没有找到
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        // 向右递归
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        }
        // 向左递归
        else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            // 向左扫描
            int temp = mid - 1;
            while (true) {
                // 最左边或发现有一个不等于查找值
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                } else {
                    resIndexList.add(temp);
                    // 继续扫描下一位
                    temp -= 1;
                }
            }
            // 存入中间值
            resIndexList.add(mid);
            temp = mid + 1;
            // 向右扫描
            while (true) {
                // 最右边或发现有一个不等于查找值
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                } else {
                    resIndexList.add(temp);
                    // 继续扫描下一位
                    temp += 1;
                }
            }
            return resIndexList;
        }
    }

}
