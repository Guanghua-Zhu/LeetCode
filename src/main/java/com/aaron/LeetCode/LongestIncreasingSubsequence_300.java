package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。 
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 👍 2617 👎 0


/**
 * 300, 最长递增子序列
 * @author Aaron Zhu
 * @date 2022-7-11
 */
public class LongestIncreasingSubsequence_300{
    
    public static void main(String[] args) {

        Solution solution = new Solution();

    }

    public static class Solution {
        public int lengthOfLIS(int[] nums) {
            int res = 0;
            int size = nums.length;

            int[] lengths = new int[ size ];
            for (int i=0 ; i<size; i++) {
                int cur = nums[i];
                int tempMax = 1;
                for (int j=0; j<i; j++) {
                    if( nums[j] < cur ) {
                        tempMax = Math.max(tempMax, lengths[j]+1 );
                    }
                }
                lengths[i] = tempMax;
                res = Math.max(res, lengths[i]);
            }

            return res;
        }
    }

}

