package com.aaron.LeetCode;

import java.util.*;

//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [["0","0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 1 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1265 👎 0


/**
 * 85, 最大矩形
 * @author Aaron Zhu
 * @date 2022-5-16
 */
public class MaximalRectangle_85{
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("gg");
    }

    /**
     * 单调递减栈
     */
    public static class Solution {
        public int maximalRectangle(char[][] matrix) {
            int maxArea = 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] heights = new int[m][n];

            // 按行转换为直方图
            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if( i==0 ) {    // 第一行
                        heights[i][j] = matrix[i][j]=='1' ? 1 : 0;
                    } else {
                        heights[i][j] = matrix[i][j]=='0' ? 0 : heights[i-1][j]+1;
                    }
                }
            }

            // 分别计算以各行为底的直方图的最大面积
            for (int i=0; i<m; i++) {
                int area = calcMaxArea( heights[i] );
                maxArea = Math.max( maxArea, area );
            }
            return maxArea;
        }

        private int calcMaxArea(int[] height) {
            int size = height.length;
            int[] leftBoard = new int[size];
            int[] rightBoard = new int[size];

            // 单调递减栈
            Deque<Integer> stack = new LinkedList<>();
            for(int i=0; i<size; i++) {
                Integer current = height[i];
                while ( !stack.isEmpty() && height[stack.peek()] >= current ) {
                    stack.poll();
                }
                leftBoard[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }

            stack.clear();
            for(int i=size-1; i>=0; i--) {
                Integer current = height[i];
                while ( !stack.isEmpty() && height[stack.peek()] >= current ) {
                    stack.poll();
                }
                rightBoard[i] = stack.isEmpty() ? size : stack.peek();
                stack.push(i);
            }

            int maxArea = 0;
            for (int i=0; i<size; i++) {
                int area = (rightBoard[i]-leftBoard[i]-1) * height[i];
                maxArea = Math.max( maxArea, area );
            }
            return maxArea;
        }

    }
}

