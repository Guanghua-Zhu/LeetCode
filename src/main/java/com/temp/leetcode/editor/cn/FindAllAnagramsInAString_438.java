package com.temp.leetcode.editor.cn;

import java.util.*;

//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 989 👎 0


/**
 * 438, 找到字符串中所有字母异位词
 * @author Aaron Zhu
 * @date 2022-8-31
 */
public class FindAllAnagramsInAString_438{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        int sLength = s.length();
        int pLength = p.length();
        if( pLength > sLength ) {
            return res;
        }

        Set<Character> pSet = new HashSet<>();
        for(char ch : p.toCharArray()) {
            pSet.add( ch );
        }

        char[] sChars = s.toCharArray();
        List<Character> subStrList = new LinkedList<>();
        for(int i=0; i<=sLength-pLength; i++) {
            if( i==0 ) {
                for (int j=0; j<pLength; j++) {
                    subStrList.add( sChars[j] );
                }
            } else {
                subStrList.remove(0);
                subStrList.add( sChars[i+pLength-1] );
            }

            Set<Character> tempSet = new HashSet<>( subStrList );
            boolean flag = true;
            for (Character tempCh : tempSet) {
                if( !pSet.contains(tempCh) ) {
                    flag = false;
                    break;
                }
            }
            if( flag ) {
                res.add( i );
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
