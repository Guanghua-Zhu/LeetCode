package com.aaron.LeetCode;

import java.util.*;

//如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。 
//
// 字母和数字都属于字母数字字符。 
//
// 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "A man, a plan, a canal: Panama"
//输出：true
//解释："amanaplanacanalpanama" 是回文串。
// 
//
// 示例 2： 
//
// 
//输入：s = "race a car"
//输出：false
//解释："raceacar" 不是回文串。
// 
//
// 示例 3： 
//
// 
//输入：s = " "
//输出：true
//解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
//由于空字符串正着反着读都一样，所以是回文串。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2 * 10⁵ 
// s 仅由可打印的 ASCII 字符组成 
// 
//
// Related Topics 双指针 字符串 👍 626 👎 0


/**
 * 125, 验证回文串
 * @author Aaron Zhu
 * @date 2023-4-4
 */
public class ValidPalindrome_125{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public boolean isPalindrome(String s) {
            if( s==null || s.length()==0 ) {
                return true;
            }

            s = s.toLowerCase();

            int left = 0;
            int right = s.length()-1;

            while (left < right) {
                while ( left<s.length() ) {
                    if( isValidChar(s.charAt(left)) ) {
                        break;
                    }
                    left++;
                }

                while ( right>=0 ) {
                    if( isValidChar(s.charAt(right)) ) {
                        break;
                    }
                    right--;
                }

                if( left>=s.length() || right<0 ) {
                    break;
                } else if ( s.charAt(left) != s.charAt(right) ) {
                    return false;
                }
                left++;
                right--;
            }

            return true;
        }

        private boolean isValidChar(char ch) {
            if( (ch>='a' && ch<='z') || (ch>='0' && ch<='9') ){
                return true;
            } else {
                return false;
            }
        }
    }
}


