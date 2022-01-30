package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 字符串 回溯 
// 👍 487 👎 0

/**
 * 剑指 Offer 38, 字符串的排列
 * @author Aaron Zhu
 * @date 2022-1-29
 */
public class Offer_38 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 回溯
     */
    public static class Solution {
        private Set<String> result;

        private StringBuilder sb;

        private boolean[] useFlag;

        private int length;

        public String[] permutation(String s) {
            init(s);
            search(s.toCharArray(), 0);
            return result.toArray( new String[result.size()] );
        }

        private void init(String s) {
            result = new HashSet<>();
            length = s.length();
            sb = new StringBuilder();
            useFlag = new boolean[length];
        }

        private void search(char[] chars, int index) {
            if( index==length ) {
                result.add( sb.toString() );
                return;
            }

            for(int i=0; i<length; i++) {
                if( !useFlag[i] ) {
                    useFlag[i] = true;
                    sb.append( chars[i] );

                    search(chars, index+1);

                    useFlag[i] = false;
                    sb.deleteCharAt( sb.length()-1 );
                }
            }
        }
    }
}
