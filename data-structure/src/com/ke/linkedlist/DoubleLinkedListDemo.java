package com.ke.linkedlist;

/**
 * 双向链表
 * 链接：http://note.youdao.com/noteshare?id=3fc4c5928705cfb7dcb5985a8a54f4e8&sub=CFA437629B5F475D97F8A3D48287BCAE
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-09 16:16
 **/
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        /*
         * 进行测试
         * 创建节点
         */
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();
        System.out.println();

        // 修改
        doubleLinkedList.update(new HeroNode2(4, "林冲", "豹子头~~"));
        System.out.println("修改后的链表");
        doubleLinkedList.list();
        System.out.println();

        // 删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表");
        doubleLinkedList.list();
        System.out.println();

        doubleLinkedList = new DoubleLinkedList();
        // 有序插入
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);

        System.out.println("有序插入");
        doubleLinkedList.list();
    }
}

/**
 * 创建一个双向链表的类
 */
class DoubleLinkedList {
    /**
     * 先初始化一个头节点，头节点不要动，不存放具体的数据
     */
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
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
        HeroNode2 temp = head.next;
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

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的 next 指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        // 因为 head 节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode2 temp = head;
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
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 有序添加
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = this.head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                System.out.printf("编号 %d 已经存在", heroNode.no);
                return;
            }
            // 遍历下一个
            temp = temp.next;
        }

        heroNode.next = temp.next;
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 修改节点的信息，根据 no 编号来修改，即 no 编号不能修改
     * 和单向链表一样
     * 1.根据 newHeroNode 的 no 来修改
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据 no 编号
        HeroNode2 temp = head.next;
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
     * 从双休链表删除节点
     * head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
     * 我们在比较时，是 temp.next.no 和需要删除的节点的 no 比较
     * 对于双向链表，我们可以直接找到要删除的这个节点
     * 找到后自我删除即可
     *
     * @param no
     */
    public void delete(int no) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            // 已经到链表的最后
            if (temp == null) {
                break;
            }
            // 找到待删除节点,删除
            if (temp.no == no) {
                temp.pre.next = temp.next;
                // 如果是最后面一个节点，不需要执行，否则空指针
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                return;
            }
            // 后移，遍历下一个节点
            temp = temp.next;
        }
        System.out.printf("要删除的 %d 节点不存在", no);
    }
}

/**
 * 定义 HeroNode，每个 HeroNode 对象就是一个节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    /**
     * 指向下一个节点
     */
    public HeroNode2 next;

    /**
     * 指向前一个节点
     */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
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

