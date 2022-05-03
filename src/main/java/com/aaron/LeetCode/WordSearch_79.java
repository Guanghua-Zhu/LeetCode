package com.aaron.LeetCode;

import java.util.*;

//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 
// 👍 1284 👎 0


/**
 * 79, 单词搜索
 * @author Aaron Zhu
 * @date 2022-5-3
 */
public class WordSearch_79{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 回溯
     */
    public static class Solution {
        /**
         * 方向向量
         */
        private static int[] dx = new int[]{1,0,-1,0};
        private static int[] dy = new int[]{0,1,0,-1};

        private boolean[][] usedFlag;

        private boolean isExist;

        private int m;

        private int n;

        private char[] chars;

        public boolean exist(char[][] board, String word) {
            if( board==null || board.length==0 || board[0]==null || board[0].length==0
                || word==null || word.length()==0
                || board.length*board[0].length < word.length() ) {
                return false;
            }

            init(board, word);
            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if( isExist ) {
                        break;
                    } else if ( board[i][j] == chars[0] ) {
                        usedFlag[i][j] = true;
                        search(board, i, j, 1);
                        usedFlag[i][j] = false;
                    }
                }
            }

            return isExist;
        }

        private void init(char[][] board, String word) {
            chars = word.toCharArray();
            m = board.length;
            n = board[0].length;
            usedFlag = new boolean[m][n];
            isExist = false;
        }

        private void search(char[][] board, int i, int j, int k) {
            if ( k == chars.length ) {
                isExist = true;
                return;
            }

            for (int d=0; d<dx.length; d++) {
                // 剪枝
                if( isExist ) {
                    break;
                }

                int newI = i + dx[d];
                int newJ = j + dy[d];
                if( newI>=m || newI<0 || newJ>=n || newJ<0
                    || usedFlag[newI][newJ] || chars[k] != board[newI][newJ] ) {
                    continue;
                }

                usedFlag[newI][newJ] = true;
                search(board, newI, newJ, k+1);
                usedFlag[newI][newJ] = false;
            }
        }
    }

}

