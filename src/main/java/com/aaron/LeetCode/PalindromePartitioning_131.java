package com.aaron.LeetCode;

import java.util.*;

//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 1464 👎 0


/**
 * 131, 分割回文串
 * @author Aaron Zhu
 * @date 2023-4-12
 */
public class PalindromePartitioning_131{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.partition("aab");
        System.out.println("gg");
    }

    /**
     * DP + 回溯
     */
    public static class Solution2 {

        private boolean[][] dp;

        private List<List<String>> res;

        private List<String> status;

        public List<List<String>> partition(String s) {
            init(s);
            dfs(s, 0);
            return res;
        }

        private void init(String s) {
            res = new ArrayList<>();
            status = new ArrayList<>();

            int length = s.length();
            dp = new boolean[length][length];
            for (int i=length-1; i>=0; i--) {
                for (int j=0; j<length; j++) {
                    if( i>j ) {
                        dp[i][j] = false;
                    } else if (i==j) {
                        dp[i][j] = true;
                    } else if( j-i==1 ) {
                        dp[i][j] = s.charAt(i)==s.charAt(j);
                    } else if (j-i>1) {
                        dp[i][j] = s.charAt(i)==s.charAt(j) && dp[i+1][j-1];
                    }
                }
            }
        }

        private void dfs(String s, int start) {
            if( start == s.length() ) {
                res.add( new ArrayList<>(status) );
                return;
            }

            for (int j=start; j<s.length(); j++) {
                if( dp[start][j] ) {
                    status.add( s.substring(start, j+1) );
                    dfs(s, j+1);
                    status.remove( status.size()-1 );   // 保证移除的是最后一个元素
                }
            }
        }

    }

    public static class Solution {
        public List<List<String>> partition(String s) {
            List<List<String>> res = new ArrayList<>();
            int length = s.length();
            if( length == 1) {
                res.add( Collections.singletonList(s) );
                return res;
            }

            boolean[][] dp = new boolean[length][length];
            for (int i=length-1; i>=0; i--) {
                for (int j=0; j<length; j++) {
                    if( i>j ) {
                        dp[i][j] = false;
                    } else if (i==j) {
                        dp[i][j] = true;
                    } else if( j-i==1 ) {
                        dp[i][j] = s.charAt(i)==s.charAt(j);
                    } else if (j-i>1) {
                        dp[i][j] = s.charAt(i)==s.charAt(j) && dp[i+1][j-1];
                    }
                }
            }

            Map<Integer, List<Integer>> validMap = new HashMap<>();
            for (int start=0; start<length; start++) {
                for (int end=start; end<length; end++) {
                    if( dp[start][end] ) {
                        List<Integer> list = validMap.getOrDefault(start, new ArrayList<>());
                        list.add( end+1 );
                        if( !validMap.containsKey( start ) ) {
                            validMap.put(start, list);
                        }
                    }
                }
            }

            List<List<Integer>> temp = new ArrayList<>();
            temp.add( Arrays.asList(0) );
            List<List<Integer>> resIndexList = new ArrayList<>();

            while ( !temp.isEmpty() ) {
                int size = temp.size();
                for (int i=size-1; i>=0; i--) {
                    List<Integer> tempList = temp.remove(i);
                    int lastIndex = tempList.get( tempList.size()-1 );
                    if( lastIndex==length ) {
                        resIndexList.add( tempList );
                        continue;
                    }

                    List<Integer> nextIndexs = validMap.getOrDefault(lastIndex, null);
                    if( nextIndexs==null || nextIndexs.isEmpty() ) {
                        continue;
                    }

                    for (Integer nextIndex : nextIndexs) {
                        List<Integer> newList = new ArrayList<>( tempList.size()+1 );
                        newList.addAll( tempList );
                        newList.add( nextIndex );
                        temp.add( newList );
                    }
                }
            }

            for (List<Integer> list : resIndexList) {
                List<String> strs = new ArrayList<>();
                for (int i=0; i<list.size()-1; i++) {
                    strs.add( s.substring(list.get(i), list.get(i+1)) );
                }
                res.add(strs);
            }
            return res;
        }
    }
}