package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。 
//
// 示例 1: 
//
// 
//输入：s = "abaccdeff"
//输出：'b'
// 
//
// 示例 2: 
//
// 
//输入：s = "" 
//输出：' '
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 队列 哈希表 字符串 计数 
// 👍 174 👎 0


/**
 * 剑指 Offer 50, 第一个只出现一次的字符
 * @author Aaron Zhu
 * @date 2022-2-9
 */
public class Offer_50 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
    public char firstUniqChar(String s) {
        char res = ' ';
        if( s==null || s.length()==0 ) {
            return res;
        }

        LinkedHashMap<Character, Integer> count = new LinkedHashMap<>();
        for (Character character : s.toCharArray() ) {
            count.compute(character, (k,v)->{
                if( v==null ) {
                    v=0;
                }
                v++;
                return v;
            });
        }

        for (Map.Entry<Character, Integer> entry : count.entrySet() ) {
            if( entry.getValue() == 1 ) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }
}
}

