package com.aaron.LeetCode;

import java.util.*;

//给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
//如果 needle 不是 haystack 的一部分，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "sadbutsad", needle = "sad"
//输出：0
//解释："sad" 在下标 0 和 6 处匹配。
//第一个匹配项的下标是 0 ，所以返回 0 。
// 
//
// 示例 2： 
//
// 
//输入：haystack = "leetcode", needle = "leeto"
//输出：-1
//解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= haystack.length, needle.length <= 10⁴ 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 字符串匹配 👍 1655 👎 0


/**
 * 28, 找出字符串中第一个匹配项的下标
 * @author Aaron Zhu
 * @date 2022-11-29
 */
public class FindTheIndexOfTheFirstOccurrenceInAString_28{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int strStr(String haystack, String needle) {
            int res = -1;
            if( haystack==null || needle==null || needle.length()>haystack.length() ){
                return res;
            }

            char[] hayChars = haystack.toCharArray();
            char[] needChars = needle.toCharArray();

            for(int p=0 ; p<=haystack.length()-needle.length(); p++) {
                for (int q=0; q<needChars.length; q++) {
                    char hayCh = hayChars[p+q];
                    char needCh = needChars[q];
                    if( hayCh!=needCh ) {
                        break;
                    }

                    if( q==needChars.length-1 ) {
                        return p;
                    }
                }
            }

            return res;
        }
    }

}
