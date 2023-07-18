package com.aaron.LeetCode;

import java.util.*;

//给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode"
//输出: 0
// 
//
// 示例 2: 
//
// 
//输入: s = "loveleetcode"
//输出: 2
// 
//
// 示例 3: 
//
// 
//输入: s = "aabb"
//输出: -1
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 10⁵ 
// s 只包含小写字母 
// 
//
// Related Topics 队列 哈希表 字符串 计数 👍 682 👎 0


/**
 * 387, 字符串中的第一个唯一字符
 * @author Aaron Zhu
 * @date 2023-7-18
 */
public class FirstUniqueCharacterInAString_387{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 基于数组计数
     */
    public static class Solution {
        public int firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            int[] counts = new int[26];
            for (char ch : chars) {
                counts[ch-'a'] += 1;
            }

            for (int i=0; i<chars.length; i++) {
                if(counts[ chars[i]-'a' ] == 1) {
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * 基于Map计数
     */
    public static class Solution1 {
        public int firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            Map<Character, Integer> counts = new HashMap<>();

            for (char ch : chars) {
                counts.put( ch, counts.getOrDefault(ch, 0)+1 );
            }

            for (int i=0; i<chars.length; i++) {
                if(counts.get( chars[i] ) == 1) {
                    return i;
                }
            }
            return -1;
        }
    }
}