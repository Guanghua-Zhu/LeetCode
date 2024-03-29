package com.aaron.LeetCode;

import java.util.*;

//给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。 
//
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。 
//
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,4,2,2]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,1,3,4,2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁵ 
// nums.length == n + 1 
// 1 <= nums[i] <= n 
// nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次 
// 
//
// 
//
// 进阶： 
//
// 
// 如何证明 nums 中至少存在一个重复的数字? 
// 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？ 
// 
// Related Topics 位运算 数组 双指针 二分查找 👍 1775 👎 0

/**
 * 287, 寻找重复数
 * @author Aaron Zhu
 * @date 2022-6-5
 */
public class FindTheDuplicateNumber_287{
    public static void main(String[] args) {
    }

    /**
     * 二分查找
     */
    public static class Solution1 {
        public int findDuplicate(int[] nums) {
            int l = 0;
            int r = nums.length-1;
            int res = -1;
            while ( l<=r) {
                int mid = (l+r)/2;
                int count = 0;
                for (int num : nums) {
                    if( num<=mid ) {
                        count++;
                    }
                }

                if( count<=mid ) {
                    l = mid+1;
                } else {
                    res = mid;
                    r = mid-1;
                }
            }
            return res;
        }
    }

    /**
     * 快慢指针
     */
    public static class Solution2 {
        public int findDuplicate(int[] nums) {
            // 必须从一个肯定不在环中的元素开始遍历
            // 否则Floyd算法无法找到环的入口
            // 根据题意可知0不在nums中, 所以肯定不会在环中
            int slow = 0;
            int fast = 0;
            while (true) {
                slow = nums[slow];
                fast = nums[ nums[fast] ];
                if( slow==fast ) {
                    break;
                }
            }

            fast = 0;
            while (true) {
                slow = nums[slow];
                fast = nums[fast];
                if( slow==fast ) {
                    break;
                }
            }
            return fast;
        }
    }
}

