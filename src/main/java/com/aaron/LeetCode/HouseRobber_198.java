package com.aaron.LeetCode;

import java.util.*;

//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 数组 动态规划 👍 2179 👎 0


/**
 * 198, 打家劫舍
 * @author Aaron Zhu
 * @date 2022-6-29
 */
public class HouseRobber_198 {
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int rob(int[] nums) {
            int size = nums.length;
            if( size==1 ) {
                return nums[0];
            }

            // 前i个房子的最大收益
            int[] dp = new int[size];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i=2; i<size; i++) {
                dp[i] = Math.max(dp[i-1], nums[i]+dp[i-2]);
            }
            return dp[size-1];
        }
    }

    public static class Solution1 {
        public int rob(int[] nums) {
            int size = nums.length;
            if( size==1 ) {
                return nums[0];
            }

            // 偷到第i个房子时最大收益
            int[] dp = new int[size];
            dp[0] = nums[0];
            dp[1] = nums[1];
            int lastMax = dp[0];
            int res = Math.max(dp[0], dp[1]);

            for (int i=2; i<size; i++) {
                dp[i] = nums[i] + lastMax;
                lastMax = Math.max(lastMax, dp[i-1]);
                res = Math.max(res, dp[i]);
            }

            return res;
        }
    }

}