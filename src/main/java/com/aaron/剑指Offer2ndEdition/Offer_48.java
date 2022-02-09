package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。 
//
// 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
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
// s.length <= 40000 
// 
//
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-rep
//eating-characters/ 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 364 👎 0


/**
 * 剑指 Offer 48, 最长不含重复字符的子字符串
 * @author Aaron Zhu
 * @date 2022-2-9
 */
public class Offer_48 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        if(s==null || s.length()==0) {
            return res;
        }

        Map<Character, Integer> map = new HashMap();
        char[] chars = s.toCharArray();

        for (int i=0; i<chars.length; i++) {
            char ch = chars[i];
            Integer index = map.get(ch);
            if( index == null ) {
                map.put(ch, i);
            } else {
                res = Math.max( map.size(), res );
                map.clear();
                i = index;
            }
        }
        res = Math.max( map.size(), res );
        return res;
    }
}
}


