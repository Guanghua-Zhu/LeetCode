package com.aaron.LeetCode;

import java.util.*;

//给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,0,1]
//输出：2
//解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：2
//解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。 
//
// 示例 3： 
//
// 
//输入：nums = [9,6,4,2,3,5,7,0,1]
//输出：8
//解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。 
//
// 示例 4： 
//
// 
//输入：nums = [0]
//输出：1
//解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 10⁴ 
// 0 <= nums[i] <= n 
// nums 中的所有数字都 独一无二 
// 
//
// 
//
// 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题? 
//
// Related Topics 位运算 数组 哈希表 数学 二分查找 排序 👍 679 👎 0


/**
 * 268, 丢失的数字
 * @author Aaron Zhu
 * @date 2023-1-21
 */
public class MissingNumber_268{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{9,6,4,2,3,5,7,0,1};
        solution.missingNumber( nums );
        System.out.println("g");
    }

    /**
     * 异或法
     */
    public static class Solution {
        public int missingNumber(int[] nums) {
            int xor = 0;
            for (int i=0; i<nums.length; i++) {
                xor ^= nums[i];
            }
            for (int i=0; i<=nums.length; i++) {
                xor ^= i;
            }
            return xor;
        }
    }

    /**
     * 原地哈希
     */
    public static class Solution1 {
        public int missingNumber(int[] nums) {
            int size = nums.length;
            for (int i=0; i<size; i++) {
                while (i!=nums[i] && nums[i]!=size) {
                    int temp = nums[i];
                    nums[i] = nums[ nums[i] ];
                    nums[temp] = temp;
                }
            }

            int res = size;
            for (int i=0; i<size; i++) {
                if( i != nums[i] ) {
                    res = i;
                    break;
                }
            }
            return res;
        }
    }

}