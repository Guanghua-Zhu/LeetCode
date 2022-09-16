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
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> res = solution.findAnagrams(s,p);
        System.out.println("gg");
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 滑动窗口 3
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        int sLength = s.length();
        int pLength = p.length();
        if( pLength > sLength ) {
            return res;
        }

        int[] count = new int[26];
        char[] pChars = p.toCharArray();
        char[] sChars = s.toCharArray();
        for (int i=0; i<pLength; i++) {
            count[ sChars[i]-'a' ]++;
            count[ pChars[i]-'a' ]--;
        }

        int diff = 0;
        for (int i=0; i<26; i++) {
            if( count[i]!=0 ) {
                diff++;
            }
        }
        if( diff!=0 ) {
            res.add(0);
        }

        for (int i=0; i<=sLength-pLength; i++) {
            // 移除索引为i的元素
            int index1 = sChars[i]-'a';
            count[index1]--;
            if( count[index1] == 0 ) {
                diff--;
            } else if( count[index1]==-1 ) {
                diff++;
            }

            // 添加索引为i的元素
            int index2 = sChars[i+pLength] - 'a';
            count[index2]++;
            if( count[index2]==0 ) {
                diff--;
            } else if( count[index2]==1 ){
                diff++;
            }

            if( diff==0 ) {
                res.add( i+1 );
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/**
 * 滑动窗口 2
 */
class Solution2 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        int sLength = s.length();
        int pLength = p.length();
        if( pLength > sLength ) {
            return res;
        }

        int[] pCount = new int[26];
        char[] pChars = p.toCharArray();
        for (char ch : pChars) {
            pCount[ ch-'a' ]++;
        }

        int[] sCount = new int[26];
        char[] sChars = s.toCharArray();

        for(int i=0; i<=sLength-pLength; i++) {
            if( i==0 ) {
                for (int j=0; j<pLength; j++) {
                    sCount[ sChars[j]-'a' ]++;
                }
            } else {
                sCount[ sChars[i-1]-'a' ]--;
                sCount[ sChars[i+pLength-1]-'a' ]++;
            }

            if( Arrays.equals( sCount, pCount ) ) {
                res.add( i );
            }
        }

        return res;
    }
}

/**
 * 滑动窗口1
 */
class Solution1 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        int sLength = s.length();
        int pLength = p.length();
        if( pLength > sLength ) {
            return res;
        }

        Map<Character, Integer> pMap = new HashMap<>();
        for(char ch : p.toCharArray()) {
            pMap.put(ch, pMap.getOrDefault(ch, 0)+1);
        }
        Map<Character, Integer> tempMap = new HashMap<>();

        char[] sChars = s.toCharArray();
        List<Character> subStrList = new LinkedList<>();

        for(int i=0; i<=sLength-pLength; i++) {
            if( i==0 ) {
                for (int j=0; j<pLength; j++) {
                    subStrList.add( sChars[j] );
                }
                for (Character tempCh : subStrList) {
                    tempMap.put(tempCh, tempMap.getOrDefault(tempCh, 0)+1 );
                }
            } else {
                Character removeCh = subStrList.remove(0);
                Integer count = tempMap.get(removeCh);
                if( count==1 ) {
                    tempMap.remove( removeCh );
                } else {
                    tempMap.put( removeCh, count-1 );
                }

                Character addCh = sChars[i+pLength-1];
                tempMap.put(addCh, tempMap.getOrDefault(addCh, 0)+1 );
                subStrList.add( addCh );
            }

            boolean flag = true;
            for (Map.Entry<Character, Integer> entry : pMap.entrySet()) {
                Integer pCount = tempMap.getOrDefault(entry.getKey(), 0);
                if( !pCount.equals( entry.getValue() ) ) {
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
