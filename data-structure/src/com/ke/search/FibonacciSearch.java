package com.ke.search;

import java.util.Arrays;

/**
 * 斐波那契查找，需要数组有序
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-12-28 22:41
 **/
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index= " + fibonacciSearch(arr, 1000));
    }

    /**
     * 得到一个斐波那契数列
     *
     * @return
     */
    public static int[] getFib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找
     *
     * @param a   数组
     * @param key 需要找的数值
     * @return 没有返回 -1
     */
    public static int fibonacciSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        // 表示斐波那契分割数值的下标
        int k = 0;
        int mid = 0;
        // 获取到斐波那契数列
        int[] f = getFib();

        // 获取到斐波那契数列的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为 f[k]的值可能大于 a 的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]，不足的部分补0
        int[] temp = Arrays.copyOf(a, f[k]);
        // 使用a[]最后的数填充temp[] temp = {1, 8, 10, 89, 1000, 1234} => {1, 8, 10, 89, 1000, 1234, 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        // while循环找到key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            // 继续向数组的左边查找
            if(key < temp[mid]){
                high = mid - 1;
                /*
                * 1. 全部元素 = 前面的元素 + 后面的元素
                * 2. f[k] = f[k-1] + f[k-2]
                *       因为前面有f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                *       即在f[k-1]的前面继续查找 k--
                *       即下次循环 mid = f[k-1-1]-1
                * */
                k--;
            }
            // 向数组的右边查找
            else if(key > temp[mid]){
                low = mid + 1;
                /*
                * 1. 全部元素 = 前面的元素 + 后面的元素
                * 2. f[k] = f[k-1] + f[k-2]
                * 3. 因为我们后面有f[k-2]，所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                * 4. 即在f[k-2]的前面进行查找 k-=2
                * 5. 即下次循环 mid = f[k-1-2]-1
                * */
                k -= 2;
            } else {
                // 找到，确定返回哪个下标
                if(mid <= high){
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
