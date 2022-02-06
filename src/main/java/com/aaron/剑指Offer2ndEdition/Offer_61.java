package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的
// 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0
// 可以看成任意数字, A 不能视为 14
//
// 示例 1:
// 输入: [1,2,3,4,5]
// 输出: True
//
// 示例 2:
// 输入: [0,0,1,2,5]
// 输出: True
//
// 限制：
// 数组长度为 5
// 数组的数取值为 [0, 13]
// Related Topics 数组 排序 
// 👍 197 👎 0


/**
 * 剑指 Offer 61, 扑克牌中的顺子
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class Offer_61 {
    public static void main(String[] args) {
        int[] nums = new int[]{4,7,5,9,2};
        Solution solution = new Solution();
        boolean res = solution.isStraight(nums);
        System.out.println("gg");
    }

    public static class Solution {
        public boolean isStraight(int[] nums) {
            boolean repeat = false;
            Set<Integer> set = new HashSet<>();
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for(int num: nums) {
                if( num==0 ) {
                    continue;
                }
                if( set.contains(num) ) {
                    repeat = true;
                    break;
                }
                set.add(num);
                max = Math.max(max, num);
                min = Math.min(min, num);
            }

            if( repeat || (max-min)>4 ) {
                return false;
            }
            return true;
        }
    }

    public static class Solution1 {
        public boolean isStraight(int[] nums) {
            int zeroCount = 0;
            for(int num : nums) {
                if(num==0) {
                    zeroCount++;
                }
            }

            Arrays.sort(nums);
            int start = -1;
            for (int i=0; i<nums.length; i++) {
                if( nums[i]==0 ) {
                    continue;
                } else if( start == -1 ) {
                    start = nums[i];
                } else if( nums[i] == start) {
                    return false;
                } else if( nums[i] == start+1 ) {
                    start = nums[i];
                } else if( nums[i] > start+1 ) {    // e = 7, s=4, s+1=5 e-s-=2
                    int needZeroCount = nums[i] - start -1;
                    if( needZeroCount <= zeroCount ) {
                        zeroCount = zeroCount - needZeroCount;
                        start = nums[i];
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}


