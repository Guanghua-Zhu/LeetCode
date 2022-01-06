package com.aaron.剑指Offer2ndEdition;

//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个
//整数，判断数组中是否含有该整数。 
//
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// 
//
// 限制： 
//
// 0 <= n <= 1000 
//
// 0 <= m <= 1000 
//
// 
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ 
// Related Topics 数组 二分查找 分治 矩阵 
// 👍 531 👎 0

/**
 * 剑指offer·第二版: 04, 二维数组中的查找
 * @author Aaron Zhu
 * @date 2022-1-6
 */
public class Offer_04{
    
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
    }

    public static class Solution1 {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 矩阵判空
            if( matrix==null || matrix.length==0 || matrix[0].length==0) {
                return false;
            }

            // 矩阵行数
            int row = matrix.length;
            // 矩阵列数
            int col = matrix[0].length;
            // 从矩阵左下角元素开始遍历
            int i=row-1, j=0;
            while ( i>=0 && j<col ) {
                int num = matrix[i][j];
                if( target == num ) {
                    return true;
                } else if( target < num ) {
                    i--;
                } else if( target > num ) {
                    j++;
                }
            }
            return false;
        }
    }

    public static class Solution2 {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 矩阵判空
            if( matrix==null || matrix.length==0 || matrix[0].length==0) {
                return false;
            }
            // 遍历矩阵各行, 依次进行二分查找
            for(int i=0; i<matrix.length; i++) {
                int[] array = matrix[i];
                if( binarySearch(array, target) ) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 二分查找
         * @param array
         * @param target
         * @return
         */
        public boolean binarySearch(int[] array, int target) {
            if( target<array[0] || target>array[array.length-1] ) {
                return false;
            }

            int left = 0;
            int right = array.length-1;
            int mid;

            while (left <= right) {
                mid = left + (right-left)/2;
                if( target == array[mid] ) {
                    return true;
                } else if( target < array[mid] ) {
                    right = mid - 1;
                } else if( target > array[mid] ) {
                    left = mid + 1;
                }
            }
            return false;
        }
    }
}
