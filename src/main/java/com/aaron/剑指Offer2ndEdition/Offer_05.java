package com.aaron.剑指Offer2ndEdition;

//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// Related Topics 字符串 
// 👍 196 👎 0


/**
 * 剑指offer·第二版: 05, 替换空格
 * @author Aaron Zhu
 * @date 2022-1-20
 */
public class Offer_05 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public String replaceSpace(String s) {
            if(s==null || s=="") {
                return s;
            }

            StringBuilder sb = new StringBuilder();
            for(char ch : s.toCharArray() ) {
                if (ch==' ') {
                    sb.append("%20");
                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
    }
}
