package com.aaron.LeetCode;

import java.util.*;

//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1']
//,['1','0','0','1','0']]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：matrix = [['0','1'],['1','0']]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：matrix = [['0']]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 数组 动态规划 矩阵 👍 1184 👎 0


/**
 * 221, 最大正方形
 * @author Aaron Zhu
 * @date 2022-7-1
 */
public class MaximalSquare_221{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] m = new char[][]{
            {'1','0','1','1','1'},
            {'0','1','0','1','0'},
            {'1','1','0','1','1'},
            {'1','1','0','1','1'},
            {'0','1','1','1','1'}
        } ;

        solution.maximalSquare(m);
        System.out.println("gg");
    }

    /**
     * DP
     */
    public static class Solution {
        public int maximalSquare(char[][] matrix) {
            int maxSide = 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] dp = new int[m][n];

            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if (matrix[i][j]=='1') {
                        if ( i==0 || j==0 ) {
                            dp[i][j] = 1;
                        } else {
                            dp[i][j] = getMinBy3(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
                        }
                        maxSide = Math.max(maxSide, dp[i][j]);
                    }
                }
            }

            return maxSide * maxSide;
        }

        private int getMinBy3(int a, int b, int c) {
            return Math.min(a, Math.min(b,c));
        }
    }

    /**
     * 单调递减栈
     */
    public static class Solution1 {
        public int maximalSquare(char[][] matrix) {
            int maxSide = 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[] heights = new int[n];
            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    heights[j] = matrix[i][j]=='0' ? 0 : heights[j]+1;
                }
                maxSide = Math.max(maxSide, calcSide(heights));
            }
            return maxSide*maxSide;
        }

        private int calcSide(int[] heights) {
            int size = heights.length;
            int[] leftBoard = new int[ size ];
            int[] rightBoard = new int[ size ];

            // 单调递减栈
            LinkedList<Integer> stack = new LinkedList<>();
            // 计算左边界
            for (int i=0; i<size; i++) {
                int height = heights[i];
                while ( !stack.isEmpty() && heights[stack.peekLast()] >= height ) {
                    stack.pollLast();
                }
                leftBoard[i] = stack.peekLast()==null ? -1 : stack.peekLast();
                stack.addLast( i );
            }

            // 计算右边界
            stack.clear();
            for (int i=size-1; i>=0; i--) {
                int height = heights[i];
                while ( !stack.isEmpty() && heights[stack.peekLast()] >= height ) {
                    stack.pollLast();
                }
                rightBoard[i] = stack.peekLast()==null ? size : stack.peekLast();
                stack.offerLast( i );
            }

            int maxSide = 0;
            for (int i=0; i<size; i++) {
                int height = heights[i];
                int length = rightBoard[i] - leftBoard[i] - 1;
                int side = Math.min(height, length);
                maxSide = Math.max(maxSide, side);
            }

            return maxSide;
        }
    }

}