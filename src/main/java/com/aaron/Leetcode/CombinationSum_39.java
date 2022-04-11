package com.aaron.LeetCode;

import java.util.*;

//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。 
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都 互不相同 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯 
// 👍 1887 👎 0


/**
 * 39, 组合总和
 * @author Aaron Zhu
 * @date 2022-4-11
 */
public class CombinationSum_39{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2,3,6,7};
        int target = 7;
        List<List<Integer>> res = solution.combinationSum(nums, target);
        System.out.println("gg");
    }

    public static class Solution {
        private List<List<Integer>> resultList;

        private List<Integer> state;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            init();
            search(candidates, 0, target);
            return resultList;
        }

        private void init() {
            resultList = new LinkedList<>();
            state = new LinkedList<>();
        }

        private void search(int[] nums, int index, int remain) {
            if( index >= nums.length ) {
                return;
            } else if( remain == 0 ) {
                resultList.add( new LinkedList<>(state) );
                return;
            }

            // 使用index位置元素
            if( remain-nums[index] >= 0 ) { // 剪枝: 避免不必要的搜索
                state.add( nums[index] );
                search(nums, index, remain - nums[index] );
                state.remove( state.size()-1 );
            }

            // 不用index位置元素
            search(nums, index+1, remain);
        }
    }

}

