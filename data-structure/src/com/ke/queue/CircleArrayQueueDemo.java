package com.ke.queue;

import java.util.Scanner;

/**
 * 使用数组模拟环形队列
 * http://note.youdao.com/noteshare?id=ef706b9c9f0f2e2aba0636e6198e7425&sub=364FAD34BC49413793493D322D1C25DA
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-07 16:27
 **/
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // 创建一个环形队列，这里设置 4 ，其队列的有效数据最大是 3
        CircleArray queue = new CircleArray(4);
        // 接受用户输入
        char key = ' ';
        // 扫描器
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据");
            System.out.println("g(get)：取出数据");
            System.out.println("h(head)：头部数据");
            // 接收一个字符串的第一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircleArray {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;
    /**
     * 队列头，front 就指向队列的第一个元素，也就是 arr[front]，front 的初始值 = 0
     */
    private int front;
    /**
     * 队列尾，rear 指向队列的最后一个元素的后一个位置。因为希望空出一个空间作为约定 maxSize - 1，rear 的初始值 = 0
     */
    private int rear;
    /**
     * 该数据用于存放数据，模拟队列
     */
    private int[] arr;

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param n
     */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        // 将 rear 后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据，出队列
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        /*
         * 这里需要分析出 front 是指向队列的第一个元素
         * 1.先把 front 对应的值保留到一个临时变量
         * 2.将 front 后移，考虑取模
         * 3.将临时保存的变量返回
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空队列，没有数据");
            return;
        }

        /*
         * 思路：从 front 开始遍历，遍历多少个元素
         */
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列有效数据的个数
     *
     * @return
     */
    public int size() {
        /*
         * rear = 1
         * front = 0
         * maxSIze = 3
         */
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据
     *
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("空队列，没有数据");
        }
        return arr[front];
    }
}
