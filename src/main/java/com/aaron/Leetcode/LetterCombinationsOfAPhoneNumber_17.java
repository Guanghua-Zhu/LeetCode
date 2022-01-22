package com.aaron.Leetcode;

import java.util.*;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1681 👎 0

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * LeetCode: 17, 电话号码的字母组合
 * @author Aaron Zhu
 * @date 2022-1-18
 */
public class LetterCombinationsOfAPhoneNumber_17{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 回溯法
     */
    public static class Solution {
        /**
         * 数字与字符的映射, key: 数字, value: 字母
         */
        private static  Map<Character, String> num2StringMap;

        static {
            num2StringMap = new HashMap<>();
            num2StringMap.put('2', "abc");
            num2StringMap.put('3', "def");
            num2StringMap.put('4', "ghi");
            num2StringMap.put('5', "jkl");
            num2StringMap.put('6', "mno");
            num2StringMap.put('7', "pqrs");
            num2StringMap.put('8', "tuv");
            num2StringMap.put('9', "wxyz");
        }

        /**
         * 最终结果: 字母组合列表
         */
        private List<String> result;

        /**
         * 状态变量：当前搜索结果
         */
        private StringBuilder sb;

        public List<String> letterCombinations(String digits) {
            // 判空
            if( digits==null || digits.length()==0 ) {
                return Collections.emptyList();
            }

            // 初始化全局变量
            init();
            // 从第0个数开始搜索
            search(digits, 0);
            // 直接返回最终搜索结果
            return result;
        }

        /**
         * 初始化全局变量
         */
        private void init() {
            result = new LinkedList<>();
            sb = new StringBuilder();
        }

        /**
         * 回溯法递归搜索
         * @param digits 待搜索数字
         * @param index 搜索第index个数字
         */
        private void search(String digits, int index) {
            // 数字全部搜索结束
            if( index == digits.length() ) {
                // 将当前搜索结果加入最终结果集
                result.add( sb.toString() );
                return;
            }

            // 获取第index个数字的可选择字符
            char num = digits.charAt(index);
            String choice = getChooseList(num);
            // 遍历可选择字符
            for(char ch : choice.toCharArray() ) {
                // 将当前字符加入搜索结果
                sb.append( ch );
                // 递归搜索下一个数字
                search(digits, index+1);
                // 将当前字符从搜索结果中移除, 即撤销所做出的选择, 以进行for循环的下一次遍历
                sb.deleteCharAt( sb.length()-1 );
            }
        }

        /**
         * 当前数字的可选择字符
         * @param ch
         * @return
         */
        private String getChooseList(char ch) {
            return num2StringMap.get(ch);
        }
    }

}

