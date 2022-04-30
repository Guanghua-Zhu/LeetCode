package com.aaron.LeetCode;

import java.util.*;

//给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。 
//
// 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。 
//
// 
//
// 示例 1： 
//
// 
//输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
// 示例 2： 
//
// 
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
// = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
// 
//
// 注意： 
//
// 
// 1 <= words.length <= 500 
// 1 <= words[i] <= 10 
// words[i] 由小写英文字母组成。 
// k 的取值范围是 [1, 不同 words[i] 的数量] 
// 
//
// 
//
// 进阶：尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。 
// Related Topics 字典树 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 
// 👍 428 👎 0

/**
 * 692, 前K个高频单词
 * @author Aaron Zhu
 * @date 2022-2-20
 */
public class TopKFrequentWords_692{
    public static void main(String[] args) {
        int res = "i".compareTo("love");
        System.out.println("gg");
    }

    /**
     * HashMap + minHeap最小堆
     */
    public static class Solution1 {
        public List<String> topKFrequent(String[] words, int k) {
            // 1. 遍历计数
            Map<String, Integer> count = new HashMap<>();
            for (String word : words) {
                count.compute(word, (key, v) -> {
                    if (v == null) {
                        v = 0;
                    }
                    v++;
                    return v;
                });
            }

            // 2. 获取Top K
            Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> {
                int res = o1.getValue() - o2.getValue();
                if (res != 0) {
                    return res;
                }
                res = o2.getKey().compareTo(o1.getKey());
                return res;
            };
            PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(comparator);

            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                if (minHeap.size() < k) {
                    minHeap.offer(entry);
                } else if (comparator.compare(entry, minHeap.peek()) > 0) {
                    minHeap.offer(entry);
                    minHeap.poll();
                }
            }

            // 3. 将最小堆的元素按降序输出
            LinkedList<String> res = new LinkedList<>();
            while (!minHeap.isEmpty()) {
                Map.Entry<String, Integer> min = minHeap.poll();
                res.addFirst(min.getKey());
            }

            return res;
        }
    }

    /**
     * Trie字典树 + DFS + minHeap最小堆
     */
    public static class Solution {
        /**
         * 获取字符串数组中Top K字符串列表
         * @param words 字符串数组
         * @param k
         * @return
         */
        public List<String> topKFrequent(String[] words, int k) {
            // 1. 遍历字符串存储到字典树中
            Trie trie = new Trie();
            for(String word : words) {
                trie.insert(word);
            }

            // 2. 通过最小堆、DFS获取Top K个字符串
            PriorityQueue<Trie.TrieNode> minHeap = new PriorityQueue<>();
            dfs(minHeap, k, trie.getRoot());

            // 3. 将最小堆的元素按降序输出
            LinkedList<String> res = new LinkedList<>();
            while( !minHeap.isEmpty() ) {
                Trie.TrieNode min = minHeap.poll();
                res.addFirst( min.getStr() );
            }

            return res;
        }

        /**
         * DFS搜索字典树
         * @param minHeap
         * @param k
         * @param current
         */
        private void dfs(PriorityQueue<Trie.TrieNode> minHeap, int k, Trie.TrieNode current) {
            if( current==null) {
                return;
            }

            // 字典树当前节点为完整的字符串
            if( current.getStr()!=null ) {
                // 最小堆中元素的数量未达到K, 则直接加入
                if( minHeap.size() < k ) {
                    minHeap.offer( current );
                } else if( current.compareTo( minHeap.peek() )>0 ) {    // 当前节点比堆中最小的元素大, 则加入
                    // 将当前节点加入最小堆
                    minHeap.offer( current );
                    // 将堆中最小的元素移出堆, 保证堆中数量始终为K
                    minHeap.poll();
                }
            }

            // DFS搜索字典树
            for(int i=0; i<26; i++) {
                Trie.TrieNode[] childs = current.getChilds();
                dfs(minHeap, k, childs[i]);
            }
        }

        /**
         * Trie字典树
         */
        public static class Trie {
            /**
             * 字典树的根节点
             */
            private TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public TrieNode getRoot() {
                return root;
            }

            /**
             * 字典树中插入字符串 word
             * @param word
             */
            public void insert(String word) {
                TrieNode current = root;
                char[] chars = word.toCharArray();
                for (int i=0; i<chars.length; i++) {
                    char ch = chars[i];
                    int index = calcIndex(ch);
                    TrieNode[] childs = current.childs;
                    if( childs[index]==null ) {
                        childs[index] = new TrieNode();
                    }
                    current = childs[index];
                }

                // 保存完整字符串信息
                current.str = word;
                // 计数值+1
                current.count += 1;
            }

            /**
             * 根据字符计算索引
             * 0～25索引 对应于 a～z 字符
             * @param ch
             * @return
             */
            private static int calcIndex(char ch) {
                return ch - 'a';
            }

            /**
             * Trie字典树节点
             */
            public static class TrieNode implements Comparable<TrieNode> {
                /**
                 * 子节点数组, 0～25索引 对应于 a～z 字符
                 */
                private TrieNode[] childs;

                /**
                 * 字符串
                 */
                private String str;

                /**
                 * 计数值
                 */
                private int count;

                public TrieNode() {
                    childs = new TrieNode[26];
                    str = null;
                    count = 0;
                }

                public TrieNode[] getChilds() {
                    return childs;
                }

                public String getStr() {
                    return str;
                }

                public int getCount() {
                    return count;
                }

                @Override
                public int compareTo(TrieNode o) {
                    // 排序规则: 先比较字符串的频率
                    int res = this.count - o.count;
                    if( res!=0 ) {
                        return res;
                    }

                    // 排序规则: 频率相同, 按字典序排序
                    res = o.str.compareTo(this.str);
                    return res;
                }
            }
        }
    }
}



