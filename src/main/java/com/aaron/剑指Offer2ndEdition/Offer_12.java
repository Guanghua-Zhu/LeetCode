package com.aaron.剑指Offer2ndEdition;

import java.util.Set;
import java.util.TreeSet;

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


/**
 * 剑指offer·第二版: 12, 矩阵中的路径
 * @author Aaron Zhu
 * @date 2022-1-17
 */
public class Offer_12{
    public static void main(String[] args) {
       Solution solution = new Solution();
    }

    /**
     * 回溯法
     */
    public static class Solution {
        /**
         * 行数
         */
        private int row;

        /**
         * 列数
         */
        private int col;

        /**
         * 最终结果标记, true: 找到; false: 未找到
         */
        private boolean exist;

        /**
         * 网格被访问状态标记, true: 该元素被访问; false: 该元素未被访问
         */
        private boolean[][] usedArray;

        public boolean exist(char[][] board, String word) {
            if( board==null || board[0]==null || word==null || word=="" // 判空
                || board.length*board[0].length < word.length() ) { // 剪枝: 网格元素数小于字符串数量, 肯定搜索不到
                return false;
            }

            // 初始化全局变量
            init(board, word);
            // 从字符串的第一个字符开始搜索
            search(board, word, 0, 0, 0);
            return exist;
        }

        /**
         * 初始化全局变量
         * @param board
         * @param word
         */
        public void init(char[][] board, String word) {
            exist = false;
            row = board.length;
            col = board[0].length;
            usedArray = new boolean[row][col];
        }

        /**
         * 回溯法搜索
         * @param board 网格
         * @param word  字符串
         * @param index 搜字符串的第index个字符
         * @param i 网格元素的横坐标
         * @param j 网格元素的纵坐标
         */
        public void search(char[][] board, String word, int index, int i, int j) {
            // 剪枝: 只要有一次搜索成功了即可结束搜索任务
            if( exist ) {
                // 剪枝
                return;
            } else if( index == word.length() ) {
                // 搜索成功, 更新结果, 并结束
                exist = true;
                return;
            }

            // 获取选择空间
            Set<int[]> indexSet = getAvailableIndexSet(index, i, j);
            // 遍历选择空间
            for(int[] tempIndex : indexSet) {

                // 剪枝: 只要有一次搜索成功了即可结束搜索任务
                if(exist) {
                    break;
                }

                int rowIndex = tempIndex[0];
                int colIndex = tempIndex[1];
                // 字符串的第index个元素搜索成功
                if( board[rowIndex][colIndex] == word.charAt(index) ) {
                    // 将网格中相应的元素标记为 被访问状态
                    usedArray[rowIndex][colIndex] = true;
                    // 递归搜索字符串中的下一个元素
                    search( board, word, index+1, rowIndex, colIndex);
                    // 将网格中相应的元素标记为 未被访问状态, 即撤销所做出的选择, 以进行for循环的下一次遍历
                    usedArray[rowIndex][colIndex] = false;
                }
            }
        }

        /**
         * 获取当前网格元素的可选择列表
         * @param index
         * @param i
         * @param j
         * @return
         */
        public Set<int[]> getAvailableIndexSet(int index, int i, int j) {
            // 自定义比较器: 当两个数组类型的元素, 如果数组中的内容均相同则视为同一元素
            Set<int[]> indexSet = new TreeSet<>( (a1, a2) -> {
                if( a1[0] == a2[0] && a1[1] == a2[1] ) {
                    return 0;
                }
                return 1;
            });

            // 搜索字符串第一个字符时, 则可选择的空间是整个网格
            if( index == 0 ) {
                for(int rowIndex=0; rowIndex<row; rowIndex++) {
                    for (int colIndex=0; colIndex<col; colIndex++ ) {
                        int[] tempIndex = new int[]{rowIndex, colIndex};
                        indexSet.add( tempIndex );
                    }
                }
                return indexSet;
            }

            // 搜索字符串不是第一个字符时, 则可选择的空间是当前网络元素(i,j)的上、下、左、右这四个元素
            int iMax = i+1 <= row-1 ? i+1 : row-1;
            int iMin = i-1 >= 0 ? i-1 : 0;
            int jMax = j+1 <= col-1 ? j+1 : col-1;
            int jMin = j-1 >= 0 ? j-1 : 0;
            indexSet.add( new int[]{iMin, j} );
            indexSet.add( new int[]{iMax, j} );
            indexSet.add( new int[]{i, jMin} );
            indexSet.add( new int[]{i, jMax} );

            // 选择空间中的元素需要移除掉已经被访问过的
            indexSet.removeIf( tempIndex -> {
                int rowIndex = tempIndex[0];
                int colIndex = tempIndex[1];
                return usedArray[rowIndex][colIndex];
            } );

            return indexSet;
        }

    }

}

