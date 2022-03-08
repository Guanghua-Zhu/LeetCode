package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。 
//
// 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：6 
//
// 
//
// 限制： 
//
// 
// 1 <= n < 2^31 
// 
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/ 
// Related Topics 递归 数学 动态规划 
// 👍 288 👎 0


/**
 * 剑指 Offer 43, 1～n 整数中 1 出现的次数
 * @author Aaron Zhu
 * @date 2022-3-7
 */
public class Offer_43 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countDigitOne(12);
        System.out.println("ggg");
    }

    public static class Solution {
        public int countDigitOne(int n) {
            int res = 0;

            int high = n / 10;
            int current = n % 10;
            int low = 0;
            int digit = 1;

            while (high!=0 || current !=0) {
                if ( current==0 ) {
                    res += digit * high;
                } else if (current==1) {
                    res += digit * high + 1 + low;
                } else {
                    res += digit * (high+1);
                }

                low = low + current * digit;
                current = high % 10;
                high = high / 10;
                digit = digit * 10;
            }

            return res;
        }
    }
}


