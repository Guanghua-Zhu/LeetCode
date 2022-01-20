package com.aaron.LeetCode;

import java.util.*;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 4
//输出：2
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 
// 
// 
// Related Topics 回溯 
// 👍 325 👎 0


/**
 * LeetCode: 52, N皇后II
 * @author Aaron Zhu
 * @date 2022-1-20
 */
public class NQueensIi_52{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {

        private int result;

        // n*n的棋盘布局, true: 表示相应网格放置了皇后, false: 表示相应网格未放置皇后
        private Boolean[][] board;

        public int totalNQueens(int n) {
            init(n);
            search(n, 0);
            return result;
        }

        private void init(int n) {
            result = 0;
            board = new Boolean[n][n];
            for(int i=0; i<n; i++) {
                Arrays.fill( board[i], false );
            }
        }

        private void search(int n, int rowIndex) {
            if(rowIndex == n) {
                result++;
                return;
            }

            for(int colIndex=0; colIndex<n; colIndex++) {
                if( isValid(rowIndex, colIndex) ) {
                    board[rowIndex][colIndex] = true;
                    search(n, rowIndex+1);
                    board[rowIndex][colIndex] = false;
                }
            }

        }

        private boolean isValid(int rowIndex, int colIndex) {
            // 所在列不能有皇后
            for(int i=0; i<board.length; i++) {
                if( board[i][colIndex]) {
                    return false;
                }
            }

            // 所在主斜线的左上方部分不能有皇后
            for(int i=1;;i++) {
                // 主斜线左上角的网格
                int x1 = rowIndex - i;
                int y1 = colIndex - i;
                // 网格均不在棋盘范围内
                if( x1<0 || y1<0 ) {
                    break;
                }
                // 存在包含皇后的网格
                if( board[x1][y1] ) {
                    return false;
                }
            }

            // 所在次斜线的右上方部分不能有皇后
            for(int i=1;; i++) {
                int x1 = rowIndex - i;
                int y1 = colIndex + i;
                if( x1<0 || y1>board.length-1 ) {
                    break;
                }
                if( board[x1][y1] ) {
                    return false;
                }
            }

            return true;
        }
    }

}
