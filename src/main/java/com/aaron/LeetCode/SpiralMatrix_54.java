package com.aaron.LeetCode;

import java.util.*;

//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
//
// Related Topics 数组 矩阵 模拟 👍 1275 👎 0


/**
 * 54, 螺旋矩阵
 * @author Aaron Zhu
 * @date 2022-12-26
 */
public class SpiralMatrix_54{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> resList = new ArrayList<>();
            if( matrix==null || matrix.length==0 || matrix[0].length==0 ) {
                return resList;
            }

            int m = matrix.length;
            int n = matrix[0].length;
            int left = 0;
            int right = n-1;
            int top = 0;
            int bottom = m-1;

            while (true) {
                // 从左到右
                for (int i=left; i<=right; i++) {
                    resList.add( matrix[top][i] );
                }
                top++;
                if( top > bottom) {
                    break;
                }

                // 从上到下
                for (int i=top; i<=bottom; i++) {
                    resList.add( matrix[i][right] );
                }
                right--;
                if( right < left ) {
                    break;
                }

                // 从右到左
                for (int i=right; i>=left; i--) {
                    resList.add( matrix[bottom][i] );
                }
                bottom--;
                if( bottom < top ) {
                    break;
                }

                // 从下到上
                for (int i=bottom; i>=top; i--) {
                    resList.add( matrix[i][left] );
                }
                left++;
                if( left > right ) {
                    break;
                }
            }

            return resList;
        }
    }

}



