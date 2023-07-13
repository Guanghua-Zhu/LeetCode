package com.aaron.LeetCode;

import java.util.*;

//给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。 
//
// 如果小数部分为循环小数，则将循环的部分括在括号内。 
//
// 如果存在多个答案，只需返回 任意一个 。 
//
// 对于所有给定的输入，保证 答案字符串的长度小于 10⁴ 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numerator = 1, denominator = 2
//输出："0.5"
// 
//
// 示例 2： 
//
// 
//输入：numerator = 2, denominator = 1
//输出："2"
// 
//
// 示例 3： 
//
// 
//输入：numerator = 4, denominator = 333
//输出："0.(012)"
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= numerator, denominator <= 2³¹ - 1 
// denominator != 0 
// 
//
// Related Topics 哈希表 数学 字符串 👍 462 👎 0


/**
 * 166, 分数到小数
 * @author Aaron Zhu
 * @date 2023-7-13
 */
public class FractionToRecurringDecimal_166{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.fractionToDecimal(-1,-2147483648    );
        String s = "1234.98249824982424242424";
//        String s = "1234398233333333333";
        String[] strs = s.split("\\.");
        System.out.println("gg");

    }

    public static class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder sb = new StringBuilder();
            long num1 = numerator;
            long num2 = denominator;
            if( num2==0 ) {
                return "0";
            } else if( num1%num2==0 ) {
                return String.valueOf(num1/num2);
            }

            if( num1*num2<0 ) {
                sb.append("-");
            }

            // 化为最简分数
            num1 = Math.abs(num1);
            num2 = Math.abs(num2);
            long gcd = gcd(num1, num2);
            num1 /= gcd;
            num2 /= gcd;

            sb.append( num1/num2 ); // 结果的整数部分
            sb.append( "." );

            Map<Long, Integer> map = new HashMap<>();
            // 计算余数
            num1 = num1 % num2;
            while ( num1!=0 ) {
                // 记录当前余数的位置
                map.put(num1, sb.length());
                // 余数补0
                num1 *= 10;
                // 记录商
                sb.append( num1/num2 );
                // 计算新余数
                num1 = num1 % num2;

                // 判断新余数是否曾经出现过，如果重复出现了，表示马上要开始重复循环了
                if( map.containsKey(num1) ) {
                    // 循环起始的位置索引
                    int cycleIndex = map.get(num1);
                    String res = String.format( "%s(%s)", sb.substring(0,cycleIndex), sb.substring(cycleIndex) );
                    return res;
                }
            }

            return sb.toString();
        }

        /**
         * 计算最大公约数
         * @param a
         * @param b
         * @return
         */
        public long gcd(long a, long b) {
            if ( b==0)  {
                return a;
            }
            return gcd(b, a%b);
        }
    }

}