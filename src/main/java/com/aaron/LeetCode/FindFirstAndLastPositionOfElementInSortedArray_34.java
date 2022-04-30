package com.aaron.LeetCode;

//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1602 👎 0


/**
 * 34, 在排序数组中查找元素的第一个和最后一个位置
 * @author Aaron Zhu
 * @date 2022-4-10
 */
public class FindFirstAndLastPositionOfElementInSortedArray_34{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 二分查找
     */
    public static class Solution {
        public int[] searchRange(int[] nums, int target) {
            if( nums==null || nums.length==0 ) {
                return new int[]{-1,-1};
            } else if( nums.length==1 ) {
                if( nums[0]==target ) {
                    return new int[]{0,0};
                } else {
                    return new int[]{-1,-1};
                }
            }

            int firstIndex = binarySearch(nums, target, 1);
            if( firstIndex == -1 ) {
                return new int[]{-1,-1};
            }
            int lastIndex = binarySearch(nums, target, 2);
            return new int[] {firstIndex, lastIndex};
        }

        private int binarySearch(int[] nums, int target, int type) {
            int resIndex = -1;

            int left = 0;
            int right = nums.length-1;
            while (left<=right) {
                int mid = left + (right-left)/2;
                if( target == nums[mid] ) {
                    // 搜索第一个索引
                    if (type == 1) {
                        // 搜索左半部分
                        right = mid - 1;
                    } else if (type == 2) { // 搜索最后一个索引
                        // 搜索右半部分
                        left = mid + 1;
                    }
                } else if ( target > nums[mid] ) {
                    // 搜索右半部分
                    left = mid + 1;
                } else if ( target < nums[mid] ) {
                    // 搜索左半部分
                    right = mid - 1;
                }
            }

            // 搜索第一个索引
            if ( type==1  && left<nums.length && nums[left]==target ) {
                resIndex = left;
            } else if ( type==2 && right>=0 && nums[right]==target ) { // 搜索最后一个索引
                resIndex = right;
            }

            return  resIndex;
        }
    }

}