package com.aaron.LeetCode;

import java.util.*;
import java.util.stream.Collectors;

//给定一个字符串 s 和一个字符串字典
// wordDict ，在字符串
// s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。 
//
// 注意：词典中的同一个单词可能在分段中被重复使用多次。 
//
// 
//
// 示例 1： 
//
// 
//输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
//输出:["cats and dog","cat sand dog"]
// 
//
// 示例 2： 
//
// 
//输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine",
//"pineapple"]
//输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//输出:[]
// 
//
// 
//
// 提示： 
//
// 
// 
//
// 
// 1 <= s.length <= 20 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 10 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中所有字符串都 不同 
// 
//
// Related Topics 字典树 记忆化搜索 数组 哈希表 字符串 动态规划 回溯 👍 697 👎 0


/**
 * 140, 单词拆分 II
 * @author Aaron Zhu
 * @date 2023-7-1
 */
public class WordBreakIi_140{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("gg");
    }

    /**
     * 回溯+记忆化搜索
     */
    public static class Solution {
        private static Set<String> wordDictSet;

        private static String s;

        private static Map<Integer, List<List<String>>> cache;

        private static Integer maxLengthOfWord;

        public List<String> wordBreak(String s, List<String> wordDict) {
            init(s, wordDict);
            List<List<String>> res = dfs(0);
            return res.stream()
                .map( list -> list.stream().collect(Collectors.joining(" ")) )
                .collect(Collectors.toList());
        }

        private void init(String s, List<String> wordDict) {
            this.s = s;
            wordDictSet = new HashSet<>( wordDict );
            cache = new HashMap<>();

            maxLengthOfWord = 0;
            for (String word : wordDict) {
                maxLengthOfWord = Math.max(maxLengthOfWord, word.length());
            }
        }

        private List<List<String>> dfs(int index) {
            // 记忆化搜索
            if( cache.containsKey(index) ) {
                return cache.get(index);
            }

            List<List<String>> res = new ArrayList<>();
            // base case
            if( index==s.length() ) {
                res.add( new ArrayList<>() );
                return res;
            }

            // 获取可选择列表
            List<String> validWords = getValidWords(index);
            for (String word : validWords ) {
                List<List<String>> subRes = dfs( index+word.length() );
                for (List<String> wordList : subRes) {
                    LinkedList tempWordList = new LinkedList( wordList );
                    tempWordList.offerFirst( word );
                    res.add( tempWordList );
                }
            }

            cache.put(index, res);
            return res;
        }

        private List<String> getValidWords(int index) {
            List<String> validWords = new ArrayList<>();
            for (int i=index+1; i<=s.length() && (i-index) <= maxLengthOfWord ; i++) {
                String word = s.substring(index, i);
                if( wordDictSet.contains( word ) ) {
                    validWords.add( word );
                }
            }
            return validWords;
        }
    }

    /**
     * 暴力回溯
     */
    public static class Solution1 {
        private static Set<String> wordDictSet;

        private static List<String> status;

        private static List<List<String>> res;

        private static String s;

        public List<String> wordBreak(String s, List<String> wordDict) {
            init(s, wordDict);
            dfs(0);
            return res.stream()
                .map( list -> list.stream().collect(Collectors.joining(" ")) )
                .collect(Collectors.toList());
        }

        private void init(String s, List<String> wordDict) {
            this.s = s;
            wordDictSet = new HashSet<>( wordDict );
            status = new ArrayList<>();
            res =  new ArrayList<>();
        }

        private void dfs(int start) {
            // base case
            if( start==s.length() ) {
                res.add( new ArrayList<>( status ) );
                return;
            } else if( start > s.length() ) {
                return;
            }

            // 获取可选择列表
            List<String> nextWords = getValidNextWords(start);
            for (String nextWord : nextWords ) {
                //作出选择：添加到列表尾部
                status.add( nextWord );
                dfs( start+nextWord.length() );
                // 撤销选择：移除列表尾部最后元素
                status.remove( status.size()-1 );
            }
        }

        private List<String> getValidNextWords(int index) {
            List<String> validNextWordList = new ArrayList<>();
            for (int i=index+1; i<=s.length(); i++) {
                String tempWord = s.substring(index, i);
                if( wordDictSet.contains( tempWord ) ) {
                    validNextWordList.add( tempWord );
                }
            }
            return validNextWordList;
        }

    }

}