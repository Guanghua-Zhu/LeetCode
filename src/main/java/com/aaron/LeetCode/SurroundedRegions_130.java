package com.aaron.LeetCode;

import java.util.*;

//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
//
// 
// 
// 
// 
// 
//
// 示例 1： 
// 
// 
//输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O",
//"X","X"]]
//输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
// 
//
// 示例 2： 
//
// 
//输入：board = [["X"]]
//输出：[["X"]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] 为 'X' 或 'O' 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 948 👎 0


/**
 * 130, 被围绕的区域
 * @author Aaron Zhu
 * @date 2023-4-6
 */
public class SurroundedRegions_130{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = new char[][] {
            {'X','O','X','O','X','O'},
            {'O','X','O','X','O','X'},
            {'X','O','X','O','X','O'},
            {'O','X','O','X','O','X'},
        };

        solution.solve(board);
        System.out.println("gg");
    }

    /**
     * BFS
     */
    public static class Solution {
        /**
         * 方向向量: 上、下、左、右
         */
        private int[] dx = new int[]{ -1,  1,   0,  0 };
        private int[] dy = new int[]{  0,  0,  -1,  1 };

        /**
         * 矩阵、行、列
         */
        private char[][] board;
        private int m;
        private int n;

        /**
         * 与边界连通的O
         */
        boolean[][] markByO;

        private Queue<int[]> queue;

        public void solve(char[][] board) {
            init(board);
            if( m<=2 || n<=2 ) {
                return;
            }

            // 上边界: 从左到右遍历
            for(int i=0, j=0; j<n; j++) {
                bfs( new int[]{i,j} );
            }

            // 下边界: 从左到右遍历
            for(int i=m-1, j=0; j<n; j++) {
                bfs( new int[]{i,j} );
            }

            // 左边界: 从上到下遍历
            for (int i=0, j=0; i<m; i++) {
                bfs( new int[]{i,j} );
            }

            // 右边界: 从上到下遍历
            for (int i=0, j=n-1; i<m; i++) {
                bfs( new int[]{i,j} );
            }

            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if( !markByO[i][j] && board[i][j]=='O' ) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void init(char[][] board) {
            this.board = board;
            m = board.length;
            n = board[0].length;
            // 与边框连通的O
            markByO = new boolean[m][n];
            queue = new LinkedList<>();
        }

        private void bfs(int[] start) {
            int startX = start[0];
            int startY = start[1];
            if( markByO[startX][startY] || board[startX][startY]!='O' ) {
                return;
            }

            queue.clear();
            queue.offer( start );
            markByO[startX][startY] = true;

            while ( !queue.isEmpty() ) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                // 按指定方向进行搜索
                for (int i=0; i<4; i++) {
                    int x1 = x + dx[i];
                    int y1 = y + dy[i];
                    // 边界检查
                    if( x1<0 || x1>=m || y1<0 || y1>=n ) {
                        continue;
                    }
                    if( !markByO[x1][y1] && board[x1][y1]=='O' ) {
                        queue.offer( new int[]{x1,y1} );
                        markByO[x1][y1] = true;
                    }
                }
            }

        }
    }

}