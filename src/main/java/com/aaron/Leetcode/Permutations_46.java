package com.aaron.LeetCode;

import java.util.*;

//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 
// 👍 1933 👎 0


/**
 * 46, 全排列
 * @author Aaron Zhu
 * @date 2022-4-13
 */
public class Permutations_46{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.permute( new int[]{1,2,3} );
        System.out.println("gg");
    }


    /**
     * 回溯法
     */
    public static class Solution {

        private List<List<Integer>> resultList;

        private boolean[] usedFlag;

        private List<Integer> state;

        public List<List<Integer>> permute(int[] nums) {
            init(nums);
            search(nums, 0);
            return resultList;
        }

        private void init(int[] nums) {
            resultList = new LinkedList<>();
            state = new LinkedList<>();
            usedFlag = new boolean[ nums.length ];
        }

        private void search(int[] nums, int index) {
            if( index == nums.length ) {
                resultList.add( new LinkedList<>(state) );
                return;
            }

            for (int i=0; i<nums.length; i++) {
                if( usedFlag[i]==false ) {
                    usedFlag[i] = true;
                    state.add( nums[i] );
                    search(nums, index+1);
                    usedFlag[i] = false;
                    state.remove( state.size()-1 );
                }
            }
        }

    }

}
