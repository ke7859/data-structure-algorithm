package com.ke.sparsearray;

/**
 * 稀疏数组
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-03 16:59
 **/
public class SparseArray {

    public static void main(String[] args) {
        /*
         * 创建一个原始的二维数组 11 * 11
         * 0表示没有棋子，1表示黑子，2表示篮子
         * */
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][5] = 1;
        // 输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        /*
         * 将二维数组转换为稀疏数组
         * 1.先遍历二维数组，得到非 0 数据的个数
         * */
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        // 给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        // 用于记录是第几个非 0 数据
        int count = 0;
        // 遍历二维数组，将非 0 的值存放倒稀疏数组中
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        /*
         * 将稀疏数组恢复成原始的数组
         * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
         * 2.再读取稀疏数组后几行的数据，并赋值给原始的二维数组即可
         * */
        // 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        // 2.再读取稀疏数组后几行的数据，并赋值给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
