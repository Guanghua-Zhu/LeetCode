package com.aaron.LeetCode;

import java.util.*;

//给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足： 
//
// 
// 0 <= i, j, k, l < n 
// nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
//输出：2
//解释：
//两个元组如下：
//1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1)
// + 2 = 0
//2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1)
// + 0 = 0
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// n == nums1.length 
// n == nums2.length 
// n == nums3.length 
// n == nums4.length 
// 1 <= n <= 200 
// -2²⁸ <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2²⁸ 
// 
//
// Related Topics 数组 哈希表 👍 714 👎 0


/**
 * 454, 四数相加 II
 * @author Aaron Zhu
 * @date 2022-12-24
 */
public class FourSumIi_454{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            int res = 0;
            Map<Long, Integer> subSumCountMap = new HashMap<>();
            int size = nums1.length;
            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {
                    long sum34 = (long)nums3[i] + (long)nums4[j];
                    subSumCountMap.put( sum34, subSumCountMap.getOrDefault(sum34,0)+1 );
                }
            }

            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {
                    long sum12 = (long)nums1[i] + (long)nums2[j];
                    res += subSumCountMap.getOrDefault( -sum12, 0 );
                }
            }

            return res;
        }
    }
}