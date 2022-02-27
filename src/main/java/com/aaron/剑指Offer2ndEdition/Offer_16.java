package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
//
// 
//
// 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/ 
// Related Topics 递归 数学 
// 👍 257 👎 0


/**
 * 剑指 Offer 16, 数值的整数次方
 * @author Aaron Zhu
 * @date 2022-2-27
 */
public class Offer_16 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public double myPow(double x, int n) {
            long power = n<0 ? -1*(long)n : n;

            double res = 1;
            double temp = x;
            while ( power>0 ) {
                long lastBit = power & 1;
                if( lastBit == 1 ) {
                    res = res * temp;
                }
                temp = temp * temp;
                power = power >> 1;
            }

            if( n<0 ) {
                res = 1/res;
            }
            return res;
        }
    }
}


