package com.aaron.LeetCode;

import java.util.*;

//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−2³¹, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= x <= 2³¹ - 1 
// 
// Related Topics 数学 👍 3671 👎 0


/**
 * 7, 整数反转
 * @author Aaron Zhu
 * @date 2022-10-31
 */
public class ReverseInteger_7{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.reverse(-2147483648);
        solution.reverse(-333);
    }

    public static class Solution {
        public int reverse(int x) {
            int ans = 0;
            while (x!=0) {
                if( ans > Integer.MAX_VALUE/10 || ans < Integer.MIN_VALUE/10 ) {
                    return 0;
                }

                int digit = x % 10;
                x = x / 10;
                ans = 10 * ans + digit;
            }
            return ans;
        }
    }
}
