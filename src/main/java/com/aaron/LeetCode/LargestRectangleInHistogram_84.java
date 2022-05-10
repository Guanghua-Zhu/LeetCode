package com.aaron.LeetCode;

import java.util.*;

//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=105 
// 0 <= heights[i] <= 104 
// 
// Related Topics 栈 数组 单调栈 
// 👍 1908 👎 0


/**
 * 84, 柱状图中最大的矩形
 * @author Aaron Zhu
 * @date 2022-5-5
 */
public class LargestRectangleInHistogram_84{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }


    /**
     * 单调递减栈
     */
    public static class Solution {
        public int largestRectangleArea(int[] heights) {
            int maxArea = 0;
            int size = heights.length;

            // 当前元素 左侧第一个比它小的元素位置索引
            int[] leftBoard = new int[size];
            // 当前元素 右侧第一个比它小的元素位置索引
            int[] rightBoard = new int[size];

            // 单调递减栈
            Deque<Integer> stack = new LinkedList<>();
            // 计算左边界
            for (int i=0; i<size; i++) {
                // 当前元素
                int current = heights[i];
                // 如果栈顶所对应的元素 不小于 当前元素, 就一直弹栈
                while ( !stack.isEmpty() && heights[stack.peek()] >= current ) {
                    stack.pop();
                }
                // 此时栈顶的位置索引 即为 左侧第一个比当前元素小的元素位置索引
                leftBoard[i] = stack.isEmpty() ? -1 : stack.peek();
                // 当前元素的位置索引入栈
                stack.push(i);
            }

            stack.clear();
            // 计算右边界
            for (int i=size-1; i>=0; i--) {
                // 当前元素
                int current = heights[i];
                // 如果栈顶所对应的元素 不小于 当前元素, 就一直弹栈
                while ( !stack.isEmpty() && heights[stack.peek()] >= current ) {
                    stack.pop();
                }
                // 此时栈顶的位置索引 即为 右侧第一个比当前元素小的元素位置索引
                rightBoard[i] = stack.isEmpty() ? size : stack.peek();
                // 当前元素的位置索引入栈
                stack.push(i);
            }

            for (int i=0; i<size; i++) {
                // 计算以当前元素为高度的矩形的面积
                int tempArea = (rightBoard[i]-leftBoard[i]-1) * heights[i];
                if( tempArea > maxArea ) {
                    maxArea = tempArea;
                }
            }

            return maxArea;
        }
    }

}
