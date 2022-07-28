package com.aaron.LeetCode;

import java.util.*;

//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 1053 👎 0


/**
 * 312, 戳气球
 * @author Aaron Zhu
 * @date 2022-7-25
 */
public class BurstBalloons_312{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int maxCoins(int[] nums) {
            int[] data = new int[ nums.length+2 ];
            int dataSize = data.length;
            for (int i=0; i<dataSize; i++) {
                if( i==0 || i==dataSize-1 ) {
                    data[i] = 1;
                } else {
                    data[i] = nums[i-1];
                }
            }

            int[][] dp = new int[dataSize][dataSize];
            for(int rangeLen=3; rangeLen<=dataSize; rangeLen++) {
                for (int rangeStart=0; rangeStart<=dataSize-rangeLen; rangeStart++) {
                    for (int lastIndex=rangeStart+1; lastIndex<rangeStart+rangeLen-1; lastIndex++) {
                        int left = dp[rangeStart][lastIndex];
                        int right = dp[lastIndex][rangeStart+rangeLen-1];
                        int temp = left + right + data[rangeStart] * data[rangeStart+rangeLen-1] * data[lastIndex];
                        dp[rangeStart][rangeStart+rangeLen-1] = Math.max( temp, dp[rangeStart][rangeStart+rangeLen-1] );
                    }
                }
            }

            return dp[0][dataSize-1];
        }
    }

}