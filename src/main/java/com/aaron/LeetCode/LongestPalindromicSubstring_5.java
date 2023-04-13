package com.aaron.LeetCode;

import java.util.*;

//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 6393 👎 0


/**
 * 5, 最长回文子串
 * @author Aaron Zhu
 * @date 2023-4-12
 */
public class LongestPalindromicSubstring_5{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "cbbd";
        solution.longestPalindrome(s);
        System.out.println("gg");
    }

    /**
     * 动态规划
     */
    public static class Solution {
        public String longestPalindrome(String s) {
            int size = s.length();
            boolean[][] dp = new boolean[size][size];
            int maxLen = 0;
            int[] resIndex = new int[2];

            for (int i=size-1; i>=0; i--) {
                for (int j=0; j<size; j++) {
                    if( i>j ) {
                        dp[i][j] = false;
                    } else if( i==j ) {
                        dp[i][j] = true;
                    } else if( j-i==1 ) {
                        dp[i][j] = s.charAt(i)==s.charAt(j);
                    } else if( j-i>1 ) {
                        dp[i][j] = s.charAt(i)==s.charAt(j) && dp[i+1][j-1];
                    }

                    if( dp[i][j] && j-i+1 > maxLen ) {
                        maxLen = j-i+1;
                        resIndex[0] = i;
                        resIndex[1] = j;
                    }
                }
            }

            return s.substring(resIndex[0], resIndex[1]+1);
        }
    }

}