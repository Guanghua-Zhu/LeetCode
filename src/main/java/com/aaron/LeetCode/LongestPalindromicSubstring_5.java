package com.aaron.LeetCode;

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
// Related Topics 字符串 动态规划 
// 👍 4917 👎 0


/**
 * 5, 最长回文子串
 * @author Aaron Zhu
 * @date 2022-3-23
 */
public class LongestPalindromicSubstring_5{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP动态规划
     */
    public static class Solution {
        public String longestPalindrome(String s) {
            if(s.length() == 1) {
                return s;
            }

            int maxLen = 1;
            int startIndex = 0;

            char[] chars = s.toCharArray();
            int size = chars.length;
            boolean[][] dp = new boolean[size][size];
            for(int i=0; i<size; i++) {
                dp[i][i] = true;
            }

            for(int len=2; len<=size; len++) {
                for (int i=0; i<size; i++) {
                    int j = i+len-1;
                    if( j>=size ) {
                        break;
                    }

                    if( chars[i] != chars[j] ) {
                        dp[i][j] = false;
                    } else {
                        if( j-i<3 ) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i+1][j-1];
                        }
                    }

                    if( dp[i][j] && j-i+1>maxLen ) {
                        maxLen = j-i+1;
                        startIndex = i;
                    }
                }
            }

            return s.substring(startIndex, startIndex+maxLen);
        }
    }

    /**
     * 中心扩展法
     */
    public static class Solution2 {
        public String longestPalindrome(String s) {
            if(s.length() == 1) {
                return s;
            }

            int start = 0;
            int end = 0;
            char[] chars = s.toCharArray();
            for(int i=0; i<chars.length; i++) {
                int len1 = expand(chars, i, i);
                int len2 = expand(chars, i, i+1);
                int len = Math.max(len1, len2);
                if( len > end-start ) {
                    start = i - (len-1)/2;
                    end = i + len/2;
                }
            }

            return s.substring(start, end+1);
        }

        private int expand(char[] chars, int left, int right) {
            while ( left>=0 && right<=chars.length-1 && chars[left]==chars[right] ) {
                left--;
                right++;
            }

            return right - left - 1;
        }
    }

}

