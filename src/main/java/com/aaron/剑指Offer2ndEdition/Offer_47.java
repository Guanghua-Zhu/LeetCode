package com.aaron.剑指Offer2ndEdition;

//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？ 
//
// 
//
// 示例 1: 
//
// 输入: 
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物 
//
// 
//
// 提示： 
//
// 
// 0 < grid.length <= 200 
// 0 < grid[0].length <= 200 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 254 👎 0


import java.util.Arrays;

/**
 * 剑指 Offer 47, 礼物的最大价值
 * @author Aaron Zhu
 * @date 2022-3-6
 */
public class Offer_47 {
    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,3,1},{1,5,1},{4,2,1}};
        Solution solution = new Solution();
        solution.maxValue(grid);
        System.out.println("gg");
    }

    /**
     * 暴力递归+记忆化
     */
    public static class Solution {
        private int[][] grid;

        private int m;

        private int n;

        public int maxValue(int[][] grid) {
            m = grid.length;
            n = grid[0].length;
            this.grid = grid;
            int[][] cache = new int[m+1][n+1];
            for (int i=0; i<m; i++) {
                Arrays.fill(cache[i], -1);
            }

            return calc(0,0, cache);
        }

        private int calc(int x, int y, int[][] cache) {
            if( cache[x][y] != -1 ) {
                return cache[x][y];
            }

            if( x==m || y==n ) {
                cache[x][y] = 0;
                return cache[x][y];
            }

            int v1 = calc(x+1, y, cache);
            int v2 = calc(x, y+1, cache);
            int res = grid[x][y] + Math.max(v1,v2);
            cache[x][y] = res;
            return cache[x][y];
        }
    }

    /**
     * DP动态规划
     */
    public static class Solution1 {
        public int maxValue(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            int[][] dp = new int[m][n];
            // 第一行
            for(int y=0; y<n; y++) {
                if( y==0 ) {
                    dp[0][0] = grid[0][0];
                } else {
                    dp[0][y] = dp[0][y-1] + grid[0][y];
                }
            }
            // 第一列
            for(int x=1; x<m; x++) {
                dp[x][0] = dp[x-1][0] + grid[x][0];
            }

            for (int x=1; x<m; x++) {
                for (int y=1; y<n; y++) {
                    dp[x][y] = Math.max(dp[x-1][y], dp[x][y-1]) + grid[x][y];
                }
            }

            return dp[m-1][n-1];
        }
    }

}
