package com.aaron.LeetCode;

import java.util.*;
import java.util.stream.Collectors;

//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。 
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
// 
//
// 示例 2： 
//
// 
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中的所有字符串 互不相同 
// 
//
// Related Topics 字典树 记忆化搜索 数组 哈希表 字符串 动态规划 👍 2174 👎 0


/**
 * 139, 单词拆分
 * @author Aaron Zhu
 * @date 2023-7-1
 */
public class WordBreak_139{
    
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
    }

    /**
     * DP+剪枝优化
     */
    public static class Solution3 {

        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> words = new HashSet<>();
            int wordMaxLen = 0;
            for (String word : wordDict) {
                words.add(word);
                wordMaxLen = Math.max(wordMaxLen, word.length());
            }

            boolean[] dp = new boolean[s.length()+1];
            dp[0] = true;
            for (int i=1; i<dp.length; i++) {
                // 子串s.substring(j,i)的长度 如果 超过 字典中字符串的最大长度，显然无必要继续搜索下去
                for (int j=i-1; j>=0 && (i-j)<=wordMaxLen ; j--) {
                    if( dp[j] && words.contains( s.substring(j,i) ) ) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }
    }

    /**
     * DP
     */
    public static class Solution2 {

        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> words = new HashSet<>( wordDict );
            boolean[] dp = new boolean[s.length()+1];
            dp[0] = true;
            for (int i=1; i<dp.length; i++) {
                for (int j=0; j<i; j++) {
                    if( dp[j] && words.contains( s.substring(j,i) ) ) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[s.length()];
        }
    }

    /**
     * 记忆化搜索
     */
    public static class Solution1 {

        /**
         * Key: 当前位置索引; Value: 下一个待开始匹配的位置索引集合
         */
        private static Map<Integer, List<Integer>> index2NextsMap;

        private static boolean searchFlag;

        private static String s;

        private static List<String> wordDict;

        private static Map<Integer, Boolean> dp;

        public boolean wordBreak(String s, List<String> wordDict) {
            init(s, wordDict);
            dfs(0);
            return searchFlag;
        }

        private void init(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            index2NextsMap = new HashMap<>();
            searchFlag = false;
            dp = new HashMap<>();
        }

        private boolean dfs(int start) {
            // 记忆化搜索
            if( dp.containsKey(start) ) {
                return dp.get(start);
            }

            // base case
            if( start==s.length() ) {
                searchFlag = true;
                return searchFlag;
            } else if( start > s.length() ) {
                searchFlag = false;
                return searchFlag;
            }

            // 获取可选择列表
            List<Integer> nextStartSet = getValidNextStarts(start);
            for (int nextStart : nextStartSet ) {
                boolean res = dfs( nextStart );   //作出选择
                if( res ) {
                    dp.put(start, true);
                    return true;
                }
            }

            dp.put(start, false);
            return false;
        }

        private List<Integer> getValidNextStarts(int index) {
            if( index2NextsMap.containsKey(index) ) {
                return index2NextsMap.get( index );
            }

            Set<String> validWords = new HashSet<>();
            for (int i=index,j=0; i<s.length(); i++,j++) {
                char ch = s.charAt(i);
                if( j==0 ) {
                    for (String word : wordDict) {
                        if( ch == word.charAt(j) ) {
                            validWords.add( word );
                        }
                    }
                } else {
                    int finalJ = j;
                    validWords.removeIf(e -> e.length()>finalJ && e.charAt(finalJ) != ch );
                }
            }

            List<Integer> nextStartSet = validWords.stream()
                .map( e -> e.length()+index )
                .sorted( Comparator.reverseOrder() )
                .collect( Collectors.toList() );
            index2NextsMap.put( index, nextStartSet );
            return nextStartSet;
        }

    }

}