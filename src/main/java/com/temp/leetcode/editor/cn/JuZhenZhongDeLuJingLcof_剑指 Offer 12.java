package com.temp.leetcode.editor.cn;

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
// 👍 487 👎 0


import java.util.Set;
import java.util.TreeSet;

/**
 * @author Aaron Zhu
 * @date 2022-1-17
 */
public class JuZhenZhongDeLuJingLcof_剑指 Offer 12{
  public static void main(String[] args) {
       Solution solution = new Solution();
  }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int row;

    private int col;

    private boolean exist;

    private boolean[][] usedArray;

    public boolean exist(char[][] board, String word) {
        exist = false;

        if( board==null || board[0]==null || word==null || word=="" // 判空
            || board.length*board[0].length < word.length() ) { // 剪枝
            return exist;
        }

        row = board.length;
        col = board[0].length;
        usedArray = new boolean[row][col];
        search(board, word, 0, 0, 0);
        return exist;
    }

    public void search(char[][] board, String word, int index, int i, int j) {
        if( exist ) {
            // 剪枝
            return;
        } else if( index == word.length() ) {
            // 已经全部找到, 故结束
            exist = true;
            return;
        }

        Set<int[]> indexSet = getAvailableIndexSet(index, i, j);
        for(int[] tempIndex : indexSet) {
            int rowIndex = tempIndex[0];
            int colIndex = tempIndex[1];
            if( board[rowIndex][colIndex] == word.charAt(index) ) {
                usedArray[rowIndex][colIndex] = true;
                search( board, word, index+1, rowIndex, colIndex);
                usedArray[rowIndex][colIndex] = false;
            }
        }
    }

    public Set<int[]> getAvailableIndexSet(int index, int i, int j) {
        Set<int[]> indexSet = new TreeSet<>( (a1, a2) -> {
            if( a1[0] == a2[0] && a1[1] == a2[1] ) {
                return 0;
            }
            return 1;
        });

        if( index == 0 ) {
            for(int rowIndex=0; rowIndex<row; rowIndex++) {
                for (int colIndex=0; colIndex<col; colIndex++ ) {
                    int[] tempIndex = new int[]{rowIndex, colIndex};
                    indexSet.add( tempIndex );
                }
            }
            return indexSet;
        }

        int iMax = i+1 <= row-1 ? i+1 : row-1;
        int iMin = i-1 >= 0 ? i-1 : 0;
        int jMax = j+1 <= col-1 ? j+1 : col-1;
        int jMin = j-1 >= 0 ? j-1 : 0;
        indexSet.add( new int[]{iMin, j} );
        indexSet.add( new int[]{iMax, j} );
        indexSet.add( new int[]{i, jMin} );
        indexSet.add( new int[]{i, jMax} );

        indexSet.removeIf( tempIndex -> {
            int rowIndex = tempIndex[0];
            int colIndex = tempIndex[1];
            return usedArray[rowIndex][colIndex];
        } );

        return indexSet;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

