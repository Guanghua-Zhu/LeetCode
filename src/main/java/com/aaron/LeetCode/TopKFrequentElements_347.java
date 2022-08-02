package com.aaron.LeetCode;

import java.util.*;
import java.util.stream.Collectors;

//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 👍 1273 👎 0


/**
 * 347, 前 K 个高频元素
 * @author Aaron Zhu
 * @date 2022-8-1
 */
public class TopKFrequentElements_347{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 桶排序
     */
    public static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int num : nums) {
                counts.put( num, counts.getOrDefault(num,0)+1 );
            }

            List<Integer>[] buckets = new ArrayList[ nums.length+1 ];
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                Integer num = entry.getKey();
                Integer count = entry.getValue();

                if( buckets[count]==null ) {
                    buckets[count] = new ArrayList<>();
                }
                buckets[count].add( num );
            }

            int[] res = new int[k];
            for (int i=nums.length, j=0; i>=0 && j<k; i--) {
                if( buckets[i] != null ) {
                    for (int num : buckets[i]) {
                        res[j] = num;
                        j++;
                    }
                }
            }

            return res;
        }
    }

    /**
     * 最小堆
     */
    public static class Solution1 {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.put( num, count.getOrDefault(num,0)+1 );
            }

            PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>( Comparator.comparing(Map.Entry::getValue) );
            for( Map.Entry<Integer, Integer> entry : count.entrySet() ) {
                if( minHeap.size() < k ) {
                    minHeap.add( entry );
                } else if ( entry.getValue() > minHeap.peek().getValue() ) {
                    minHeap.remove();
                    minHeap.add( entry );
                }
            }

            int[] res = new int[k];
            for (int i=0; i<k; i++) {
                res[i] = minHeap.remove().getKey();
            }
            return res;
        }
    }

}
