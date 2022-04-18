package com.aaron.LeetCode;

import java.util.*;

//给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。 
//
// 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。 
//
// 
//
// 示例 1: 
//
// 
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// 示例 2: 
//
// 
//输入: strs = [""]
//输出: [[""]]
// 
//
// 示例 3: 
//
// 
//输入: strs = ["a"]
//输出: [["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 104 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 排序 
// 👍 1102 👎 0


/**
 * 49, 字母异位词分组
 * @author Aaron Zhu
 * @date 2022-4-18
 */
public class GroupAnagrams_49{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        solution.groupAnagrams( strs );
        System.out.println("gg");
    }

    public static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for(String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String tempStr = new String(chars);

                map.computeIfAbsent(tempStr, k->new LinkedList())
                    .add( str );
            }

            List<List<String>> list = new LinkedList<>();
            list.addAll( map.values() );
            return list;
        }
    }
}


