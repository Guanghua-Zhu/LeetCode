package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 
// 👍 1615 👎 0


/**
 * 78, 子集
 * @author Aaron Zhu
 * @date 2022-5-3
 */
public class Subsets_78{
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.subsets(new int[]{1,2,3});
    }


    /**
     * 位运算
     */
    public static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            int num = nums.length;
            int maxMask = (1 << num ) - 1;

            List<List<Integer>> res = new LinkedList<>();
            for (int mask = 0; mask<= maxMask; mask++) {
                List<Integer> subRes = new LinkedList<>();

                for (int index=0; index<num; index++) {
                    int maskRes = (mask >>> index) & 1;
                    if( maskRes == 1 ) {
                        subRes.add( nums[index] );
                    }
                }

                res.add( subRes );
            }

            return res;
        }
    }

    /**
     * 回溯
     */
    public static class Solution1 {
        private List<List<Integer>> res;

        private boolean[] usedFlag;

        public List<List<Integer>> subsets(int[] nums) {
            init(nums);
            search(nums, 0);
            return res;
        }

        private void init(int[] nums) {
            res = new LinkedList<>();
            usedFlag = new boolean[ nums.length ];
        }

        private void search(int[] nums, int index) {
            if( index == nums.length ) {
                convert( nums );
                return;
            }

            // 选择当前元素
            usedFlag[index] = true;
            search( nums, index+1 );
            usedFlag[index] = false;

            // 不选择当前元素
            search(nums, index+1);
        }

        private void convert(int[] nums) {
            List<Integer> subRes = new LinkedList<>();
            for (int i=0; i<usedFlag.length; i++) {
                if( usedFlag[i] ) {
                    subRes.add( nums[i] );
                }
            }

            res.add( subRes );
        }
    }

}
