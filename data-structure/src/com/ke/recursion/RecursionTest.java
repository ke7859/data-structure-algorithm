package com.ke.recursion;

/**
 * 测试递归
 * 文档：递归.md
 * 链接：http://note.youdao.com/noteshare?id=cff72a515eff34e5d574f23aad803bd8&sub=437704797E5E4387B8CD429270CB7CA5
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-24 17:42
 **/
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
        System.out.println(factorial(5));
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    /**
     * 阶乘问题
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
