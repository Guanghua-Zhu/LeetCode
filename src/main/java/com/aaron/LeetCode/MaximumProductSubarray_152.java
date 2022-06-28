package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 测试用例的答案是一个 32-位 整数。 
//
// 子数组 是数组的连续子序列。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -10 <= nums[i] <= 10 
// nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数 
// 
// Related Topics 数组 动态规划 👍 1704 👎 0


/**
 * 152, 乘积最大子数组
 * @author Aaron Zhu
 * @date 2022-6-28
 */
public class MaximumProductSubarray_152{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP
     */
    public static class Solution {
        public int maxProduct(int[] nums) {
            int size = nums.length;
            int res = nums[0];
            int maxValue = nums[0];
            int minValue = nums[0];

            for (int i=1; i<size; i++) {
                int lastMax = maxValue;
                int lastMin = minValue;
                maxValue = maxBy3(lastMax*nums[i], lastMin*nums[i], nums[i]);
                minValue = minBy3(lastMax*nums[i], lastMin*nums[i], nums[i]);
                res = Math.max(res, maxValue);
            }
            return res;
        }

        private int maxBy3(int a, int b, int c) {
            int temp = Math.max(a,b);
            int res=  Math.max(temp, c);
            return res;
        }

        private int minBy3(int a, int b, int c) {
            int temp = Math.min(a,b);
            int res=  Math.min(temp, c);
            return res;
        }
    }

    /**
     * DP
     */
    public static class Solution1 {
        public int maxProduct(int[] nums) {
            long res = Long.MIN_VALUE;
            int size = nums.length;
            long[] dp = new long[size];

            for (int i=0; i<size; i++) {
                for (int j=0; j<=i; j++) {
                    if( i==j ) {
                        dp[j] = nums[j];
                    } else {
                        dp[j] *= nums[i];
                    }

                    if( dp[j] > res ) {
                        res= dp[j];
                    }
                }
            }

            return (int)res;
        }
    }

}

