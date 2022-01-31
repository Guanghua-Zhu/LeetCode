package com.aaron.剑指Offer2ndEdition;

//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
//
// 示例 1：
// 输入：matrix = [
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
// ]
// 输出：[1,2,3,6,9,8,7,4,5]
// 
// 示例 2：
//
// 输入：matrix = [
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
// ]
// 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
//
// 限制： 
// 0 <= matrix.length <= 100
// 0 <= matrix[i].length <= 100 
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
// Related Topics 数组 矩阵 模拟 
// 👍 350 👎 0

/**
 * 剑指 Offer 29, 顺时针打印矩阵
 * @author Aaron Zhu
 * @date 2022-1-31
 */
public class Offer_29 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if( matrix==null || matrix.length==0 || matrix[0].length==0 ) {
            return new int[0];
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m*n];

        // 左边界
        int l = 0;
        // 右边界
        int r = n-1;
        // 上边界
        int t = 0;
        // 下边界
        int b = m-1;
        int index = 0;
        while (true) {
            // 从左向右
            for(int i=l; i<=r; i++) {
                result[index] = matrix[t][i];
                index++;
            }
            t++;
            if( t>b ){
                break;
            }

            // 从上到下
            for(int i=t; i<=b; i++) {
                result[index] = matrix[i][r];
                index++;
            }
            r--;
            if( r<l ) {
                break;
            }

            // 从右到左
            for(int i=r; i>=l; i--) {
                result[index] = matrix[b][i];
                index++;
            }
            b--;
            if( b<t ) {
                break;
            }

            // 从下到上
            for(int i=b; i>=t; i--) {
                result[index] = matrix[i][l];
                index++;
            }
            l++;
            if( l>r ){
                break;
            }
        }
        return result;
    }
}

    public static class Solution1 {
    /**
     * 方向向量: 右、下、左、上
     */
    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};

    public int[] spiralOrder(int[][] matrix) {
        if( matrix==null || matrix.length==0 || matrix[0].length==0 ) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m*n];
        result[0] = matrix[0][0];

        int right = n-1;
        int down = m-1;
        int left = 0;
        int up = 1;

        int flag = 0;
        int i=0;
        int j=0;
        int index = 1;
        while (index < m*n) {
            // 向右
            if( flag==0 ) {
                if(j<right) {
                    i += dx[flag];
                    j += dy[flag];
                    result[index] = matrix[i][j];
                    index++;
                } else {
                    flag = 1;
                    right = right-1;
                }
                continue;
            }

            // 向下
            if( flag==1 ) {
                if(i<down) {
                    i += dx[flag];
                    j += dy[flag];
                    result[index] = matrix[i][j];
                    index++;
                } else {
                    flag = 2;
                    down = down-1;
                }
                continue;
            }

            // 向左
            if( flag==2 ) {
                if(j>left) {
                    i += dx[flag];
                    j += dy[flag];
                    result[index] = matrix[i][j];
                    index++;
                } else {
                    flag = 3;
                    left = left+1;
                }
                continue;
            }

            // 向上
            if( flag==3 ) {
                if(i>up) {
                    i += dx[flag];
                    j += dy[flag];
                    result[index] = matrix[i][j];
                    index++;
                } else {
                    flag = 0;
                    up = up+1;
                }
                continue;
            }
        }

        return result;
    }
}
}

