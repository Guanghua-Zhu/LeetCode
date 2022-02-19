package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数
//将返回左旋转两位得到的结果"cdefgab"。 
//
// 
//
// 示例 1： 
//
// 输入: s = "abcdefg", k = 2
//输出: "cdefgab"
// 
//
// 示例 2： 
//
// 输入: s = "lrloseumgh", k = 6
//输出: "umghlrlose"
// 
//
// 
//
// 限制： 
//
// 
// 1 <= k < s.length <= 10000 
// 
// Related Topics 数学 双指针 字符串 
// 👍 193 👎 0


/**
 * 剑指 Offer 58-II, 左旋转字符串
 * @author Aaron Zhu
 * @date 2022-2-19
 */
public class Offer_58_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public String reverseLeftWords(String s, int n) {
            StringBuilder sbLeft = new StringBuilder();
            StringBuilder sbRight = new StringBuilder();

            char[] chars = s.toCharArray();
            for (int i=0; i<chars.length; i++) {
                if( i<n ) {
                    sbLeft.append(chars[i]);
                } else {
                    sbRight.append(chars[i]);
                }
            }

            return sbRight.toString() + sbLeft.toString();
        }
    }

    /**
     * 字符串切片
     */
    public static class Solution2 {
        public String reverseLeftWords(String s, int n) {
            String left = s.substring(0, n);
            String right = s.substring(n);
            return right + left;
        }
    }
}

