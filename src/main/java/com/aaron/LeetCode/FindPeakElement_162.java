package com.aaron.LeetCode;

import java.util.*;

//峰值元素是指其值严格大于左右相邻值的元素。 
//
// 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。 
//
// 你可以假设 nums[-1] = nums[n] = -∞ 。 
//
// 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1]
//输出：2
//解释：3 是峰值元素，你的函数应该返回其索引 2。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,1,3,5,6,4]
//输出：1 或 5 
//解释：你的函数可以返回索引 1，其峰值元素为 2；
//     或者返回索引 5， 其峰值元素为 6。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 对于所有有效的 i 都有 nums[i] != nums[i + 1] 
// 
//
// Related Topics 数组 二分查找 👍 1068 👎 0


/**
 * 162, 寻找峰值
 * @author Aaron Zhu
 * @date 2023-7-6
 */
public class FindPeakElement_162{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 二分查找
     */
    public static class Solution {

        public int findPeakElement(int[] nums) {
            int size = nums.length;
            if( size==1 ) {
                return 0;
            } else if ( size==2 ) {
                return nums[0] > nums[1] ? 0 : 1;
            } else if ( nums[0]>nums[1] ) {
                return 0;
            } else if ( nums[size-1]>nums[size-2] ) {
                return size-1;
            }

            int res = -1;

            int l = 0;
            int r = size-1;
            while (l <= r) {    // 二分查找搜索区间为左闭右闭区间: [l,r]
                int mid = l + (r-l)/2;
                if( nums[mid] < nums[mid+1] ) { // 峰值必然存在于[mid+1,r]
                    l = mid+1;
                } else if( nums[mid] > nums[mid+1] ) { // 峰值必然存在于[l,mid]
                    r = mid;
                }

                // 当区间中只有一个元素时，则其必然是峰值
                if( l==r ) {
                    res = l;
                    break;
                }
            }

            return res;
        }
    }

    /**
     * 线性遍历
     */
    public static class Solution1 {

        public int findPeakElement(int[] nums) {
            int size = nums.length;
            if( size==1 ) {
                return 0;
            } else if ( size==2 ) {
                return nums[0] > nums[1] ? 0 : 1;
            } else if ( nums[0]>nums[1] ) {
                return 0;
            } else if ( nums[size-1]>nums[size-2] ) {
                return size-1;
            }

            int res = -1;
            for (int i=1; i<size-1; i++) {
                if ( nums[i]>nums[i-1] && nums[i]>nums[i+1] ) {
                    res = i;
                    break;
                }
            }
            return res;
        }
    }

}