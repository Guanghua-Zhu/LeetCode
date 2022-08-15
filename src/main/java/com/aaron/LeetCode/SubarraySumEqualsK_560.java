package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
// Related Topics 数组 哈希表 前缀和 👍 1621 👎 0


/**
 * 560, 和为 K 的子数组
 * @author Aaron Zhu
 * @date 2022-8-12
 */
public class SubarraySumEqualsK_560{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int subarraySum(int[] nums, int k) {
            int res = 0;
            int size = nums.length;

            for(int j=0; j<size; j++) {
                int sum = 0;
                for (int i=j; i>=0; i--) {
                    sum += nums[i];
                    if( sum==k ) {
                        res++;
                    }
                }
            }

            return res;
        }
    }

    public static class Solution1 {
        public int subarraySum(int[] nums, int k) {
            int res = 0;
            int size = nums.length;
            int sum = 0;
            Map<Integer, Integer> preSumByCount = new HashMap<>();
            preSumByCount.put( 0,1 );
            for (int i=0; i<size; i++) {
                sum += nums[i];
                res += preSumByCount.getOrDefault(sum-k, 0);
                preSumByCount.put(sum, preSumByCount.getOrDefault(sum,0)+1);
            }

            return res;
        }

    }
}
