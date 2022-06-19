package com.aaron.LeetCode;

import java.util.*;

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
// 
//
// 示例 2: 
//
// 
//输入: nums = [0]
//输出: [0] 
//
// 
//
// 提示: 
// 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能尽量减少完成的操作次数吗？ 
// Related Topics 数组 双指针 👍 1623 👎 0


/**
 * 283, 移动零
 * @author Aaron Zhu
 * @date 2022-6-17
 */
public class MoveZeroes_283{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public void moveZeroes(int[] nums) {
            int p1 = 0;
            int p2 = 0;

            while ( p2<nums.length ) {
                if( nums[p2]!=0 ) {
                    swap(nums, p1, p2);
                    p1++;
                }
                p2++;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static class Solution1 {
        public void moveZeroes(int[] nums) {
            int p1 = 0;
            int p2 = nums.length-1;

            while (p1 < p2) {
                if( nums[p1]!=0 ) {
                    p1++;
                } else if( nums[p2] == 0 ) {
                    p2--;
                } else {
                    shift(nums, p1, p2);
                    p2--;
                }
            }
        }

        private void shift(int[] nums, int i, int j) {
            int temp = nums[i];
            for (int k=i+1; k<=j; k++) {
                nums[k-1] = nums[k];
            }
            nums[j] = temp;
        }
    }

}

