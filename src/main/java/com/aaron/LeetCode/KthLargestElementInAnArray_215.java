package com.aaron.LeetCode;

import java.util.*;

//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1722 👎 0


/**
 * 215, 数组中的第K个最大元素
 * @author Aaron Zhu
 * @date 2022-7-1
 */
public class KthLargestElementInAnArray_215{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * Min Heap
     */
    public static class Solution {
        public int findKthLargest(int[] nums, int k) {
            Queue<Integer> minHeap = new PriorityQueue<>();

            for (int num : nums) {
                if ( minHeap.size() < k ) {
                    minHeap.offer( num );
                } else if( num > minHeap.peek() ){
                    minHeap.poll();
                    minHeap.offer( num );
                }
            }

            return minHeap.peek();
        }
    }

}

