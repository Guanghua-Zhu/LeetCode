package com.aaron.Leetcode;

import javax.swing.*;
import java.util.*;

//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
//
// 
// Related Topics 数组 数学 矩阵 
// 👍 1249 👎 0


/**
 * 48, 旋转图像
 * @author Aaron Zhu
 * @date 2022-4-15
 */
public class RotateImage_48{
    public static void main(String[] args) {
        int[][] matrix = new int[][]{ {1,2,3}, {4,5,6}, {7,8,9} };
        Solution solution = new Solution();
        solution.rotate(matrix);
        System.out.println("gg");
    }


    /**
     * 翻转法: 先按主对角线翻转、再进行垂直翻转
     */
    public static class Solution {
        public void rotate(int[][] matrix) {
            if( matrix.length==1 ) {
                return;
            }

            int n = matrix.length;
            // 矩阵按 主对角线进行翻转
            for (int i=0; i<n; i++) {
                for (int j=i+1; j<n;j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }

            // 矩阵进行垂直翻转
            int count = n/2;
            for (int i=0; i<n; i++) {
                for (int j=0; j<count; j++) {
                    int newJ = n - 1 - j;
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][newJ];
                    matrix[i][newJ] = temp;
                }
            }
        }
    }

    /**
     * 模拟法
     */
    public static class Solution1 {
        public void rotate(int[][] matrix) {
            if( matrix.length==1 ) {
                return;
            }

            int n = matrix.length;
            int ring = n / 2;   // 圈数
            int count = n - 1;  // 每一圈修改的次数
            for (int ringNum=0; ringNum<ring; ringNum++) {
                int i = ringNum;
                int j = ringNum;

                for (int countNum=0; countNum<count; countNum++) {
                    int rowIndex = i;
                    int colIndex = j + countNum;
                    swap(matrix, rowIndex, colIndex);
                }

                count = count - 2;
            }
        }

        private void swap(int[][] matrix, int i, int j) {
            int m = matrix.length - 1;
            int rowIndex = i;
            int colIndex = j;
            int current = matrix[rowIndex][colIndex];

            for (int k=0; k<4; k++) {
                int nextRowIndex = colIndex;
                int nextColIndex = m - rowIndex;
                int next = matrix[ nextRowIndex ][ nextColIndex ];

                matrix[nextRowIndex][nextColIndex] = current;

                current = next;
                rowIndex = nextRowIndex;
                colIndex = nextColIndex;
            }
        }
    }

}
