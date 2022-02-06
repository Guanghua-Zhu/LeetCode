package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内
// 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字
//
// 示例 1: 
// 输入: [0,1,3]
// 输出: 2
// 
// 示例 2:
// 输入: [0,1,2,3,4,5,6,7,9]
// 输出: 8
//
// 限制：
// 1 <= 数组长度 <= 10000
// Related Topics 位运算 数组 哈希表 数学 二分查找 
// 👍 216 👎 0

/**
 * 剑指 Offer 53-II, 0～n-1中缺失的数字
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class Offer_53_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 二分法
     */
    public static class Solution {
        public int missingNumber(int[] nums) {
            int start = 0;
            int end = nums.length-1;
            while ( start<=end ) {
                int mid = start + (end-start)/2;
                if( nums[mid] == mid ) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            }
            return start;
        }
    }

    /**
     * 遍历
     */
    public static class Solution1 {
        public int missingNumber(int[] nums) {
            int res = 0;
            for (int i=0; i<=nums.length; i++) {
                if( i<nums.length && nums[i]==i ) {
                    continue;
                }
                if( i+1<nums.length && nums[i+1]==i ) {
                    continue;
                }
                res = i;
                return res;
            }
            return res;
        }
    }
}

