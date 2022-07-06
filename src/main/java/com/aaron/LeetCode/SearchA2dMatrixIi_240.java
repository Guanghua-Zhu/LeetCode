package com.aaron.LeetCode;

import java.util.*;

//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 二分查找 分治 矩阵 👍 1063 👎 0


/**
 * 240, 搜索二维矩阵 II
 * @author Aaron Zhu
 * @date 2022-7-5
 */
public class SearchA2dMatrixIi_240{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{
            {1,4,7,11,15},
            {2,5,8,12,19},
            {3,6,9,16,22},
            {10,13,14,17,24},
            {18,21,23,26,30}
        };

        solution.searchMatrix(matrix, 5);
        System.out.println("gg");
    }

    public static class Solution {

        public boolean searchMatrix(int[][] matrix, int target) {
            boolean res = false;
            int m = matrix.length;
            int n = matrix[0].length;
            int i = 0;
            int j = n - 1;

            while (i<m && j>=0) {
                int num = matrix[i][j];
                if (num>target) {
                    j--;
                } else if (num<target) {
                    i++;
                } else if (num == target) {
                    res = true;
                    break;
                }
            }

            return res;
        }

    }

    public static class Solution1 {

        private int[][] matrix;

        private int m;

        private int n;

        public boolean searchMatrix(int[][] matrix, int target) {
            this.matrix = matrix;
            this.m = matrix.length;
            this.n = matrix[0].length;

            boolean res = false;
            int i = 0;
            int j = 0;

            while (i<m && j<n) {
                while ( i<m && target > matrix[i][n-1] ) {
                    i++;
                }
                while ( j<n && target > matrix[m-1][j] ) {
                    j++;
                }

                if( i>=m || j>=n || target < matrix[i][j] ) {
                    break;
                }

                if( binarySearch(i, j, target) ) {
                    res = true;
                    break;
                } else {
                    i++;
                    j++;
                }
            }

            return res;
        }

        /**
         * 二分查找
         * @param i
         * @param j
         * @return
         */
        private boolean binarySearch(int i, int j, int target) {
            boolean res = false;
            // 水平搜索
            int start = j;
            int end = n-1;
            int mid;
            while ( start <= end ) {
                mid = (start+end)/2;
                if( target == matrix[i][mid] ) {
                    res = true;
                    break;
                } else if( target < matrix[i][mid] ) {
                    end = mid-1;
                } else if( target > matrix[i][mid] ) {
                    start = mid+1;
                }
            }

            if( res ) {
                return res;
            }

            // 垂直搜索
            start = i;
            end = m-1;
            while ( start <= end ) {
                mid = (start+end)/2;
                if( target == matrix[mid][j] ) {
                    res = true;
                    break;
                } else if( target < matrix[mid][j] ) {
                    end = mid-1;
                } else if( target > matrix[mid][j] ) {
                    start = mid+1;
                }
            }

            return res;
        }
    }

}

