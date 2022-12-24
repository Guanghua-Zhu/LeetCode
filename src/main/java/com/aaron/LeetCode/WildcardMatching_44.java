package com.aaron.LeetCode;

import java.util.*;

//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
//
// Related Topics 贪心 递归 字符串 动态规划 👍 973 👎 0


/**
 * 44, 通配符匹配
 * @author Aaron Zhu
 * @date 2022-12-12
 */
public class WildcardMatching_44{
    
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
    }

    public static class Solution2 {

        public boolean isMatch(String s, String p) {
            if( s==null || p==null) {
                return false;
            }

            int m = s.length();
            int n = p.length();
            char[] sCh = s.toCharArray();
            char[] pCh = p.toCharArray();
            boolean[][] dp = new boolean[m+1][n+1];
            dp[0][0] = true;
            // 处理第一行
            for (int i=1; i<n+1; i++) {
                if( pCh[i-1]=='*' ) {
                    dp[0][i] = true;
                } else {
                    break;
                }
            }

            for (int row=1; row<m+1; row++) {
                for (int col=1; col<n+1; col++) {
                    if( pCh[col-1]=='*') {
                        dp[row][col] = dp[row-1][col] | dp[row][col-1];
                    } else if( pCh[col-1]=='?' || pCh[col-1]==sCh[row-1] ) {
                        dp[row][col] = dp[row-1][col-1];
                    }
                }
            }

            return dp[m][n];
        }
    }

    public static class Solution1 {

        public boolean isMatch(String s, String p) {
            if( s==null || p==null) {
                return false;
            } else if( s.length()==0 &&  p.length()==0 ) {
                return true;
            } else if( s.length()==0 ) {
                for (char ch : p.toCharArray()) {
                    if( ch!='*' ) {
                        return false;
                    }
                }
                return true;
            } else if( p.length()==0 ) {
                return false;
            }

            int m = s.length();
            int n = p.length();
            char[] sCh = s.toCharArray();
            char[] pCh = p.toCharArray();
            boolean[][] dp = new boolean[m][n];

            // 处理第一行
            boolean cousum = false;
            for (int i=0; i<n; i++) {
                if( pCh[i]!='*' && pCh[i]!='?' && pCh[i]!=sCh[0] ) {
                    break;
                }

                if( pCh[i]=='*' ) {
                    dp[0][i] = i==0 ? true : dp[0][i-1];
                }

                if (pCh[i]==sCh[0] || pCh[i]=='?' ) {
                    if( cousum ) {
                        break;
                    } else {
                        dp[0][i] = true;
                        cousum = true;
                    }
                }
            }

            // 剪枝
            if( dp[0][0] == false ) {
                return false;
            }

            // 处理第一列
            if( pCh[0]=='*' ) {
                for(int i=1; i<m; i++) {
                    dp[i][0] = true;
                }
            }

            for (int row=1; row<m; row++) {
                for (int col=1; col<n; col++) {
                    if( pCh[col]=='*') {
                        dp[row][col] = dp[row-1][col] | dp[row][col-1];
                    } else if( pCh[col]=='?' || pCh[col]==sCh[row] ) {
                        dp[row][col] = dp[row-1][col-1];
                    }
                }
            }

            return dp[m-1][n-1];
        }
    }

}

