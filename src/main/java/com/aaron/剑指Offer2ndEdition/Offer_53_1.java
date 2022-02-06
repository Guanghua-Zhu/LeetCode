package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//统计一个数字在排序数组中出现的次数。 
//
// 示例 1:
// 输入: nums = [5,7,7,8,8,10], target = 8
// 输出: 2
//
// 示例 2: 
// 输入: nums = [5,7,7,8,8,10], target = 6
// 输出: 0
//
// 提示： 
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// Related Topics 数组 二分查找 
// 👍 255 👎 0


/**
 * 剑指 Offer 53-I, 在排序数组中查找数字I
 * @author Aaron Zhu
 * @date 2022-2-5
 */
public class Offer_53_1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 二分法
     */
    public static class Solution {
        public int search(int[] nums, int target) {
            if(nums==null || nums.length==0) {
                return 0;
            }

            int leftIndex = findLeftIndex(nums, target);
            if( leftIndex == -1) {
                return 0;
            }

            int rightIndex = findRightIndex(nums, target);

            int count = rightIndex - leftIndex + 1;
            return count;
        }

        /**
         * 寻找元素重复出现时的第一个位置(即，左下标)
         * @param nums 存在重复元素的有序数组
         * @param target 目标元素值
         * @return
         */
        private int findLeftIndex(int[] nums, int target) {
            int start = 0;
            int end = nums.length-1;
            while (start<=end) {
                int mid = start + (end-start)/2;
                if ( target<nums[mid] ) {
                    // 目标元素在左侧区间, 即 [start, mid-1]
                    // 故更新end值
                    end = mid-1;
                } else if ( target>nums[mid] ) {
                    // 目标元素在右侧区间, 即 [mid+1, end]
                    // 故更新start值
                    start = mid+1;
                } else if( target==nums[mid] ) {
                    // 在找到目标值后继续寻找左边界, 需要继续搜索左区间
                    // 故更新end值
                    end = mid-1;
                }
            }

            // 检验: 防止查找元素不存在
            if ( start>=0 && start<nums.length && nums[start] == target ) {
                return start;
            } else {
                // 元素不存在
                return -1;
            }
        }

        /**
         * 寻找元素重复出现时的最后一个位置(即，右下标)
         * @param nums 存在重复元素的有序数组
         * @param target 目标元素值
         * @return
         */
        private int findRightIndex(int[] nums, int target) {
            int start = 0;
            int end = nums.length-1;
            while (start<=end) {
                int mid = start + (end-start)/2;
                if ( target<nums[mid] ) {
                    // 目标元素在左侧区间, 即 [start, mid-1]
                    // 故更新end值
                    end = mid-1;
                } else if ( target>nums[mid] ) {
                    // 目标元素在右侧区间, 即 [mid+1, end]
                    // 故更新start值
                    start = mid+1;
                } else if( target==nums[mid] ) {
                    // 在找到目标值后继续寻找右边界, 需要继续搜索右区间
                    // 故更新start值
                    start = mid+1;
                }
            }

            // 检验: 防止查找元素不存在
            if ( end>=0 && end<nums.length && nums[end] == target ) {
                return end;
            } else {
                // 元素不存在
                return -1;
            }
        }

    }

    /**
     * 遍历统计
     */
    public static class Solution1 {
        public int search(int[] nums, int target) {
            int count = 0;
            for (int num : nums) {
                if( num==target ) {
                    count++;
                } else if( num > target ) {
                    break;
                }
            }
            return count;
        }
    }
}
