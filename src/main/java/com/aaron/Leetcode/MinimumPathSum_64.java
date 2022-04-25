package com.aaron.LeetCode;

import java.awt.*;
import java.util.*;

//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 1224 👎 0


/**
 * 64, 最小路径和
 * @author Aaron Zhu
 * @date 2022-4-24
 */
public class MinimumPathSum_64{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP动态规划
     */
    public static class Solution {
        public int minPathSum(int[][] grid) {
            if(grid.length==1 && grid[0].length==1) {
                return grid[0][0];
            }

            int m = grid.length;
            int n = grid[0].length;
            int[] last = null;

            for(int i=0; i<m; i++) {
                int[] now = new int[n];
                for (int j=0; j<n; j++) {
                    if( i==0 ) {
                        now[j] = j-1<0 ? grid[i][j] : grid[i][j] + now[j-1];
                    } else {
                        now[j] = j-1<0 ? grid[i][j] + last[j] : grid[i][j] + Math.min(now[j-1], last[j]);
                    }
                }
                last = now;
            }

            return last[n-1];
        }
    }

}

