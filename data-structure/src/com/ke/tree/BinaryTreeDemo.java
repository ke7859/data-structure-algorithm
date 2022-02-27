package com.ke.tree;

/**
 * 二叉树
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2022-02-09 23:20
 **/
public class BinaryTreeDemo {

    /**
     * 前序遍历计数
     */
    public static int countPreOrderSearch = 0;
    /**
     * 中序遍历计数
     */
    public static int countInfixOrderSearch = 0;
    /**
     * 后序遍历计数
     */
    public static int countPostOrderSearch = 0;

    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        System.out.println("前序遍历 1 2 3 5 4");
        binaryTree.preOrder();

        System.out.println("中序遍历 2 1 5 3 4");
        binaryTree.infixOrder();

        System.out.println("后序遍历 2 5 4 3 1");
        binaryTree.postOrder();

        int no = 5;
        System.out.println("前序遍历查找");
        HeroNode resNode = binaryTree.preOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到编号为 %d 的节点 name=%s，共递归 %d 次\n", no, resNode.getName(), countPreOrderSearch);
        } else {
            System.out.printf("没有找到编号为 %d 的节点\n", no);
        }

        System.out.println("中序遍历查找");
        resNode = binaryTree.infixOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到编号为 %d 的节点 name=%s，共递归 %d 次\n", no, resNode.getName(), countInfixOrderSearch);
        } else {
            System.out.printf("没有找到编号为 %d 的节点\n", no);
        }

        System.out.println("后序遍历查找");
        resNode = binaryTree.postOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到编号为 %d 的节点 name=%s，共递归 %d 次\n", no, resNode.getName(), countPostOrderSearch);
        } else {
            System.out.printf("没有找到编号为 %d 的节点\n", no);
        }

        System.out.printf("删除节点 %d 前，前序遍历\n", no);
        binaryTree.preOrder();
        binaryTree.delNode(no);
        System.out.printf("删除节点 %d 后，前序遍历\n", no);
        binaryTree.preOrder();
    }
}

/**
 * 定义二叉树
 */
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法前序遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法中序遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法后序遍历");
        }
    }

    /**
     * 前序遍历查找
     *
     * @param no 编号
     * @return 找到就返回Node，没有则返回null
     */
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }
        return null;
    }

    /**
     * 中序遍历查找
     *
     * @param no 编号
     * @return 找到就返回Node，没有则返回null
     */
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }
        return null;
    }

    /**
     * 后序遍历查找
     *
     * @param no 编号
     * @return 找到就返回Node，没有则返回null
     */
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }

    /**
     * 删除节点
     * @param no 需要删除的编号
     */
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个 root 节点，判断 root 是不是需要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除！！！");
        }
    }
}

/**
 * 先创建HeroNode节点
 */
class HeroNode {
    private int no;
    private String name;
    /**
     * 默认 null
     */
    private HeroNode left;
    /**
     * 默认 null
     */
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出当前节点，父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        // 递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出当前节点，父节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 编号
     * @return 找到就返回Node，没有则返回null
     */
    public HeroNode preOrderSearch(int no) {
        BinaryTreeDemo.countPreOrderSearch++;
        // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        // 判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        // 说明左子树找到
        if (resNode != null) {
            return resNode;
        }
        // 判断当前节点的右子节点是否为空，如果不为空，则向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 编号
     * @return 找到就返回Node，没有则返回null
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        // 判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        BinaryTreeDemo.countInfixOrderSearch++;
        // 没有找到就和当前节点比较，如果是则返回当前节点
        if (this.no == no) {
            return this;
        }
        // 向右递归中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no 编号
     * @return 找到就返回Node，没有则返回null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        // 先判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 没有找到则向右递归后序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        BinaryTreeDemo.countPostOrderSearch++;
        // 如果左右子树都没找到，比较当前节点
        if (this.no == no) {
            return this;
        }
        return null;
    }

    /**
     * 递归删除节点
     * 1. 如果删除的节点是叶子节点，则删除该节点
     * 2. 如果删除的节点是非叶子节点，则删除该树
     * @param no 需要删除的节点编号
     */
    public void delNode(int no){
        /**
         * 1. 单向二叉树，判断当前节点的子节点是否需要删除，而不能去判断当前这个节点是不是需要删除节点
         * 2. 如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left = null;并且返回（结束递归删除）
         * 3. 如果当前节点的右子节点不为空，并且右子节点就是要删除节点，就将this.right = null;并且返回（结束递归删除）
         * 4. 如果第2第3步没有删除节点，那么就需要向左子树进行递归删除
         * 5. 如果第4步也没有删除节点，则应当向右子树进行递归删除
         */
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}
