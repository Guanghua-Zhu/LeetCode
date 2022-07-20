package com.aaron.LeetCode;

import java.util.*;

//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。 
//
// 返回所有可能的结果。答案可以按 任意顺序 返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()())()"
//输出：["(())()","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：s = "(a)())()"
//输出：["(a())()","(a)()()"]
// 
//
// 示例 3： 
//
// 
//输入：s = ")("
//输出：[""]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 25 
// s 由小写英文字母以及括号 '(' 和 ')' 组成 
// s 中至多含 20 个括号 
// 
// Related Topics 广度优先搜索 字符串 回溯 👍 740 👎 0


/**
 * 301, 删除无效的括号
 * @author Aaron Zhu
 * @date 2022-7-14
 */
public class RemoveInvalidParentheses_301{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private Set<String> res;

        private boolean[] used;

        private char[] chars;

        private int currentMaxLength;

        private Map<String,Boolean> bracketMap;

        public List<String> removeInvalidParentheses(String s) {
            init(s);
            search(0,0);
            return new ArrayList<>( res );
        }

        private void init(String s) {
            res = new HashSet<>();
            used = new boolean[ s.length() ];
            chars = s.toCharArray();
            currentMaxLength = 0;
            bracketMap = new HashMap<>();
        }

        private void search(int index, int usedLength) {
            if( index == chars.length ) {
                if( usedLength<currentMaxLength ) {
                    return;
                }

                String str = isValidStr();
                if( str==null ) {

                } else if ( str.length()>currentMaxLength ) {
                    res.clear();
                    res.add( str );
                    currentMaxLength = str.length();
                } else if ( str.length() == currentMaxLength ) {
                    res.add( str );
                }

                return;
            }

            // 括号, 可以不选择
            if( chars[index] == '(' || chars[index] == ')' ) {
                used[index] = false;
                search( index+1, usedLength);
            }

            // 无论括号、字符都可以选择
            used[index] = true;
            search(index+1, usedLength+1);
        }

        /**
         * 判断字符串中的括号是否有效
         * @return
         */
        private String isValidStr() {
            StringBuffer sb1 = new StringBuffer();    // 包含括号、字符
            StringBuffer sb2 = new StringBuffer();    // 包含括号
            for (int i=0; i<used.length; i++) {
                if( used[i] ) {
                    char ch = chars[i];
                    sb1.append( ch );
                    if( ch=='(' || ch==')' ) {
                        sb2.append( ch );
                    }
                }
            }
            String str1 = sb1.toString();
            String str2 = sb2.toString();

            if( bracketMap.containsKey(str2) ) {
                // 括号有效
                if( bracketMap.get(str2) ) {
                    return str1;
                } else { // 括号无效
                    return null;
                }
            }

            boolean res = isValidBracket(str2);
            bracketMap.put(str2, res);

            if( res ) {
                return str1;
            } else {
                return null;
            }
        }

        /**
         * 判断括号是否有效
         * @param str
         * @return
         */
        private boolean isValidBracket(String str) {
            char[] chs = str.toCharArray();
            int num = 0;
            for (char ch : chs) {
                if( ch=='(' ) {
                    num++;
                } else if( ch==')' ) {
                    num--;
                }
                if( num<0 ) {
                    return false;
                }
            }

            if( num==0 ) {
                return true;
            } else {
                return false;
            }
        }

    }

}