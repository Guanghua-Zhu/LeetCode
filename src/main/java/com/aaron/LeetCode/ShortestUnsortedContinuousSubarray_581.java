package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。 
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//
// 
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？ 
//
// Related Topics 栈 贪心 数组 双指针 排序 单调栈 👍 924 👎 0


/**
 * 581, 最短无序连续子数组
 * @author Aaron Zhu
 * @date 2022-9-24
 */
public class ShortestUnsortedContinuousSubarray_581{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int findUnsortedSubarray(int[] nums) {
            int size = nums.length;
            if(size<2) {
                return 0;
            }

            // 从左到右,寻找第一个元素, 使得该元素的右侧存在比它小的元素
            int leftBoardIndex = -1;
            int min = Integer.MAX_VALUE;
            for (int i=size-1; i>=0; i--) {
                if( nums[i] <= min ) {
                    min = nums[i];
                } else {
                    leftBoardIndex = i;
                }
            }

            // 从右到左,寻找第一个元素, 使得该元素的左侧存在比它大的元素
            int rightBoardIndex = -1;
            int max = Integer.MIN_VALUE;
            for (int i=0; i<size; i++) {
                if( nums[i] >=max ) {
                    max = nums[i];
                } else {
                    rightBoardIndex = i;
                }
            }

            if( leftBoardIndex==-1 || rightBoardIndex==-1 ) {
                return 0;
            } else {
                return rightBoardIndex - leftBoardIndex + 1;
            }
        }
    }

}
