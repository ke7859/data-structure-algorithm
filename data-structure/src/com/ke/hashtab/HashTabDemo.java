package com.ke.hashtab;

import java.util.Scanner;

/**
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2022-01-05 22:54
 **/
public class HashTabDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加元素");
            System.out.println("list: 显示元素");
            System.out.println("find: 查找元素");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建一个元素
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 创建HashTable，管理多条链表
 */
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    /**
     * 多少条链表
     */
    private int size;

    /**
     * 初始化链表
     *
     * @param size
     */
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加
     *
     * @param emp
     */
    public void add(Emp emp) {
        // 根据元素的id，得到该员工应该添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    /**
     * 遍历hashTab
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据id查找元素
     * @param id
     */
    public void findEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp == null) {
            System.out.println("没有找到该元素");
        } else {
            System.out.printf("在第%s条链表中找到该元素 id=%d name=%s\n", empLinkedListNO + 1, emp.id, emp.name);
        }
    }

    /**
     * 编写散列函数，使用取模
     *
     * @param id
     * @return
     */
    public int hashFun(int id) {
        return id % size;
    }
}

/**
 * 表示一个元素
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 表示链表
 */
class EmpLinkedList {
    /**
     * 头指针，指向第一个Emp，因此我们这个链表的head是直接指向第一个元素的
     */
    private Emp head;

    /**
     * 添加
     * 添加元素时，id自增，加在本链表最后
     *
     * @param emp
     */
    public void add(Emp emp) {
        // 如果是添加第一个
        if (head == null) {
            head = emp;
            return;
        }
        // 否则定位到最后
        Emp curEmp = head;
        while (true) {
            // 说明到链表最后
            if (curEmp.next == null) {
                break;
            }
            // 后移
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历链表
     *
     * @param no 下标
     */
    public void list(int no) {
        // 链表为空
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 条链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 条链表信息：");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" -> id=%d name=%s\t", curEmp.id, curEmp.name);
            // 最后节点
            if (curEmp.next == null) {
                break;
            }
            // 后移
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找元素
     * @param id
     * @return
     */
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (curEmp != null) {
            if (curEmp.id == id) {
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}
