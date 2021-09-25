package com.ke.recursion;

/**
 * 八皇后问题
 * 文档：递归.md
 * 链接：http://note.youdao.com/noteshare?id=cff72a515eff34e5d574f23aad803bd8&sub=437704797E5E4387B8CD429270CB7CA5
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-25 16:58
 **/
public class Queue8 {

    /**
     * 定于一个 max 表示共有多少个皇后
     */
    int max = 8;
    /**
     * 定义数组 array，保存皇后放置位置的结果，比如 arr = {0, 4, 7, 5, 2, 6, 1, 3}
     */
    int[] array = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有 " + count + " 种解法");
    }

    /**
     * 放置第 n 个皇后
     * check 是每一次递归时，进入到 check 中都有 for (int i = 0; i < max; i++)，因此会有回溯
     *
     * @param n
     */
    private void check(int n) {
        // n == max 表示放置第 9 个皇后，前 8 个皇后已经放好了
        if (n == max) {
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前皇后 n，放到该行的第 1 列
            array[n] = i;
            // 判断当放置第 n 个皇后到 i 列时，是否冲突，成立说明不冲突
            if (judge(n)) {
                // 接着放 n+1 个皇后，即开始递归
                check(n + 1);
            }
            // 如果冲突，就继续执行 array[n] = i;即将第 n 个皇后放置在本行的后移的一个位置
        }
    }


    /**
     * 查看当我们放置第 n 个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第 n 个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 表示判断第 n 个皇后是否和前面的 n-1 个皇后在同一列 或者 第 n 个皇后时候和第 i 个皇后是同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 可以将皇后摆放的位置输出
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }
}
