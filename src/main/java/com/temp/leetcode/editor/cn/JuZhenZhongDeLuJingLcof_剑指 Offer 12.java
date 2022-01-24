package com.temp.leetcode.editor.cn;

import java.util.*;

//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。 
//
// 
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
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/ 
// Related Topics 数组 回溯 矩阵 
// 👍 493 👎 0


/**
 * @author Aaron Zhu
 * @date 2022-1-24
 */
public class JuZhenZhongDeLuJingLcof_剑指 Offer 12{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 方向向量: 上、下、左、右
    private int[] dx = new int[]{1, 0, -1, 0};
    private int[] dy = new int[]{0, 1, 0, -1};

    private int m;

    private int n;

    private boolean[][] visitFlag;

    private Queue<int[]> queue;

    private int matchIndex;

    public boolean exist(char[][] board, String word) {
        if( board==null || board[0]==null || word==null || word=="" // 判空
            || board.length*board[0].length < word.length() ) { // 剪枝: 网格元素数小于字符串数量, 肯定搜索不到
            return false;
        }

        m = board.length;
        n = board[0].length;
        char[] wordCharArray = word.toCharArray();


        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                char start = board[m][n];
                if( start != wordCharArray[0] ) {
                    continue;
                }

                // Init
                init(i, j);
                // BFS
                while ( matchIndex<wordCharArray.length && !queue.isEmpty() ) {
                    int[] currentIndex = queue.poll();
                    int currentRowIndex = currentIndex[0];
                    int currentColIndex = currentIndex[1];

                    for(int k=0; k<4; k++) {
                        int x = currentRowIndex + dx[k];
                        int y = currentColIndex + dy[k];
                        if( x<0 || x>=m || y<0 || y>=n
                            ||    )

                    }


                }
                //
                if( matchIndex==wordCharArray.length ) {
                    return true;
                }
            }
        }


    }

    private void init(int i, int j) {
        visitFlag = new boolean[m][n];
        queue = new LinkedList<>();

        matchIndex = 1;
        queue.add( new int[]{i,j} );
        visitFlag[i][j] = true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
