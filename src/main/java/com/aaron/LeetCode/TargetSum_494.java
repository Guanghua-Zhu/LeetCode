package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 1269 👎 0


/**
 * 494, 目标和
 * @author Aaron Zhu
 * @date 2022-6-26
 */
public class TargetSum_494{
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findTargetSumWays(new int[]{1,1,1,1,1}, 3);
    }

    /**
     * DP
     */
    public static class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums) {
                sum += Math.abs(num);
            }
            if( Math.abs(target) > sum ) {
                return 0;
            }

            int[][] dp = new int[ nums.length+1 ][ 2*sum+1 ];
            dp[0][sum] = 1;
            for (int i=0; i<nums.length; i++) {
                for(int j=-sum; j<=sum; j++) {
                    int num = nums[i];
                    int temp = 0;
                    if( j+num+sum < 2*sum+1 ) {
                        temp += dp[i][ j+num+sum ];
                    }
                    if( j-num+sum >= 0 ) {
                        temp += dp[i][ j-num+sum ];
                    }
                    dp[i+1][j+sum] = temp;
                }
            }

            return dp[nums.length][ target+sum ];
        }
    }

    public static class Solution2 {

        public int findTargetSumWays(int[] nums, int target) {
            int count = 0;
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if( sum==target ) {
                count++;
            }

            int length = nums.length;
            for (int i=1; i< (1<<length); i++) {
                int lastI = i-1;
                int xor = lastI ^ i;

                int j = 0;
                while ( xor!=0 ) {
                    if( (xor & 1) == 1) {
                        if( (lastI & 1) == 1 ) {
                            sum += 2 * nums[j];
                        } else {
                            sum -= 2 * nums[j];
                        }

                    }
                    xor >>= 1;
                    lastI >>= 1;
                    j++;
                }

                if( sum == target ) {
                    count++;
                }

            }

            return count;
        }

    }

    /**
     * 回溯
     */
    public static  class Solution1 {
        /**
         * 结果变量
         */
        private int count;

        /**
         * 状态变量
         */
        private boolean[] sign;

        public int findTargetSumWays(int[] nums, int target) {
            init(nums);
            search(nums, 0, target);
            return count;
        }

        private void init(int[] nums) {
            count = 0;
            sign = new boolean[ nums.length ];
        }

        private void search(int[] nums, int index, int remain) {
            if( index==nums.length ) {
                if( remain==0 ) {
                    count++;
                }
                return;
            }

            search(nums, index+1, remain-nums[index]);
            search(nums, index+1, remain+nums[index]);
        }

    }

}