package com.aaron.剑指Offer2ndEdition;

import java.util.HashSet;

//找出数组中重复的数字。 
//
// 
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。 
//
// 示例 1： 
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3 
// 
//
// 
//
// 限制： 
//
// 2 <= n <= 100000 
// Related Topics 数组 哈希表 排序 
// 👍 659 👎 0


/**
 * 剑指offer·第二版: 03, 数组中重复的数字
 * @author Aaron Zhu
 * @date 2022-1-4
 */
public class Offer_03{

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
    }

    /**
     * 原地交换, 归位法
     */
    public static class Solution {
        public int findRepeatNumber(int[] nums) {
            int result = -1;
            if( nums==null || nums.length==0) {
                return result;
            }

            for(int i=0; i<nums.length; ) {
                // 索引为i处的元素已经放置正确, 故继续处理下一个索引位
                if( nums[i] == i ) {
                    i++;
                    continue;
                }

                // 索引为i的元素, 正确的索引位置
                int okIndex = nums[i];
                // 正确的索引位置okIndex上已经是正确的元素值了
                // 即索引为i、okIndex处的元素均相同
                if( nums[okIndex] == okIndex ) {
                    // 找到重复元素
                    result = nums[i];
                    break;
                }

                // 交换索引为i、okIndex处的元素值
                // 实现将nums[i]元素放置到正确的索引位置上
                nums[i] = nums[okIndex];
                nums[okIndex] = okIndex;
            }
            return result;
        }
    }

    /**
     * 基于HashSet
     */
    public static class Solution1 {
        public int findRepeatNumber(int[] nums) {
            int result = -1;
            HashSet<Integer> set = new HashSet<>();
            for(int e : nums)  {
                if(!set.add(e)) {
                    result = e;
                    break;
                }
            }
            return result;
        }
    }

}
