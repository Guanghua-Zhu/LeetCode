package com.aaron.Leetcode;

import java.util.*;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 7109 👎 0


/**
 * 3, 无重复字符的最长子串
 * @author Aaron Zhu
 * @date 2022-3-13
 */
public class LongestSubstringWithoutRepeatingCharacters_3{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.lengthOfLongestSubstring("dvdf");
        System.out.println("gg");
    }

    /**
     * 双指针
     */
    public static  class Solution {
        public int lengthOfLongestSubstring(String s) {
            if( s==null || s.length()==0 ) {
                return 0;
            }

            int res = 0;
            char[] chars = s.toCharArray();
            Set<Character> set = new HashSet<>();
            int r = 0;
            for(int l=0; l<chars.length; l++) {
                for ( ;r<chars.length; r++) {
                    // 说明发生重复
                    if( !set.add(chars[r]) ) {
                        res = Math.max(res, set.size());
                        // 删除最左边的元素
                        set.remove(chars[l]);
                        break;
                    }
                }
            }

            res = Math.max(res, set.size());
            return res;
        }
    }

    /**
     * 哈希表
     */
    public static class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            if( s==null || s.length()==0 ) {
                return 0;
            }

            int res = 0;
            Map<Character, Integer> map = new LinkedHashMap<>();
            char[] chars = s.toCharArray();
            for(int i=0; i<chars.length; i++) {
                char ch = chars[i];
                Integer index = map.get(ch);
                if (index != null) {    // 说明ch字符已经发生重复了
                    res = Math.max(res, map.size());
                    Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<Character, Integer> entry = iterator.next();
                        int lastIndex = entry.getValue();
                        if (lastIndex <= index) {
                            iterator.remove();
                        } else {
                            break;
                        }
                    }
                }
                map.put(ch, i);
            }
            res = Math.max(res, map.size());
            return res;
        }


    }

}