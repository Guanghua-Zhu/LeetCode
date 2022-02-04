package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 在数组中的两个数字
// 如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对
// 输入一个数组，求出这个数组中的逆序对的总数
//
// 示例 1:
// 输入: [7,5,6,4]
// 输出: 5
//
// 限制：
// 0 <= 数组长度 <= 50000
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 
// 👍 608 👎 0


/**
 * 剑指 Offer 51, 数组中的逆序对
 * @author Aaron Zhu
 * @date 2022-2-1
 */
public class Offer_51{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 基于归并排序
     */
    public static class Solution {
        private int[] aux;

        public int reversePairs(int[] nums) {
            int count=0;
            if(nums==null || nums.length==0) {
                return count;
            }

            aux = new int[ nums.length ];
            count = sort(nums, 0, nums.length-1);
            return count;
        }

        private int sort(int[] array, int start, int end) {
            if( start>=end ) {
                return 0;
            }

            int mid = start + (end-start)/2;
            int countL = sort(array, start, mid);
            int countR = sort(array, mid+1, end);
            int countAll = merge(array, start, mid, end, countL+countR);
            return countAll;
        }

        private int merge(int[] array, int start, int mid, int end, int count) {
            for (int i=start; i<=end; i++) {
                aux[i] = array[i];
            }

            int i = start;
            int j = mid+1;
            for (int index=start; index<=end; index++) {
                if ( i>mid ) {
                    array[index] = aux[j];
                    j++;
                } else if( j>end ) {
                    array[index] = aux[i];
                    i++;
                } else if ( aux[i] > aux[j] ) {
                    array[index] = aux[j];
                    j++;
                    // 统计逆序对
                    count += (mid-i+1);
                } else if( aux[i] <= aux[j] ) {
                    array[index] = aux[i];
                    i++;
                }
            }

            return count;
        }
    }

    /**
     * 基于树状数组
     */
    public static class Solution1 {
        /**
         * key: 原始数组的元素值; value: 排名值(相对大小关系, 其中1是最小的)
         */
        private Map<Integer, Integer> rankMap;

        public int reversePairs(int[] nums) {
            int result = 0;
            if( nums==null || nums.length==0 ) {
                return result;
            }

            buildRankMap(nums);
            FenwickTree fenwickTree = new FenwickTree( nums.length+1 );
            for(int i=0; i<nums.length; i++) {
                // 获取映射后的新值
                int rankNum = rankMap.get(nums[i]);

                // 原数组已经遍历完成的个数
                int allNum = i;
                // 计算顺序对的数量(含相等情形)
                int pairNum = fenwickTree.getPrefixSum( rankNum );
                // 逆序对的个数 = 原数组已经遍历完成的个数 - 顺序对的个数
                int reversePairNum = allNum - pairNum;

                // 计数加1, 故对树状数组索引为rankNum进行加1
                fenwickTree.update(rankNum,1);
                result += reversePairNum;
            }

            return result;
        }

        private void buildRankMap(int[] nums) {
            // 对原数组进行去重排序, 已获取相对关系
            int[] temp = Arrays.stream(nums)
                .distinct()
                .sorted()
                .toArray();

            rankMap = new HashMap<>();
            for(int i=0; i<temp.length; i++) {
                // key: 原始数组的元素值; value: 排名值(相对大小关系, 其中1是最小的)
                rankMap.put(temp[i], i+1);
            }
        }

        public static class FenwickTree {
            private int[] bit;

            public FenwickTree(int size) {
                bit = new int[size];
            }

            /**
             * 计算前缀和
             * @param index
             * @return
             */
            public int getPrefixSum(int index) {
                int sum = 0;
                while (index>0) {
                    sum += bit[index];
                    index = index - lowbit(index);
                }
                return sum;
            }

            /**
             * 单点增量更新
             * @param index
             * @param delta
             */
            public void update(int index, int delta) {
                while (index<bit.length) {
                    bit[index] += delta;
                    index = index + lowbit(index);
                }
            }

            private int lowbit(int num) {
                return num & -num ;
            }
        }
    }

}

