package com.ke.sparsearray;

/**
 * 稀疏数组练习
 * http://note.youdao.com/noteshare?id=f85409b431514289413978fdf9716e5d&sub=E064D17324F54521BB8B89602F9503FA
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-07 09:33
 **/
public class SparseArrayExercise {

    public static void main(String[] args) {
        int[][] chessArr1 = new int[11][11];
        chessArr1[2][3] = 1;
        chessArr1[2][4] = 2;
        chessArr1[4][6] = 1;


        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                System.out.printf("%d\t", chessArr1[i][j]);
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
            System.out.println();
        }

        // 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        int count = 0;
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

        System.out.println("稀疏数组");
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

        // 创建恢复数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("恢复后的数组");
        for (int i = 0; i < chessArr2.length; i++) {
            for (int j = 0; j < chessArr2[i].length; j++) {
                System.out.printf("%d\t", chessArr2[i][j]);
            }
            System.out.println();
        }
    }
}
