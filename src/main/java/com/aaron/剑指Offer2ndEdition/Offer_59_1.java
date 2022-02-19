package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/ 
// Related Topics 队列 滑动窗口 单调队列 堆（优先队列） 
// 👍 385 👎 0


/**
 * 剑指 Offer 59 - I, 滑动窗口的最大值
 * @author Aaron Zhu
 * @date 2022-2-19
 */
public class Offer_59_1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 滑动窗口
     */
    public static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if( nums==null || nums.length==0 ) {
                return new int[0];
            }

            int size = nums.length-k+1;
            int[] res = new int[size];
            for(int i=0; i<size; i++) {
                if( i==0 ) {
                    res[i] = calcMax(nums, 0, i+k);
                } else {
                    int dropNum = nums[i-1];
                    int newNum = nums[i+k-1];
                    if( newNum>= res[i-1] ) {
                        res[i] = newNum;
                    } else if( dropNum != res[i-1] ) {
                        res[i] = res[i-1];
                    } else {
                        res[i] = calcMax(nums, i, i+k);
                    }
                }
            }
            return res;
        }

        private int calcMax(int[] nums, int start, int end) {
            Integer max = Integer.MIN_VALUE;
            for(int i=start; i<end; i++) {
                max = Math.max(max, nums[i]);
            }
            return max;
        }
    }

}


