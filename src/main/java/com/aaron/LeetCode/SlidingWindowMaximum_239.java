package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1607 👎 0


/**
 * 239, 滑动窗口最大值
 * @author Aaron Zhu
 * @date 2022-5-11
 */
public class SlidingWindowMaximum_239{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 单调递减队列
     */
    public static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int size = nums.length;
            int[] res = new int[ size-k+1 ];
            // 单调递减队列
            Deque<Integer> queue = new LinkedList<>();

            for (int i=0; i<size; i++) {
                int current = nums[i];
                // 当前元素破坏了队列的单调性, 则不对队尾元素进行出队操作
                while ( !queue.isEmpty() && nums[queue.peekLast()] < current ) {
                    queue.removeLast();
                }

                // 当前元素从队尾入队
                queue.addLast( i );

                // 区间范围: [i+1-k, i]
                int leftIndex = i+1-k;
                if( leftIndex>=0 ) {
                    // 队首元素不在当前区间范围内, 故移除
                    while ( !queue.isEmpty() && queue.peekFirst()<leftIndex ) {
                        queue.removeFirst();
                    }
                    // 此时队首元素即为当前区间范围内的最大值
                    res[leftIndex] = nums[ queue.peekFirst() ];
                }
            }

            return res;
        }
    }

}