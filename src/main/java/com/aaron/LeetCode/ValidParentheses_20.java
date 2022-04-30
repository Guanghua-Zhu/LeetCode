package com.aaron.LeetCode;

import java.util.*;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 3128 👎 0


/**
 * 20, 有效的括号
 * @author Aaron Zhu
 * @date 2022-3-29
 */
public class ValidParentheses_20{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public boolean isValid(String s) {
            Deque<Character> stack = new LinkedList<>();
            char[] chars = s.toCharArray();
            for (char ch : chars) {
                if( isLeftBracket(ch) ) {
                    stack.addLast(ch);
                } else {
                    Character leftBracket = stack.pollLast();
                    if(  leftBracket==null || !isMatch(leftBracket, ch) ) {
                        return false;
                    }
                }
            }

            if( stack.isEmpty() ) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isLeftBracket(char ch) {
            return ch=='(' || ch=='[' || ch=='{';
        }

        private boolean isMatch(char left, char right) {
            if( (left=='(' && right==')')
                || (left=='[' && right==']')
                || (left=='{' && right=='}') ){
                return true;
            }
            return false;
        }
    }

}

