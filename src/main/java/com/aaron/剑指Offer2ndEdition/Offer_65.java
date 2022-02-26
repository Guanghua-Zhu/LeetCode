package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。 
//
// 
//
// 示例: 
//
// 输入: a = 1, b = 1
//输出: 2 
//
// 
//
// 提示： 
//
// 
// a, b 均可能是负数或 0 
// 结果不会溢出 32 位整数 
// 
// Related Topics 位运算 数学 
// 👍 262 👎 0


/**
 * 剑指 Offer 65, 不用加减乘除做加法
 * @author Aaron Zhu
 * @date 2022-2-26
 */
public class Offer_65 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.add(1,-2);
        System.out.println("gg");
    }

    public static class Solution {
        public int add(int a, int b) {
            int carry;
            do {
                // 计算进位
                carry = (a & b) << 1;
                // 计算非进位的值
                a = a ^ b;
                b = carry;
            }while ( carry!=0 );

            return a;
        }
    }

    public static class Solution1 {
        public int add(int a, int b) {
            StringBuilder sb = new StringBuilder();
            // 进位标志
            boolean carry = false;
            for (int i=0; i<32; i++) {
                int aBit = a & 1;
                a >>= 1;
                int bBit = b & 1;
                b >>= 1;

                if (carry) {
                    int value = 1 - aBit ^ bBit;
                    sb.append(value);
                    carry = (aBit | bBit) == 1;
                } else {
                    int value = aBit ^ bBit;
                    sb.append(value);
                    carry = (aBit & bBit) == 1;
                }
            }

            int res = Integer.parseUnsignedInt(sb.reverse().toString(),2);
            return res;
        }
    }

}

