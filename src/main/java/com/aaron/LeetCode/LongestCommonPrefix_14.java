package com.aaron.LeetCode;

import javax.print.DocFlavor;
import java.util.*;

//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 2534 👎 0


/**
 * 14, 最长公共前缀
 * @author Aaron Zhu
 * @date 2022-11-2
 */
public class LongestCommonPrefix_14{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if ( strs==null || strs.length==0 ) {
                return "";
            } else if ( strs.length==1 ) {
                return strs[0];
            }

            int length = strs[0].length();
            int count = strs.length;

            for (int i=0; i<length; i++) {
                char ch = strs[0].charAt( i );
                for(int j=1; j<count; j++) {
                    if( i==strs[j].length() || strs[j].charAt(i) != ch ) {
                        return strs[0].substring(0,i);
                    }
                }
            }

            return strs[0];
        }
    }

}