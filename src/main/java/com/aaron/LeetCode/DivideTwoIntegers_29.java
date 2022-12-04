package com.aaron.LeetCode;

import java.util.*;

//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2³¹, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 位运算 数学 👍 1019 👎 0


/**
 * 29, 两数相除
 * @author Aaron Zhu
 * @date 2022-11-30
 */
public class DivideTwoIntegers_29{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int divide(int dividend, int divisor) {
            if( dividend == 0 ) {
                return 0;
            } else if ( dividend==Integer.MIN_VALUE ) {
                if( divisor==1 ) {
                    return Integer.MIN_VALUE;
                } else if( divisor==-1 ) {
                    return Integer.MAX_VALUE;
                }
            } else if ( divisor==Integer.MIN_VALUE ) {
                if( dividend == Integer.MIN_VALUE ) {
                    return 1;
                } else {
                    return 0;
                }
            }

            boolean sign = false;
            if( (dividend>0 && divisor<0) || (dividend<0 && divisor>0) ) {
                sign = true;
            }

            // 全部转化为负数
            dividend = dividend < 0 ? dividend : -dividend;
            divisor = divisor < 0 ? divisor : -divisor;

            int res = 0;
            int limit = Integer.MIN_VALUE/2;
            while ( dividend <= divisor ) {
                int b = divisor;
                int tempRes = -1;
                while ( b>=limit && tempRes>=limit && dividend - b <=b ) {
                    b += b;
                    tempRes += tempRes;
                }

                dividend -= b;
                res += tempRes;
            }

            return sign ? res : -res;
        }
    }

}