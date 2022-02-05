package com.temp.leetcode.editor.cn;

import java.util.*;

// 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s
// 如果有多对数字的和等于s，则输出任意一对即可
//
// 示例 1： 
// 输入：nums = [2,7,11,15], target = 9
// 输出：[2,7] 或者 [7,2]
//
// 示例 2： 
// 输入：nums = [10,26,30,31,47,60], target = 40
// 输出：[10,30] 或者 [30,10]
// 
// 限制：
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^6 
// Related Topics 数组 双指针 二分查找
// 👍 158 👎 0


/**
 * 剑指 Offer 57, 和为s的两个数字
 * @author Aaron Zhu
 * @date 2022-2-5
 */
public class Offer_57 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 双指针
     */
    public static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] res = new int[2];
            // 左右指针
            int l = 0;
            int r = nums.length-1;
            while ( l<r ) {
                int sum = nums[l] + nums[r];
                if ( sum==target ) {
                    res = new int[]{nums[l], nums[r]};
                    break;
                } else if( sum>target ) {
                    r--;
                } else if( sum<target ) {
                    l++;
                }
            }
            return res;
        }
    }

    /**
     * 二分查找
     */
    public static class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            int[] res = null;
            for (int i=0; i<nums.length; i++) {
                int num2Index = binarySearch(nums, target-nums[i]);
                if( num2Index != -1 && i!=num2Index) {
                    res = new int[]{nums[i], target-nums[i]};
                    break;
                }
            }

            return res;
        }

        private int binarySearch(int[] nums, int target) {
            int index = -1;
            int start = 0;
            int end = nums.length-1;
            while ( start>end ) {
                int mid = start + (end-start)/2;
                if( target == nums[mid]) {
                    index = mid;
                    break;
                } else if( target<nums[mid] ) {
                    end = mid -1;
                } else if( target>nums[mid] ) {
                    start = mid+1;
                }
            }
            return index;
        }
    }

    /**
     * 哈希表
     */
    public static class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            int[] res = null;
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                if( set.contains( target-num ) ) {
                    res = new int[]{num, target-num};
                    break;
                }
                set.add(num);
            }
            return res;
        }
    }
}
