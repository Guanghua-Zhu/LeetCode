package com.temp.leetcode.editor.cn;

import java.util.*;

//请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。 
//
// 函数 myAtoi(string s) 的算法如下： 
//
// 
// 读入字符串并丢弃无用的前导空格 
// 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。 
// 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。 
// 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 
//2 开始）。 
// 如果整数数超过 32 位有符号整数范围 [−2³¹, 231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2³¹ 的整数应该被固
//定为 −2³¹ ，大于 231 − 1 的整数应该被固定为 231 − 1 。 
// 返回整数作为最终结果。 
// 
//
// 注意： 
//
// 
// 本题中的空白字符只包括空格字符 ' ' 。 
// 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "42"
//输出：42
//解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
//第 1 步："42"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："42"（读入 "42"）
//           ^
//解析得到整数 42 。
//由于 "42" 在范围 [-2³¹, 2³¹ - 1] 内，最终结果为 42 。 
//
// 示例 2： 
//
// 
//输入：s = "   -42"
//输出：-42
//解释：
//第 1 步："   -42"（读入前导空格，但忽视掉）
//            ^
//第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
//             ^
//第 3 步："   -42"（读入 "42"）
//               ^
//解析得到整数 -42 。
//由于 "-42" 在范围 [-2³¹, 2³¹ - 1] 内，最终结果为 -42 。
// 
//
// 示例 3： 
//
// 
//输入：s = "4193 with words"
//输出：4193
//解释：
//第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
//         ^
//第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
//         ^
//第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
//             ^
//解析得到整数 4193 。
//由于 "4193" 在范围 [-2³¹, 2³¹ - 1] 内，最终结果为 4193 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 200 
// s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成 
// 
// Related Topics 字符串 👍 1564 👎 0


/**
 * 8, 字符串转换整数 (atoi)
 * @author Aaron Zhu
 * @date 2022-11-21
 */
public class StringToIntegerAtoi_8{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "-42";
        solution.myAtoi(s);
        System.out.println("gg");
    }

    public static class Solution {
        public int myAtoi(String s) {
            if( isBlank(s) ) {
                return 0;
            }

            // 处理前导空格
            s = s.trim();
            if( isBlank(s) ) {
                return 0;
            }

            // 处理符号位
            int factor = 1;    // 系数
            char[] chars = s.toCharArray();
            if ( chars[0] == '-' ) {
                factor = -1;
                s = s.substring(1);
            } else if( chars[0] == '+' ) {
                factor = 1;
                s = s.substring(1);
            }

            // 处理前导零
            int zeroEndIndex = 0;
            for( ; zeroEndIndex<s.length(); zeroEndIndex++) {
                if( s.charAt(zeroEndIndex) != '0' ) {
                    break;
                }
            }
            s = s.substring(zeroEndIndex);
            if( isBlank(s) ) {
                return 0;
            }

            chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            sb.append(0);
            int maxLength = String.valueOf(Integer.MAX_VALUE).length() + 2;
            for (int i=0; i<chars.length; i++) {
                char ch = chars[i];
                if( ch>='0' && ch<='9' ) {
                    sb.append(ch);
                } else {
                    break;
                }

                if( sb.length() > maxLength ) {
                    break;
                }
            }

            long num1 = Long.valueOf(sb.toString(), 10) * factor;
            int res = 0;
            if( num1 > Integer.MAX_VALUE ) {
                res = Integer.MAX_VALUE;
            } else if( num1 < Integer.MIN_VALUE ) {
                res = Integer.MIN_VALUE;
            } else {
                res = (int) num1;
            }

            return res;
        }

        private boolean isBlank(String s) {
            if( s==null || s.length()==0 ) {
                return true;
            }
            return false;
        }
    }

}