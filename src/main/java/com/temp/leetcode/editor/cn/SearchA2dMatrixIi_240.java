package com.temp.leetcode.editor.cn;

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
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        if( target<matrix[0][0] || target > matrix[m-1][n-1] ) {
            return false;
        }

        int leftBoard = 0;
        int rightBoard = n-1;
        int topBoard = 0;
        int bottomBoard = m-1;

        while (true) {

            // 上边界
            while ( true ) {
                if( target<matrix[topBoard][leftBoard] || target>matrix[topBoard][rightBoard] ) {
                    topBoard++;
                } else {
                    break;
                }
                if( topBoard > bottomBoard ) {
                    return false;
                }
            }

            // 下边界
            while ( true ) {
                if( target<matrix[bottomBoard][leftBoard] || target>matrix[bottomBoard][rightBoard] ) {
                    bottomBoard--;
                } else {
                    break;
                }
                if( topBoard > bottomBoard ) {
                    return false;
                }
            }

            // 左边界
            while ( true ) {
                if( target<matrix[topBoard][leftBoard] || target>matrix[bottomBoard][leftBoard] ) {
                    leftBoard++;
                } else {
                    break;
                }
                if( leftBoard > rightBoard ) {
                    return false;
                }
            }

            // 右边界
            while ( true ) {
                if( target<matrix[topBoard][rightBoard] || target>matrix[bottomBoard][rightBoard] ) {
                    rightBoard--;
                } else {
                    break;
                }
                if( leftBoard > rightBoard ) {
                    return false;
                }
            }

            if( topBoard==bottomBoard && leftBoard==rightBoard ) {
                if( matrix[topBoard][leftBoard]==target ) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
