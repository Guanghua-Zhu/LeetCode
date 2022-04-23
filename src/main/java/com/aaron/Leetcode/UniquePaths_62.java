package com.aaron.Leetcode;

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 示例 1： 
//
// 
//输入：m = 3, n = 7
//输出：28 
//
// 示例 2： 
//
// 
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
// 
//
// 示例 3： 
//
// 
//输入：m = 7, n = 3
//输出：28
// 
//
// 示例 4： 
//
// 
//输入：m = 3, n = 3
//输出：6 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 109 
// 
// Related Topics 数学 动态规划 组合数学 
// 👍 1382 👎 0


import java.util.Arrays;

/**
 * 62, 不同路径
 * @author Aaron Zhu
 * @date 2022-4-23
 */
public class UniquePaths_62{

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 组合数学
     */
    public static class Solution {
        public int uniquePaths(int m, int n) {
            if( m==1 || n==1 ) {
                return 1;
            }

            long res = 1;
            for(long a=1,b=n; a<=m-1; a++,b++) {
                res = res * b / a;
            }

            return (int)res;
        }
    }

    /**
     * 动态规划DP
     */
    public static class Solution1 {
        // f(i,j) = f(i-1,j) + f(i,j-1)
        public int uniquePaths(int m, int n) {
            if( m==1 || n==1 ) {
                return 1;
            }

            int[] last = new int[n];
            int[] now = null;

            Arrays.fill(last, 1);
            last[0] = 0;

            for (int i=0; i<m-1; i++) {
                now = new int[n];
                now[0] = 1;
                for (int j=1; j<n; j++) {
                    now[j] = now[j-1] + last[j];
                }

                last = now;
            }

            return last[n-1];
        }
    }

}
