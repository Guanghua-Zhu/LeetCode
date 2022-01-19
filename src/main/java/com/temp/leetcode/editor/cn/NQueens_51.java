package com.temp.leetcode.editor.cn;

//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
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
// Related Topics 数组 回溯 
// 👍 1149 👎 0


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Boolean.FALSE;

/**
 * @author Aaron Zhu
 * @date 2022-1-19
 */
public class NQueens_51{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private List<List<String>> result;

    // n*n的棋盘布局, true: 表示相应网格放置了皇后, false: 表示相应网格未放置皇后
    private Boolean[][] board;

    public List<List<String>> solveNQueens(int n) {
        init(n);
        search(n, 0);
        return result;
    }

    private void init(int n) {
        result = new LinkedList<>();
        board = new Boolean[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill( board[i], false );
        }
    }

    private void search(int n, int rowIndex) {
        if(rowIndex == n) {
            convert();
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

        // 所在主斜线不能有皇后
        for(int i=0;;i++) {
            // 主斜线左上角的网格
            int x1 = rowIndex - i;
            int y1 = colIndex - i;
            boolean grid1Exist = x1>=0 && y1>=0;
            // 主斜线右下角的网格
            int x2 = rowIndex + i;
            int y2 = colIndex + i;
            boolean grid2Exist = x2<board.length && y2<board.length;
            // 两个网格均不在棋盘范围内
            if( !grid1Exist && !grid2Exist ) {
                break;
            }
            // 存在包含皇后的网格
            if( (grid1Exist && board[x1][y1])
                || (grid2Exist && board[x2][y2]) ) {
                return false;
            }
        }

        // 所在次斜线不能有皇后
        int sum = rowIndex+colIndex;
        for(int i=0;i<=sum; i++) {
            int x1 = i;
            int y1 = sum-i;
            if( x1>=board.length || y1>=board.length ) {
                continue;
            }
            if( board[x1][y1] ) {
                return false;
            }
        }

        return true;
    }

    private void convert() {
        List<String> tempList = new LinkedList<>();
        for(int i=0; i<board.length; i++) {
            String rowStr = Arrays.stream( board[i] )
                .map( e -> {
                    if( e ) {
                        return "Q";
                    }
                    return ".";
                } )
                .collect(Collectors.joining());
            tempList.add( rowStr );
        }

        result.add( tempList );
    }



}
//leetcode submit region end(Prohibit modification and deletion)

