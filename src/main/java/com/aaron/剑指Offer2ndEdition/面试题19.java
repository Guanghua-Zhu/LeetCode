package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。 
// 
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
// 
// Related Topics 递归 字符串 动态规划 
// 👍 335 👎 0


/**
 * 面试题19, 正则表达式匹配
 * @author Aaron Zhu
 * @date 2022-2-13
 */
public class 面试题19 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean res = solution.isMatch("mississippi","mis*is*ip*.");
        System.out.println("gg");
    }


    /**
     * NFA 非确定有限状态自动机 + 回溯
     */
    public static class Solution {
        /**
         * NFA 非确定有限状态自动机
         */
        private List<Character> state;

        /**
         * 可重复的状态位置索引
         */
        private Set<Integer> repeatSet;

        /**
         * 可通过空转移进入的状态位置索引
         */
        private Set<Integer> epsilonSet;

        /**
         * 判断字符串是否匹配正则表达式
         * @param s 字符串
         * @param p 正则表达式
         * @return
         */
        public boolean isMatch(String s, String p) {
            if( s==null || s=="" || p==null || p=="" ) {
                return false;
            }

            state = new ArrayList<>();
            repeatSet = new HashSet<>();
            epsilonSet = new HashSet<>();

            // 构建NFA
            int index = -1;
            for(char ch : p.toCharArray()) {
                if( (ch>='a' && ch<='z') || ch=='.' ) {
                    state.add(ch);
                    index++;
                } else if( ch=='*' ) {
                    // * 前面的字符可以重复
                    repeatSet.add( index );
                    // 从 state[index-1]状态 到 state[index]状态 为 空转移
                    epsilonSet.add( index);
                }
            }

            boolean res = dfs(s.toCharArray(), -1, -1,0);
            return res;
        }

        private boolean dfs(char[] chars, int charsIndex, int stateIndex, int opsType) {
            // 字符串已经遍历完毕 且 NFA状态已经全部遍历完毕, 则说明正则匹配成功
            if( charsIndex > chars.length-1 && stateIndex > state.size()-1 ) {
                return true;
            }

            // 字符串已经遍历完毕 或 NFA状态已经全部遍历完毕, 则说明正则匹配失败
            if( charsIndex > chars.length-1 || stateIndex > state.size()-1 ) {
                return false;
            }

            // 通过 空转移 进入的
            if( opsType==1 ) {
                if( !epsilonSet.contains(stateIndex) ) {
                    // 当前状态不可通过空转移进入
                    return false;
                }
            } else if ( opsType==2 ) { // 通过 匹配下一个状态 进入的
                if( epsilonSet.contains(stateIndex) ) {
                    // 当前状态如果可以通过空转移进入，则就不应该消耗输入字符
                    return false;
                }
                if( chars[charsIndex]!=state.get(stateIndex) && state.get(stateIndex)!='.' ) {
                    // 当前状态与指定字符 不匹配
                    return false;
                }
            } else if( opsType==3 ) {   // 通过 重复匹配当前状态 进入的
                if( !repeatSet.contains(stateIndex) ) {
                    // 当前状态不可重复进入
                    return false;
                }
                if( chars[charsIndex]!=state.get(stateIndex) && state.get(stateIndex)!='.' ) {
                    // 当前状态与指定字符 不匹配
                    return false;
                }
            }

            return dfs(chars, charsIndex, stateIndex+1, 1)                 // 空转移
                || dfs(chars, charsIndex+1, stateIndex+1, 2)   // 匹配下一个状态
                || dfs(chars, charsIndex+1, stateIndex, 3);               // 重复匹配当前状态
        }
    }
}
