package com.aaron.LeetCode;

import javax.print.DocFlavor;
import java.util.*;

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
// 👍 1842 👎 0


/**
 * 76, 最小覆盖子串
 * @author Aaron Zhu
 * @date 2022-5-3
 */
public class MinimumWindowSubstring_76{
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        solution.minWindow(s,t);
        System.out.println("gg");
    }

    /**
     * 滑动窗口
     */
    public static class Solution {
        public String minWindow(String s, String t) {
            String res = "";
            if( s==null || t==null || s.equals("") || t.equals("") || s.length()<t.length() ) {
                return res;
            }

            Map<Character, Integer> sFreq = new HashMap<>();
            Map<Character, Integer> tFreq = new HashMap<>();
            for(char ch : t.toCharArray()) {
                tFreq.compute( ch, (k,v)->{
                    if( v==null ) {
                        v = 0;
                    }
                    v++;
                    return v;
                } );
            }

            int left = 0;
            int right = 0;

            int minSubStrLength = s.length()+1;
            int minSubStrStartIndex = 0;
            int distance = 0;
            char[] chars = s.toCharArray();

            while ( right < s.length() ) {
                char rightCh = chars[right];
                // 右边界拓展时遇到无效字符, 继续拓展
                if( !tFreq.containsKey( rightCh ) ) {
                    right++;
                    continue;
                }

                // 这个字符不是重复多余的
                if( sFreq.getOrDefault(rightCh, 0) < tFreq.get(rightCh) ) {
                    distance++;
                }
                sFreq.put(rightCh, sFreq.getOrDefault(rightCh, 0)+1 );
                right++;    // 滑动窗口是左闭右开区间, 故右边界需要自增

                while ( distance == t.length() ) {
                    if( right-left < minSubStrLength ) {
                        minSubStrLength = right - left;
                        minSubStrStartIndex = left;
                    }

                    char leftCh = chars[left];
                    // 左边界收缩时遇到无效字符, 继续收缩
                    if( !tFreq.containsKey( leftCh ) ) {
                        left++;
                        continue;
                    }

                    if( sFreq.get(leftCh).equals( tFreq.get(leftCh) ) ) {
                        distance--;
                    }
                    sFreq.put(leftCh, sFreq.get(leftCh)-1 );
                    left++;
                }
            }

            if( minSubStrLength < s.length()+1 ) {
                res = s.substring(minSubStrStartIndex,  minSubStrStartIndex+minSubStrLength);
            }

            return  res;
        }
    }
}

