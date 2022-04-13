package com.aaron.LeetCode;

import java.util.*;

//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 3331 👎 0


/**
 * 42, 接雨水
 * @author Aaron Zhu
 * @date 2022-4-12
 */
public class TrappingRainWater_42{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 双指针
     */
    public static class Solution {
        public int trap(int[] height) {
            if( height==null || height.length<=2 ) {
                return 0;
            }

            int res = 0;
            int left = 0;
            int right = height.length-1;

            int leftMax = 0;
            int rightMax = 0;

            while (left < right) {
                leftMax = Math.max( leftMax, height[left] );
                rightMax = Math.max( rightMax, height[right] );
                int finalBoard = Math.min(leftMax, rightMax);

                if( leftMax < rightMax ) {
                    res += finalBoard - height[left];
                    left++;
                } else {
                    res += finalBoard - height[right];
                    right--;
                }
            }

            return res;
        }
    }

    /**
     * 分别进行正向、逆向遍历
     */
    public static class Solution2 {
        public int trap(int[] height) {
            if( height==null || height.length<=2 ) {
                return 0;
            }

            int[] leftMax = new int[height.length];
            int[] rightMax = new int[height.length];
            leftMax[0] = height[0];
            rightMax[height.length-1] = height[height.length-1];

            for(int left=1, right=height.length-2; left<height.length; left++, right--) {
                leftMax[left] = Math.max( leftMax[left-1], height[left] );
                rightMax[right] = Math.max( rightMax[right+1], height[right] );
            }

            int res = 0;

            for(int i=1; i<height.length-1; i++) {
                int leftBorder = leftMax[i];
                int rightBorder = rightMax[i];
                int finalBorder = Math.min( leftBorder, rightBorder );
                res += finalBorder - height[i];
            }

            return res;
        }
    }

}

