package com.aaron.LeetCode;

import java.util.*;

//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 2327 👎 0


/**
 * 72, 编辑距离
 * @author Aaron Zhu
 * @date 2022-4-26
 */
public class EditDistance_72{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP动态规划
     */
    public static class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            if( m*n == 0 ) {
                return Math.max(m, n);
            }

            int[][] lev = new int[m+1][n+1];

            // 字符串word1从空串 变为 字符串word2 前j个字符 的莱文斯坦距离
            for (int j=0; j<n+1; j++) {
                lev[0][j] = j;
            }
            // 字符串word1从前i个字符 变为 空串 的莱文斯坦距离
            for (int i=0; i<m+1; i++) {
                lev[i][0] = i;
            }

            for (int i=1; i<m+1; i++) {
                for (int j=1; j<n+1; j++) {
                    // 在 字符串A的前i个字符 与 字符串B的前j-1个字符 完全相同的基础上, 进行一次插入操作
                    int countByInsert = lev[i][j-1] + 1;
                    // 在 字符串A的前i-1个字符 与 字符串B的前j个字符 完全相同的基础上, 进行一次删除操作
                    int countByDel = lev[i-1][j] + 1;
                    // 在 字符串A的前i-1个字符 与 字符串B的前j-1个字符 完全相同的基础上, 进行一次替换操作
                    int countByReplace =  word1.charAt(i-1)==word2.charAt(j-1) ? lev[i-1][j-1] : lev[i-1][j-1]+1;
                    // 计算 字符串A的前i个字符 与 字符串B的前j个字符 的莱文斯坦距离
                    lev[i][j] = min( countByInsert, countByDel, countByReplace );
                }
            }

            return lev[m][n];
        }

        private int min(int a, int b, int c) {
            int temp = Math.min(a,b);
            int res= Math.min(temp, c);
            return res;
        }
    }
}