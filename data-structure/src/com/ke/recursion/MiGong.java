package com.ke.recursion;

/**
 * 迷宫问题
 * 文档：递归.md
 * 链接：http://note.youdao.com/noteshare?id=cff72a515eff34e5d574f23aad803bd8&sub=437704797E5E4387B8CD429270CB7CA5
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-25 15:45
 **/
public class MiGong {

    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        // 使用 1 表示墙，上下全部设置为 1
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部设置为 1
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // 输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();
        // 使用递归回溯找路
        setWay(map, 1, 1);

        // 输出新的地图，走过，并标识过的
        System.out.println("小球走过，并标识过的地图的情况");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小球找路
     * 从 map[1][1] 出发，如果找到 map[6][5] 位置，则说明通路找到
     * 当 map[i][j] 为 0 表示该点没有走过，为 1 表示墙，2 表示通路，3 表示该点已经走过但是不通
     * 确定一个策略 下 -> 右 -> 上 -> 左，如果该点走不通，再回溯
     *
     * @param map 表示地图
     * @param i   从哪个位置开始
     * @param j   从哪个位置开始
     * @return 如果找到通路，返回 true，否则返回 false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 说明通路已经找到
        if (map[6][5] == 2) {
            return true;
        }
        // 如果当前点没有走过
        if (map[i][j] == 0) {
            // 按照策略 下 -> 右 -> 上 -> 左，假设该点可以走通
            map[i][j] = 2;
            // 向下走
            if (setWay(map, i + 1, j)) {
                return true;
            }
            // 向右走
            else if (setWay(map, i, j + 1)) {
                return true;
            }
            // 向上走
            else if (setWay(map, i - 1, j)) {
                return true;
            }
            // 向左走
            else if (setWay(map, i, j - 1)) {
                return true;
            }
            // 说明该点走不通，是死路，假定可以走通错误
            else {
                map[i][j] = 3;
                return false;
            }
        }
        // 不为 0返回false
        else {
            return false;
        }
    }
}
