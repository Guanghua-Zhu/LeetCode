package com.temp.leetcode.editor.cn;

import java.util.*;

//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 1471 👎 0


/**
 * 416, 分割等和子集
 * @author Aaron Zhu
 * @date 2022-8-29
 */
public class PartitionEqualSubsetSum_416{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int size = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if( size==1 || sum%2==1 ) {
            return false;
        }

        int halfSum  = sum / 2;
        Set<Integer> oldSet = new HashSet<>();
        oldSet.add( 0 );

        for (int i=0; i<size; i++) {
            Set<Integer> newSet = new HashSet<>();
            for (int num : oldSet) {
                newSet.add( num );
                newSet.add( num + nums[i] );
            }

            if( newSet.contains( halfSum ) ) {
                return true;
            }

            oldSet = newSet;
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public boolean canPartition(int[] nums) {
        int size = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if( size==1 || sum%2==1 ) {
            return false;
        }

        int halfSum  = sum / 2;
        Set<Integer> oldSet = new HashSet<>();
        oldSet.add( 0 );

        for (int i=0; i<size; i++) {
            Set<Integer> newSet = new HashSet<>();
            for (int num : oldSet) {
                newSet.add( num );
                newSet.add( num + nums[i] );
            }

            if( newSet.contains( halfSum ) ) {
                return true;
            }

            oldSet = newSet;
        }

        return false;
    }
}
