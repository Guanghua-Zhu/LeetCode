package com.aaron.LeetCode;

import java.util.*;

//给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。 
//
// 回文字符串 是正着读和倒过来读一样的字符串。 
//
// 子字符串 是字符串中的由连续字符组成的一个序列。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 905 👎 0


/**
 * 647, 回文子串
 * @author Aaron Zhu
 * @date 2022-6-25
 */
public class PalindromicSubstrings_647{
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countSubstrings("fdsklf");
        System.out.println("gg");
    }

    /**
     * 按回文中心一次检测
     */
    public static class Solution {
        public int countSubstrings(String s) {
            int res = 0;
            int size = s.length();
            char[] chars = s.toCharArray();

            for (double i=0; i<=size-1; i=i+0.5) {
                int left;
                int right;
                if( (2*i)%2 == 1) {  // 检测偶数回文串
                    left = (int)(i-0.5);
                    right = (int)(i+0.5);
                } else {    // 检测奇数回文串
                    left = (int)(i);
                    right = left;
                }

                for ( ; left>=0 && right<size; left--, right++) {
                    if( chars[left]!=chars[right] ) {
                        break;
                    }
                    res++;
                }
            }

            return res;
        }
    }

    /**
     * DP
     */
    public static class Solution1 {
        public int countSubstrings(String s) {
            int res = 0;
            int size = s.length();
            char[] chars = s.toCharArray();
            boolean[][] dp = new boolean[size][size];
            for (int j=0; j<size; j++) {
                for (int i=0; i<=j; i++) {
                    if( chars[i]==chars[j] && (j-i<2 || dp[i+1][j-1]) ) {
                        dp[i][j] = true;
                        res++;
                    }
                }
            }

            return res;
        }
    }
}

