package com.aaron.Leetcode;

import java.util.*;

//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 
// 👍 1087 👎 0

/**
 * LeetCode: 37. 解数独
 * @author Aaron Zhu
 * @date 2022-1-19
 */
public class SudokuSolver_37{

    public static void main(String[] args) {
        char[][] board = new char[][]{
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Solution solution = new Solution();
        printBoard(board);
        solution.solveSudoku(board);
        printBoard(board);
    }

    public static void printBoard(char[][] board) {
        System.out.println("----------------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 回溯法
     */
    public static class Solution {

        private boolean result;

        /**
         * 待填数的坐标列表
         */
        private List<int[]> emptyIndexList;

        public char[][] solveSudoku(char[][] board) {
            init(board);
            search(board, 0);
            return board;
        }

        private void init(char[][] board) {
            result = false;
            emptyIndexList = new ArrayList<>();
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    if(board[i][j] == '.') {
                        emptyIndexList.add( new int[]{i,j} );
                    }
                }
            }
        }

        private void search(char[][] board, int index) {
            if( result ) {
                return;
            } else if( index == emptyIndexList.size() ) {
                result = true;
                return;
            }

            int[] rowColIndex = emptyIndexList.get(index);
            for (int num=1; num<=9; num++) {
                char insertChar = Character.forDigit(num, 10);
                if( isValid(board, rowColIndex, insertChar) ) {
                    int rowIndex = rowColIndex[0];
                    int colIndex = rowColIndex[1];
                    board[rowIndex][colIndex] = insertChar;
                    search(board, index+1);
                    // 已经完成, 故直接跳出循环
                    if( result ) {
                        break;
                    }
                    board[rowIndex][colIndex] = '.';
                }
            }
        }

        private boolean isValid(char[][] board, int[] rowColIndex, char insertChar) {
            int rowIndex = rowColIndex[0];
            int colIndex = rowColIndex[1];
            Set<Character> hashset = new HashSet<>();
            hashset.add( insertChar );
            // 所在行不允许重复
            for(char ch : board[rowIndex]) {
                if( ch == '.' ) {
                    continue;
                }
                if( !hashset.add(ch) ) {
                    return false;
                }
            }

            // 所在列不允许重复
            hashset.clear();
            hashset.add( insertChar );
            for(int i=0; i<9; i++) {
                char ch = board[i][colIndex];
                if( ch == '.' ) {
                    continue;
                }
                if( !hashset.add(ch) ) {
                    return false;
                }
            }

            // 所在九宫格不允许重复
            hashset.clear();
            hashset.add( insertChar );
            int baseRowIndex = rowIndex / 3;
            int baseColIndex = colIndex / 3;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    char ch = board[ baseRowIndex*3+i ][ baseColIndex*3+j ];
                    if( ch == '.' ) {
                        continue;
                    }
                    if( !hashset.add(ch) ) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

}