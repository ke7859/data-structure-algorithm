package com.ke.linkedlist;

import java.util.Stack;

/**
 * 单向链表
 * http://note.youdao.com/noteshare?id=3fc4c5928705cfb7dcb5985a8a54f4e8&sub=CFA437629B5F475D97F8A3D48287BCAE
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-07 17:44
 **/
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        /*
         * 进行测试
         * 创建节点
         */
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 添加
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);

        System.out.println("普通链表");
        singleLinkedList.list();
        System.out.println();

        // 反转链表
        reverseList(singleLinkedList.getHead());
        System.out.println("反转后的普通链表");
        singleLinkedList.list();
        System.out.println();

        // 逆序打印
        System.out.println("逆序打印反转后的普通链表，不改变链表结构");
        reversePrint(singleLinkedList.getHead());
        System.out.println();

        // 创建链表
        SingleLinkedList singleLinkedListByOrder = new SingleLinkedList();

        // 按照编号顺序加入
        singleLinkedListByOrder.addByOrder(hero4);
        singleLinkedListByOrder.addByOrder(hero1);
        singleLinkedListByOrder.addByOrder(hero2);
        singleLinkedListByOrder.addByOrder(hero3);
        singleLinkedListByOrder.addByOrder(hero3);

        System.out.println("有序链表");
        singleLinkedListByOrder.list();
        System.out.println();

        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        // 修改节点
        singleLinkedListByOrder.update(newHeroNode);
        System.out.println("修改后的有序链表");
        singleLinkedListByOrder.list();
        System.out.println();

        // 删除节点
        singleLinkedListByOrder.delete(4);
        singleLinkedListByOrder.delete(1);
        System.out.println("删除后的有序链表");
        singleLinkedListByOrder.list();
        System.out.println();

        // 求单链表中有效节点的个数
        System.out.println("有效的节点个数= " + getLength(singleLinkedListByOrder.getHead()));

        int k = 3;
        // 查找单链表中的倒数第 k 个节点
        System.out.println("倒数第 " + k + " 个节点= " + findLastIndexNode(singleLinkedListByOrder.getHead(), k));
    }

    /**
     * 面试题：查找单链表中的倒数第 k 个节点
     * 编写一个方法，接收 head 节点，同时接收一个 index
     * index 表示是倒数第 index 个节点
     * 先遍历链表，得到链表的总长度 getLength
     * 得到 size 后，从链表的第一个开始遍历（size - index）个，就可以得到
     * 如果找到了，则返回该节点，否则返回 null
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 判断如果链表为空，返回 null
        if (head.next == null) {
            return null;
        }
        // 第一次遍历得到链表长度
        int size = getLength(head);
        // 第二次遍历 size - index 位置，就是我们倒数第 k 个位置
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义一个辅助变量, for 循环定位到倒数的index
        HeroNode temp = head.next;

        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 面试题：获取到单链表的节点的个数（如果时带头节点的链表，需求不统计头节点）
     *
     * @param head 链表的头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        // 定义一个辅助变量，直接指向下一个节点，不统计头节点
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            // 遍历下一个
            temp = temp.next;
        }
        return length;
    }

    /**
     * 面试题：将单链表进行反转
     *
     * @param head
     */
    public static void reverseList(HeroNode head) {
        // 如果当前链表为空，或者只有一个节点，无需反转
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义一个辅助变量，帮助我们遍历原来的节点
        HeroNode temp = head.next;
        // 指向当前节点 [temp] 的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个节点，就将其去除，并放在新的链表 reverseHead 的最前端
        while (temp != null) {
            // 暂时保存当前节点的下一个节点
            next = temp.next;
            // 将 temp 的下一个节点指向新的链表的最前端、倒序，头插法
            temp.next = reverseHead.next;
            // 将 temp 连接到新的链表上
            reverseHead.next = temp;
            // temp 后移，遍历下一个节点
            temp = next;
        }

        // 将 head.next 指向 reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 面试题：逆序打印，不破坏远链表结构
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        // 空链表
        if (head.next == null) {
            return;
        }
        // 创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack();
        HeroNode temp = head.next;
        while (temp != null) {
            // 将链表的节点压入栈
            stack.push(temp);
            // 遍历下一个节点
            temp = temp.next;
        }
        // 将栈中的节点进行打印，出栈，pop
        while (stack.size() > 0) {
            // 先进后出
            System.out.println(stack.pop());
        }
    }
}

/**
 * 定义 SingleLinked List 管理 HeroNode
 */
class SingleLinkedList {
    /**
     * 先初始化一个头节点，头节点不要动，不存放具体的数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的 next 指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后
            temp = temp.next;
        }
        // 当退出 while 循环时，temp 就指向了链表的最后
        temp.next = heroNode;
    }

    /**
     * 第二种方式添加英雄，根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败，并给出提示
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        /*
         * 因为头节点不能动，因此我们通过一个辅助指针（变量）来帮助找到添加的位置
         * 因为单链表，因此我们找到 temp 是位于添加位置的前一个节点，否则插入不了
         */
        HeroNode temp = head;
        while (true) {
            // 说明 temp 已经在链表的最后
            if (temp.next == null) {
                break;
            }
            // 位置找到，就在 temp 的后面插入
            if (temp.next.no > heroNode.no) {
                break;
            }
            // 说明希望添加的 heroNode 的编号已经存在
            else if (temp.next.no == heroNode.no) {
                // 说明编号存在
                System.out.printf("准备插入的数据编号 %d 已经存在了，不能加入\n", heroNode.no);
                return;
            }
            // 后移，遍历下一个节点
            temp = temp.next;
        }
        // 插入链表
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 修改节点的信息，根据 no 编号来修改，即 no 编号不能修改
     * 1.根据 newHeroNode 的 no 来修改
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据 no 编号
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                // 链表遍历结束
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
                return;
            }
            temp = temp.next;
        }
        System.out.printf("没有找到编号 %d 的节点\n", newHeroNode.no);
    }

    /**
     * 删除节点
     * head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
     * 我们在比较时，是 temp.next.no 和需要删除的节点的 no 比较
     *
     * @param no
     */
    public void delete(int no) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        while (true) {
            // 已经到链表的最后
            if (temp.next == null) {
                break;
            }
            // 找到待删除节点的前一个节点
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }
            // 后移，遍历下一个节点
            temp = temp.next;
        }
        System.out.printf("要删除的 %d 节点不存在", no);
    }

    /**
     * 显示链表，遍历
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将 next 后移，不然死循环
            temp = temp.next;
        }
    }
}

/**
 * 定义 HeroNode，每个 HeroNode 对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
