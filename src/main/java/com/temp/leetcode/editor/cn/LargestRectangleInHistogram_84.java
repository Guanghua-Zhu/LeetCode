package com.temp.leetcode.editor.cn;

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
}

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for(int i=0; i<heights.length; i++) {
            int cur = heights[i];

            int leftIndex = i;
            while ( leftIndex >= 0 ) {
                if( heights[leftIndex] < cur ) {
                    break;
                }
                leftIndex--;
            }
            leftIndex++;

            int rightIndex = i;
            while ( rightIndex < heights.length ) {
                if( heights[rightIndex] < cur ) {
                    break;
                }
                rightIndex++;
            }
            rightIndex--;

            int area = (rightIndex-leftIndex+1) * cur;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/**
 * 暴力法: 超时
 */
class Solution1 {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for(int i=0; i<heights.length; i++) {
            int cur = heights[i];

            int leftIndex = i;
            while ( leftIndex >= 0 ) {
                if( heights[leftIndex] < cur ) {
                    break;
                }
                leftIndex--;
            }
            leftIndex++;

            int rightIndex = i;
            while ( rightIndex < heights.length ) {
                if( heights[rightIndex] < cur ) {
                    break;
                }
                rightIndex++;
            }
            rightIndex--;

            int area = (rightIndex-leftIndex+1) * cur;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
