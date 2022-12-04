package com.aaron.LeetCode;

import java.util.*;

//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。 
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 4
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
//
// Related Topics 数学 二分查找 👍 1211 👎 0


/**
 * 69, x 的平方根 
 * @author Aaron Zhu
 * @date 2022-12-4
 */
public class Sqrtx_69{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int mySqrt(int x) {
            if( x==0 ) {
                return 0;
            } else if( x==1 ) {
                return 1;
            }

            int left = 1;
            int right = x/2;
            while (left <= right) {
                int mid = left + (right-left)/2;
                int temp = x / mid;
                if( temp == mid ) {
                    return mid;
                }
                else if( temp>mid ) {
                    left = mid+1;
                } else if( temp<mid ) {
                    right = mid-1;
                }
            }

            return left-1;
        }
    }
}

