package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入一个整型数组
// 数组中的一个或连续多个整数组成一个子数组
// 求所有子数组的和的最大值
// 要求时间复杂度为O(n)
//
// 示例1:
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
// 输出: 6
// 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6
// 
// 提示：
// 1 <= arr.length <= 10^5
// -100 <= arr[i] <= 100 
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
// Related Topics 数组 分治 动态规划
// 👍 449 👎 0


/**
 * 剑指 Offer 42, 连续子数组的最大和
 * @author Aaron Zhu
 * @date 2022-1-31
 */
public class Offer_42 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int maxSubArray(int[] nums) {
            if(nums==null && nums.length==0) {
                return 0;
            }

            int[] dp = new int[nums.length];
            int maxInDp = Integer.MIN_VALUE;

            for(int i=0; i<nums.length; i++) {
                if( i==0 ) {
                    dp[i] = nums[i];
                } else if ( dp[i-1]<0 ) {
                    dp[i] = nums[i];
                } else if( dp[i-1]>=0 ) {
                    dp[i] = dp[i-1] + nums[i];
                }

                maxInDp = Math.max(maxInDp, dp[i]);
            }

            return maxInDp;
        }
    }
}


