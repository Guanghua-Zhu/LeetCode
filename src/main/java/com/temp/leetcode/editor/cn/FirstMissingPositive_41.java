package com.temp.leetcode.editor.cn;

import java.util.*;

//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
// Related Topics 数组 哈希表 👍 1685 👎 0


/**
 * 41, 缺失的第一个正数
 * @author Aaron Zhu
 * @date 2022-12-5
 */
public class FirstMissingPositive_41{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstMissingPositive(int[] nums) {
        if( nums==null || nums.length==0 ) {
            return 1;
        }

        for (int i=0; )

        int res = nums.length+1;
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public int firstMissingPositive(int[] nums) {
        if( nums==null || nums.length==0 ) {
            return 1;
        }

        for(int i=0; i<nums.length; i++) {
            if( nums[i]<=0 ) {
                nums[i] = nums.length+1;
            }
        }

        for(int i=0; i<nums.length; i++) {
            int numIndex = Math.abs( nums[i] ) -1;
            if( numIndex < nums.length && nums[numIndex]>0 ) {
                nums[numIndex] = -1 * nums[numIndex];
            }
        }

        int res = nums.length + 1;
        for(int i=0; i<nums.length; i++) {
            if( nums[i]>0 ) {
                res = i+1;
                break;
            }
        }

        return res;
    }


}
