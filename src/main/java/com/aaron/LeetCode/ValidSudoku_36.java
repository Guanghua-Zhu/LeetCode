package com.aaron.LeetCode;

import java.util.*;

//请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 
//
// 注意： 
//
// 
// 一个有效的数独（部分已被填充）不一定是可解的。 
// 只需要根据以上规则，验证已经填入的数字是否有效即可。 
// 空白格用 '.' 表示。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：board = 
//[["5","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = 
//[["8","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：false
//解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无
//效的。 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字（1-9）或者 '.' 
// 
//
// Related Topics 数组 哈希表 矩阵 👍 993 👎 0


/**
 * 36, 有效的数独
 * @author Aaron Zhu
 * @date 2022-12-4
 */
public class ValidSudoku_36{
    
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
    }

    public static class Solution2 {
        public boolean isValidSudoku(char[][] board) {
            int[][] rowCount = new int[9][9];
            int[][] colCount = new int[9][9];
            int[][] matCount = new int[9][9];

            for (int i=0; i<9; i++) {
                for (int j=0; j<9; j++) {
                    char ch = board[i][j];
                    if( ch=='.' ) {
                        continue;
                    }

                    int chIndex = ch - '1';
                    int matIndex = i/3 * 3 + j/3;

                    rowCount[i][chIndex]++;
                    colCount[j][chIndex]++;
                    matCount[matIndex][chIndex]++;

                    if( rowCount[i][chIndex]>1 || colCount[j][chIndex]>1 || matCount[matIndex][chIndex]>1 ) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    public static class Solution1 {
        public boolean isValidSudoku(char[][] board) {
            Map<Integer, Set<Character>> rowMap = new HashMap();
            Map<Integer, Set<Character>> colMap = new HashMap();
            Map<Integer, Set<Character>> matMap = new HashMap();
            init(rowMap);
            init(colMap);
            init(matMap);

            for (int i=0; i<9; i++) {
                for (int j=0; j<9; j++) {
                    char ch = board[i][j];
                    if( ch=='.' ) {
                        continue;
                    }

                    int matMapIndex = i/3 * 3 + j/3;
                    if( rowMap.get(i).contains(ch)
                        || colMap.get(j).contains(ch)
                        || matMap.get(matMapIndex).contains(ch) ) {
                        return false;
                    }

                    rowMap.get(i).add( ch );
                    colMap.get(j).add( ch );
                    matMap.get( matMapIndex ).add( ch );
                }
            }

            return true;
        }

        private void init(Map<Integer, Set<Character>> map) {
            for (int i=0; i<9; i++) {
                map.put(i, new HashSet<>());
            }
        }
    }

}