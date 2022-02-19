package com.aaron.Leetcode;

//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。 
//
// 请你实现 Trie 类： 
//
// 
// Trie() 初始化前缀树对象。 
// void insert(String word) 向前缀树中插入字符串 word 。 
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
// 。 
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length, prefix.length <= 2000 
// word 和 prefix 仅由小写英文字母组成 
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次 
// 
// Related Topics 设计 字典树 哈希表 字符串 
// 👍 1063 👎 0


/**
 * 208, 实现 Trie (前缀树)
 * @author Aaron Zhu
 * @date 2022-2-19
 */
public class ImplementTriePrefixTree_208{
    public static void main(String[] args) {
    }
}

/**
 * Trie字典树
 */
class Trie {
    /**
     * 字典树的根节点
     */
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
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
            TrieNode[] childs = current.getChilds();
            if( childs[index]==null ) {
                childs[index] = new TrieNode();
            }
            current = childs[index];
        }
        current.setEndFlag( true );
    }

    /**
     * 判断字符串是否存在于字典树
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode current = root;
        char[] chars = word.toCharArray();
        for(int i=0; i<chars.length; i++) {
            char ch = chars[i];
            int index = calcIndex(ch);
            TrieNode[] childs = current.getChilds();
            if( childs[index]==null ) {
                return false;
            }
            current = childs[index];
        }

        return current.isEndFlag();
    }

    /**
     * 判断前缀是否存在于字典树中
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        char[] chars = prefix.toCharArray();
        for(int i=0; i<chars.length; i++) {
            char ch = chars[i];
            int index = calcIndex(ch);
            TrieNode[] childs = current.getChilds();
            if( childs[index] == null ) {
                return false;
            }
            current = childs[index];
        }

        return true;
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
    public static class TrieNode {
        /**
         * 子节点数组, 0～25索引 对应于 a～z 字符
         */
        private TrieNode[] childs;

        /**
         * 当前字符是否为字符串的最后一个字符
         */
        private boolean endFlag;

        public TrieNode() {
            childs = new TrieNode[26];
            endFlag = false;
        }

        public TrieNode[] getChilds() {
            return childs;
        }

        public boolean isEndFlag() {
            return endFlag;
        }

        public void setEndFlag(boolean endFlag) {
            this.endFlag = endFlag;
        }
    }
}