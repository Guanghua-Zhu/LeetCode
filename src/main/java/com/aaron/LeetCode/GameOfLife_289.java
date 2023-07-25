package com.aaron.LeetCode;

import java.util.*;

//根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。 
//
// 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞
// （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律： 
//
// 
// 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡； 
// 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活； 
// 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡； 
// 如果死细胞周围正好有三个活细胞，则该位置死细胞复活； 
// 
//
// 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返
//回下一个状态。 
//
// 
//
// 示例 1： 
// 
// 
//输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
//输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
// 
//
// 示例 2： 
// 
// 
//输入：board = [[1,1],[1,0]]
//输出：[[1,1],[1,1]]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 25 
// board[i][j] 为 0 或 1 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。 
// 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？ 
// 
//
// Related Topics 数组 矩阵 模拟 👍 516 👎 0


/**
 * 289, 生命游戏
 * @author Aaron Zhu
 * @date 2023-7-25
 */
public class GameOfLife_289{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 复合状态表示法
     */
    public static class Solution {
        private int m;
        private int n;

        /**
         * 方向向量
         */
        private static int[] dx = new int[]{0,  0,  1, -1, 1, -1, -1,  1};
        private static int[] dy = new int[]{1, -1,  0,  0, 1, -1,  1, -1};

        /**
         * Key: 旧值*10 + 新值; Value: 标记值
         */
        private static Map<Integer, Integer> oldnew2Flag = new HashMap<>();

        /**
         * Key: 标记值；Value：新值
         */
        private static Map<Integer, Integer> flag2New = new HashMap<>();

        static {
            oldnew2Flag.put(11, 6);
            oldnew2Flag.put(10, 7);
            oldnew2Flag.put(1, 8); // Key为 01
            oldnew2Flag.put(0, 9); // Key为 00

            flag2New.put(6,1);
            flag2New.put(7,0);
            flag2New.put(8,1);
            flag2New.put(9,0);
        }

        public void gameOfLife(int[][] board) {
            m = board.length;
            n = board[0].length;

            for (int k=0; k<2; k++) {
                for (int i=0; i<m; i++) {
                    for (int j=0; j<n; j++) {
                        if(k==0) {
                            board[i][j] = calcFlag(board, i, j);
                        } else {
                            board[i][j] = flag2New.get(board[i][j]);
                        }
                    }
                }
            }

        }

        private int calcFlag(int[][] board, int x, int y) {
            int oldValue = board[x][y];
            int newValue = -1;
            int liveNum = 0;
            for (int i=0; i<8; i++) {
                int tempX = x + dx[i];
                int tempY = y + dy[i];
                if( tempX<0 || tempY<0 || tempX>=m || tempY>=n ) {
                    continue;
                }
                int temp = board[tempX][tempY];
                if( temp==1 || temp==6 || temp==7) {
                    liveNum++;
                }
            }

            if( oldValue==0 )  {
                if( liveNum==3 ) {
                    newValue = 1;
                } else {
                    newValue = 0;
                }
            } else {
                if( liveNum<2 || liveNum>3 ) {
                    newValue = 0;
                } else {
                    newValue = 1;
                }
            }

            int flag = oldnew2Flag.get( oldValue*10+newValue );
            return flag;
        }
    }

    /**
     * 辅助数组法
     */
    public static class Solution1 {
        private int m;
        private int n;

        private int[][] aux;

        /**
         * 方向向量
         */
        private static int[] dx = new int[]{0,  0,  1, -1, 1, -1, -1,  1};
        private static int[] dy = new int[]{1, -1,  0,  0, 1, -1,  1, -1};

        public void gameOfLife(int[][] board) {
            m = board.length;
            n = board[0].length;
            aux = new int[m][n];
            for (int i=0; i<m; i++) {
                aux[i] = Arrays.copyOf(board[i], n);
            }

            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    board[i][j] = calc(i,j);
                }
            }
        }

        private int calc(int x, int y) {
            int cur = aux[x][y];
            int liveNum = 0;
            for (int i=0; i<8; i++) {
                int tempX = x + dx[i];
                int tempY = y + dy[i];
                if( tempX<0 || tempY<0 || tempX>=m || tempY>=n ) {
                    continue;
                }
                if( aux[tempX][tempY] == 1 ) {
                    liveNum++;
                }
            }

            if( cur==0 )  {
                if( liveNum==3 ) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if( liveNum<2 || liveNum>3 ) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    }
}