package com.aaron.Leetcode;

import java.util.*;

//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 
// 👍 1769 👎 0


/**
 * 32, 最长有效括号
 * @author Aaron Zhu
 * @date 2022-4-9
 */
public class LongestValidParentheses_32{
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "(()))())(";
        solution.longestValidParentheses( s );
        System.out.println("gg");
    }


    /**
     * 正向、逆向 遍历
     */
    public static class Solution {
        public int longestValidParentheses(String s) {
            if( s==null || s.length()<=1 ) {
                return 0;
            }

            int maxLength = 0;
            int left = 0;
            int right = 0;

            // 正向遍历
            for(int i=0; i<s.length(); i++) {
                if( s.charAt(i)=='(' ) {
                    left++;
                }else{
                    right++;
                }

                if( left==right ) {
                    maxLength = Math.max(maxLength, left*2);
                } else if( right>left ) {
                    left = 0;
                    right = 0;
                }
            }

            left = 0;
            right = 0;
            // 反向遍历
            for(int i=s.length()-1; i>=0; i--) {
                if( s.charAt(i)=='(' ) {
                    left++;
                }else{
                    right++;
                }

                if( left==right ) {
                    maxLength = Math.max(maxLength, left*2);
                } else if( right<left ) {
                    left = 0;
                    right = 0;
                }
            }

            return maxLength;
        }
    }

    /**
     * 栈
     */
    public static class Solution2 {
        public int longestValidParentheses(String s) {
            if( s==null || s.length()<=1 ) {
                return 0;
            }
            int maxLength = 0;

            Deque<Integer> stack = new LinkedList<>();
            stack.addLast(-1);
            for (int i=0; i<s.length(); i++) {
                char ch = s.charAt(i);
                if ( ch=='(' ) {
                    stack.addLast(i);
                } else if (ch==')'){
                    stack.removeLast();
                    if( stack.isEmpty() ) {
                        stack.addLast(i);
                    } else {
                        int currentMaxlength = i - stack.peekLast();
                        maxLength = Math.max( maxLength, currentMaxlength );
                    }
                }
            }

            return maxLength;
        }
    }

    /**
     * DP动态规划
     */
    public static class Solution1 {
        public int longestValidParentheses(String s) {
            if( s==null || s.length()<=1 ) {
                return 0;
            }

            int maxLength = 0;
            int[] dp = new int[ s.length() ];
            for(int i=1; i<s.length(); i++) {
                char ch = s.charAt(i);
                if(ch == '(') {
                    dp[i] = 0;
                    continue;
                }

                // ch 为 )
                char ch1 = s.charAt(i-1);
                if( ch1=='(' ) {
                    if( i-2>=0 ) {
                        dp[i] = dp[i-2] + 2;
                    } else {
                        dp[i] = 2;
                    }
                    maxLength = Math.max(maxLength, dp[i]);
                    continue;
                }

                // ch为), ch1为)
                int subIndex = i-dp[i-1]-1;
                if( subIndex>=0 && s.charAt(subIndex)=='(') {
                    int subIndex2 = subIndex-1;
                    if( subIndex2>=0 ) {
                        dp[i] = dp[subIndex2] + dp[i-1] + 2;
                    } else {
                        dp[i] = dp[i-1] + 2;
                    }
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }

            return maxLength;
        }
    }

}
