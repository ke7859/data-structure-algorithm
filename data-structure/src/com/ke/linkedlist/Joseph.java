package com.ke.linkedlist;

/**
 * 约瑟夫问题
 * 链接：http://note.youdao.com/noteshare?id=3fc4c5928705cfb7dcb5985a8a54f4e8&sub=CFA437629B5F475D97F8A3D48287BCAE
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-18 17:00
 **/
public class Joseph {
    public static void main(String[] args) {
        // 构建一个环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        // 加入5个节点
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        System.out.println();

        // 测试出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

/**
 * 创建一个环形单向链表
 */
class CircleSingleLinkedList {
    /**
     * 创建一个 first 节点，当前没有编号
     */
    private Boy first = null;

    /**
     * 添加节点，构建成一个环形的链表
     *
     * @param nums 有多少个节点
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        // 辅助指针，帮助构建环形链表，指向最后一个节点
        Boy curBoy = null;
        // 使用循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建节点
            Boy boy = new Boy(i);
            // 如果是第一个 boy
            if (i == 1) {
                first = boy;
                // 构成一个环状
                first.setNext(first);
                // 让 curBoy 指向第一个
                curBoy = first;
            } else {
                // 添加到最后
                curBoy.setNext(boy);
                // 指向头节点，构成环状
                boy.setNext(first);
                // 辅助指针后移
                curBoy = boy;
            }
        }
    }

    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("编号 %d \n", curBoy.getNo());
            // 说明遍历完毕
            if (curBoy.getNext() == first) {
                break;
            }
            // 辅助指针后移，指向下一个节点
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出圈的顺序
     *
     * @param startNo  表示从第几个开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 创建一个辅助指针，帮助完成出圈
        Boy helper = first;
        // 先让 helper 指向最后节点
        while (true) {
            // 说明 helper 指向最后节点
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 报数前，先让 first 和 helper 移动 k - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = first.getNext();
        }
        // 当报数时，让 first 和 helper 指针同时的移动 m - 1 次，然后出圈
        while (true) {
            // 说明圈中只有一个节点
            if (helper == first) {
                break;
            }
            // 让 first 和 helper 指针同时的移动 countNum - 1 次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时 first 指向的节点就是要出圈的节点
            System.out.printf("%d 出圈\n", first.getNo());
            // 将 first 指向的节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后圈中的编号 %d \n", helper.getNo());
    }
}

/**
 * 创建一个 Boy 类，表示一个节点
 */
class Boy {
    /**
     * 编号
     */
    private int no;
    /**
     * 指向下一个节点，默认 null
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
