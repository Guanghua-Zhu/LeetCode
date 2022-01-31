package com.temp.leetcode.editor.cn;

import java.util.*;

// 输入整数数组 arr
// 找出其中最小的 k 个数
// 例如，输入4、5、1、6、2、7、3、8这8个数字
// 则最小的4个数字是1、2、3、4
//
// 示例 1：
// 输入：arr = [3,2,1], k = 2
// 输出：[1,2] 或者 [2,1]
// 
// 示例 2：
// 输入：arr = [0,1,2,1], k = 1
// 输出：[0]
//
// 限制：
// 0 <= k <= arr.length <= 10000
// 0 <= arr[i] <= 10000 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列）
// 👍 353 👎 0

/**
 * 剑指 Offer 40, 最小的k个数
 * @author Aaron Zhu
 * @date 2022-1-31
 */
public class Offer_40 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 最大堆
     */
    public static class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if(k==0) {
                return new int[0];
            }

            int[] result = new int[k];
            // 最大堆
            Queue<Integer> queue = new PriorityQueue<>( (o1, o2)->o2-o1 );
            for(Integer num : arr) {
                if( queue.size()<k ) {
                    queue.add( num );
                } else if( num<queue.peek() ) {
                    queue.poll();
                    queue.add(num);
                }
            }

            for(int i=0; i<k;i++) {
                result[i] = queue.poll();
            }

            return result;
        }
    }

    /**
     * 直接排序
     */
    public static class Solution1 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if(k==0) {
                return new int[0];
            }

            Arrays.sort(arr);
            int[] result = new int[k];
            for(int i=0; i<k; i++) {
                result[i] = arr[i];
            }
            return result;
        }
    }

    /**
     * 最小堆
     */
    public static class Solution2 {
        public int[] getLeastNumbers(int[] arr, int k) {
            if(k==0) {
                return new int[0];
            }

            Queue<Integer> queue = new PriorityQueue<>();
            for (int num : arr) {
                queue.add( num );
            }

            int[] result = new int[k];
            for(int i=0; i<k; i++) {
                result[i] = queue.poll();
            }
            return result;
        }
    }

}

