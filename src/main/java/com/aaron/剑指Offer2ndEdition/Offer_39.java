package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素
//
// 示例 1:
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
// 输出: 2
//
// 限制：
// 1 <= 数组长度 <= 50000
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
// Related Topics 数组 哈希表 分治 计数 排序
// 👍 240 👎 0


/**
 * 剑指 Offer 39, 数组中出现次数超过一半的数字
 * @author Aaron Zhu
 * @date 2022-2-1
 */
public class Offer_39 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * Boyer–Moore摩尔投票算法
     */
    public static class Solution {
        public int majorityElement(int[] nums) {
            Integer major = null;  // 多数元素
            Integer vote = 0;   // 投票数

            for (Integer num : nums) {
                // 票数为0, 直接将当前元素作为多数元素
                if(vote == 0) {
                    major = num;
                }
                // 当前元素与多数元素相等, 则票数加1
                if(num.equals(major)) {
                    vote++;
                } else {    // 当前元素与多数元素不等, 则票数减1
                    vote--;
                }
            }

            // 对摩尔投票算法结果进行检查
            int count = 0;
            for(Integer num : nums) {
                if( num.equals(major) ) {
                    // 对算法结果进行计数
                    count++;
                }
            }
            if( count <= nums.length/2  ) {
                // 算法结果 不符合多数元素半数以上的要求
                major = null;
            }
            return major;
        }
    }

    /**
     * 哈希表计数
     */
    public static class Solution1 {
        public int majorityElement(int[] nums) {
            int result = nums[0];
            Map<Integer, Integer> countMap = new HashMap<>();
            for(int num : nums) {
                Integer oldV = countMap.get(num);
                if( oldV==null ) {
                    countMap.put(num, 1);
                } else if ( oldV == nums.length/2 ) {
                    result = num;
                    break;
                } else {
                    countMap.put(num, oldV+1);
                }
            }
            return result;
        }
    }

}


